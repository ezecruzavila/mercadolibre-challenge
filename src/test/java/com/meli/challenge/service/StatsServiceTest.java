package com.meli.challenge.service;

import com.meli.challenge.dto.ResponseStatsDTO;
import com.meli.challenge.entity.DistanceEntity;
import com.meli.challenge.utilities.TestValues;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class StatsServiceTest {

    @Mock
    TraceServiceImpl traceSvc;

    @Mock
    DistanceServiceImpl distanceSvc;

    @InjectMocks
    StatsService svc;

    public StatsServiceTest() {
    }

    /**
     * Test of getDistanceStats method, of class StatsService when table contains data.
     */
    @Test
    public void testGetDistanceStats_whenDistancesExist() {
        List<DistanceEntity> distanceMocks = new ArrayList();
        distanceMocks.add(new DistanceEntity(TestValues.COUNTRY_CODE_US, TestValues.ESTIMATED_DISTANCE_US_LG));
        distanceMocks.add(new DistanceEntity(TestValues.COUNTRY_CODE_GE, TestValues.ESTIMATED_DISTANCE_GE_LG));
        HashMap<String, Long> mockMap = new HashMap();
        mockMap.put(TestValues.COUNTRY_CODE_US, 1L);
        mockMap.put(TestValues.COUNTRY_CODE_GE, 1L);
        when(distanceSvc.findAll()).thenReturn(distanceMocks);
        when(traceSvc.countTraceByCountryCode()).thenReturn(mockMap);
        when(traceSvc.count()).thenReturn(2L);
        ResponseStatsDTO result = svc.getDistanceStats();
        assertEquals(TestValues.COUNTRY_CODE_US + " " + TestValues.ESTIMATED_DISTANCE_US_STR, result.getClosestDistanceCall());
        assertEquals(TestValues.COUNTRY_CODE_GE + " " + TestValues.ESTIMATED_DISTANCE_GE_STR,result.getFurthestDistanceCall());
        assertEquals(TestValues.AVERAGE_DISTANCE_STR, result.getAverageDistanceCall());

    }
    
        /**
     * Test of getDistanceStats method, of class StatsService when table does not contain data.
     */
    @Test
    public void testGetDistanceStats_whenDistancesNotExist() {
        List<DistanceEntity> distanceMocks = new ArrayList();
        HashMap<String, Long> mockMap = new HashMap();

        when(distanceSvc.findAll()).thenReturn(distanceMocks);
        when(traceSvc.countTraceByCountryCode()).thenReturn(mockMap);
        ResponseStatsDTO result = svc.getDistanceStats();
        assertEquals("0 km", result.getClosestDistanceCall());
        assertEquals("0 km",result.getFurthestDistanceCall());
        assertEquals("0 km", result.getAverageDistanceCall());

    }
    
    
    
    

}
