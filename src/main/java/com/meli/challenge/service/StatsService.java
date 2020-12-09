/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.service;

import com.meli.challenge.dto.ResponseStatsDTO;
import com.meli.challenge.entity.DistanceEntity;

import com.meli.challenge.utilities.FormatUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Service("stats")
public class StatsService {

    @Autowired
    DistanceServiceImpl distanceSvc;

    @Autowired
    TraceServiceImpl traceSvc;

    public ResponseStatsDTO getDistanceStats() {
        List<DistanceEntity> distanceEntitys = distanceSvc.findAll();
        HashMap<String, Long> tracesCountHashMap = traceSvc.countTraceByCountryCode();
        ResponseStatsDTO response = this.setDistances(distanceEntitys, tracesCountHashMap);
        return response;
    }

    private Long calculateAvegareDistanceCall(List<DistanceEntity> distances, HashMap<String, Long> tracesCountHashMap) {
        Long averageDistance = 0L;
        for (DistanceEntity distance : distances) {
            averageDistance = averageDistance + (distance.getDistance() * tracesCountHashMap.get(distance.getCountryCode()));
        }
        Long requestCount = traceSvc.count();
        averageDistance = averageDistance / requestCount;
        return averageDistance;
    }

    private ResponseStatsDTO setDistances(List<DistanceEntity> countryDistanceList, HashMap<String, Long> tracesCountHashMap) {
        ResponseStatsDTO dto = new ResponseStatsDTO();
        if (!countryDistanceList.isEmpty()) {
            Collections.sort(countryDistanceList);

            String closest = countryDistanceList.get(0).getCountryCode() + " "
                    + FormatUtils.distanceLongToString(countryDistanceList.get(0).getDistance());
            String furthest = countryDistanceList.get(countryDistanceList.size() - 1).getCountryCode() + " "
                    + FormatUtils.distanceLongToString(countryDistanceList.get(countryDistanceList.size() - 1).getDistance());
            String average = FormatUtils.distanceLongToString(this.calculateAvegareDistanceCall(countryDistanceList, tracesCountHashMap));

            dto.setClosestDistanceCall(closest);
            dto.setFurthestDistanceCall(furthest);
            dto.setAverageDistanceCall(average);
        }

        return dto;
    }
}
