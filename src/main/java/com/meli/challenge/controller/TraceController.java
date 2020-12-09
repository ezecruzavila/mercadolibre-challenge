/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.controller;

import com.meli.challenge.dto.RequestDTO;
import com.meli.challenge.dto.ResponseStatsDTO;
import com.meli.challenge.dto.ResponseTraceDTO;
import com.meli.challenge.entity.DistanceEntity;
import com.meli.challenge.entity.TraceEntity;
import com.meli.challenge.mapper.DistanceMapper;
import com.meli.challenge.mapper.ResponseTraceMapper;
import com.meli.challenge.service.DistanceServiceImpl;
import com.meli.challenge.service.ExternalAPIService;
import com.meli.challenge.service.StatsService;
import com.meli.challenge.service.TraceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RestController
@RequestMapping("trace")
public class TraceController {

    @Autowired
    TraceServiceImpl traceService;

    @Autowired
    StatsService statsService;

    @Autowired
    ExternalAPIService externalService;
    
    @Autowired
    DistanceServiceImpl distanceServiceImpl;

    @Autowired
    ResponseTraceMapper responseTraceMapper;
    
    @Autowired
    DistanceMapper distanceMapper;

    @PostMapping()
    ResponseEntity save(@RequestBody RequestDTO dto) {
        ResponseTraceDTO response = externalService.getDataFromIP(dto.getIp());
        TraceEntity trace = new TraceEntity(response.getIp(), response.getCountryCode());
        traceService.save(trace);
        DistanceEntity distance = distanceMapper.toEntity(response);
        distanceServiceImpl.saveIfNotExists(distance);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats")
    public ResponseEntity stats() {
        ResponseStatsDTO dto = statsService.getDistanceStats();
        return ResponseEntity.ok(dto);
    }

}
