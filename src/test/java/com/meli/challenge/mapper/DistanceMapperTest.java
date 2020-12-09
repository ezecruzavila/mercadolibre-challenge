/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.mapper;

import com.meli.challenge.dto.ResponseTraceDTO;
import com.meli.challenge.entity.DistanceEntity;
import com.meli.challenge.utilities.TestValues;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class DistanceMapperTest {

    DistanceMapperImpl mapper = new DistanceMapperImpl();

    public DistanceMapperTest() {
    }

    /**
     * Test of toEntity method, of class CountryDistanceMapper.
     */
    @Test
    public void testToEntity_whenTraceIsNotNull() {
        DistanceEntity countryDistance = new DistanceEntity(TestValues.COUNTRY_CODE_US, TestValues.ESTIMATED_DISTANCE_US_LG);

        ResponseTraceDTO responseTraceDTO = new ResponseTraceDTO();
        responseTraceDTO.setCountryCode(TestValues.COUNTRY_CODE_US);
        responseTraceDTO.setEstimatedDistance(TestValues.ESTIMATED_DISTANCE_US_STR);
        
        DistanceEntity result = mapper.toEntity(responseTraceDTO);
        assertEquals(countryDistance.getCountryCode(), result.getCountryCode());
        assertEquals(countryDistance.getDistance(), result.getDistance());
    }
    
    @Test
    public void testToEntity_whenTraceIsNull(){
        assertNull(mapper.toEntity(null));
    }

}
