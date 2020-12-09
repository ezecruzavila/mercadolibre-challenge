package com.meli.challenge.service;

import com.meli.challenge.repository.DistanceRepository;
import com.meli.challenge.entity.DistanceEntity;
import com.meli.challenge.utilities.TestValues;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class DistanceServiceImplTest {

    @Mock
    DistanceRepository dao;

    @InjectMocks
    DistanceServiceImpl svc;

    DistanceEntity distanceMock;

    @Before
    public void setup() {
        distanceMock = new DistanceEntity(TestValues.COUNTRY_CODE_US, TestValues.ESTIMATED_DISTANCE_US_LG);
    }

    public DistanceServiceImplTest() {
    }

    @Test
    public void testSave() {
        when(dao.save(any(DistanceEntity.class))).thenReturn(distanceMock);
        DistanceEntity result = svc.save(new DistanceEntity(TestValues.COUNTRY_CODE_US, TestValues.ESTIMATED_DISTANCE_US_LG));
        assertEquals(result.getCountryCode(), TestValues.COUNTRY_CODE_US);
        assertEquals(result.getDistance(), TestValues.ESTIMATED_DISTANCE_US_LG);
    }

    /**
     * Test of findById method, of class TraceServiceImpl.
     */
    @Test
    public void testFindById() {
        Optional<DistanceEntity> optional = Optional.of(distanceMock);
        when(dao.findById(anyString())).thenReturn(optional);
        DistanceEntity result = svc.findById(anyString());
        assertEquals(result.getCountryCode(), TestValues.COUNTRY_CODE_US);
        assertEquals(result.getDistance(), TestValues.ESTIMATED_DISTANCE_US_LG);
    }

    @Test
    public void testSaveIfNotExists_whenNotExist() {
        when(dao.existsById(anyString())).thenReturn(Boolean.FALSE);
        when(dao.save(any(DistanceEntity.class))).thenReturn(distanceMock);
        DistanceEntity result = svc.saveIfNotExists(new DistanceEntity(TestValues.COUNTRY_CODE_US, TestValues.ESTIMATED_DISTANCE_US_LG));
        assertEquals(result.getCountryCode(), TestValues.COUNTRY_CODE_US);
        assertEquals(result.getDistance(), TestValues.ESTIMATED_DISTANCE_US_LG);
    }
    
        @Test
    public void testSaveIfNotExists_whenExist() {
        when(dao.existsById(anyString())).thenReturn(Boolean.TRUE);
        //when(dao.save(any(DistanceEntity.class))).thenReturn(distanceMock);
        DistanceEntity result = svc.saveIfNotExists(new DistanceEntity(TestValues.COUNTRY_CODE_US, TestValues.ESTIMATED_DISTANCE_US_LG));
        assertNull(result);
    }


    /**
     * Test of delete method, of class TraceServiceImpl.
     */
    @Test
    public void testDelete() {
        svc.delete(distanceMock);
        verify(dao, times(1)).delete(distanceMock);
    }

    /**
     * Test of deleteById method, of class TraceServiceImpl.
     */
    @Test
    public void testDeleteById() {
        svc.deleteById(TestValues.CURRENCY_CODE);
        verify(dao, times(1)).deleteById(TestValues.CURRENCY_CODE);
    }

    /**
     * Test of findAll method, of class TraceServiceImpl.
     */
    @Test
    public void testFindAll() {
        List<DistanceEntity> mockList = new ArrayList<>();
        mockList.add(distanceMock);
        mockList.add(distanceMock);
        when(dao.findAll()).thenReturn(mockList);
        List<DistanceEntity> result = svc.findAll();
        assertEquals(TestValues.COUNTRY_CODE_US, result.get(0).getCountryCode());
        assertEquals(TestValues.ESTIMATED_DISTANCE_US_LG, result.get(0).getDistance());
        assertEquals(TestValues.COUNTRY_CODE_US, result.get(1).getCountryCode());
        assertEquals(TestValues.ESTIMATED_DISTANCE_US_LG, result.get(1).getDistance());

    }

}
