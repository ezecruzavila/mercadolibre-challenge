package com.meli.challenge.service;

import com.meli.challenge.repository.TraceRepository;
import com.meli.challenge.entity.TraceEntity;
import com.meli.challenge.utilities.TestValues;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
public class TraceServiceImplTest {

    @Mock
    TraceRepository dao;

    @InjectMocks
    TraceServiceImpl svc;

    TraceEntity traceMock;

    public TraceServiceImplTest() {
    }

    @Before
    public void setup() {
        traceMock = new TraceEntity(TestValues.IP, TestValues.COUNTRY_CODE_US);
        traceMock.setId(1L);
    }

    /**
     * Test of countTraceByCountryCode method, of class TraceServiceImpl.
     */
    @Test
    public void testCountTraceByCountryCode() {
        List<Object[]> mockList = new ArrayList<>();
        Object[] mock1 = new Object[]{TestValues.COUNTRY_CODE_US, 5L};
        Object[] mock2 = new Object[]{TestValues.COUNTRY_CODE_US + "R", 7L};
        mockList.add(mock1);
        mockList.add(mock2);
        when(dao.countCountryCodeGroupByCountryCode()).thenReturn(mockList);
        HashMap<String, Long> result = svc.countTraceByCountryCode();
        assertEquals(result.get(TestValues.COUNTRY_CODE_US),(Long) 5L);
        assertEquals(result.get(TestValues.COUNTRY_CODE_US + "R"),(Long) 7L);

    }

    /**
     * Test of save method, of class TraceServiceImpl.
     */
    @Test
    public void testSave() {
        when(dao.save(any(TraceEntity.class))).thenReturn(traceMock);
        TraceEntity result = svc.save(new TraceEntity(TestValues.IP, TestValues.COUNTRY_CODE_US));
        assertEquals(result.getCountryCode(), TestValues.COUNTRY_CODE_US);
        assertEquals(result.getIp(), TestValues.IP);
    }

    /**
     * Test of findById method, of class TraceServiceImpl.
     */
    @Test
    public void testFindById() {
        Optional<TraceEntity> optional = Optional.of(traceMock);
        when(dao.findById(anyLong())).thenReturn(optional);
        TraceEntity result = svc.findById(1L);
        assertEquals(result.getCountryCode(), TestValues.COUNTRY_CODE_US);
        assertEquals(result.getIp(), TestValues.IP);
    }

    /**
     * Test of delete method, of class TraceServiceImpl.
     */
    @Test
    public void testDelete() {
        svc.delete(traceMock);
        verify(dao, times(1)).delete(traceMock);
    }

    /**
     * Test of deleteById method, of class TraceServiceImpl.
     */
    @Test
    public void testDeleteById() {
        svc.deleteById(1L);
        verify(dao, times(1)).deleteById(1L);
    }

    /**
     * Test of findAll method, of class TraceServiceImpl.
     */
    @Test
    public void testFindAll() {
        List<TraceEntity> mockList = new ArrayList<>();
        mockList.add(traceMock);
        mockList.add(traceMock);
        when(dao.findAll()).thenReturn(mockList);
        List<TraceEntity> result = svc.findAll();
        assertEquals(result.get(0).getCountryCode(), TestValues.COUNTRY_CODE_US);
        assertEquals(result.get(0).getIp(), TestValues.IP);
        assertEquals(result.get(1).getCountryCode(), TestValues.COUNTRY_CODE_US);
        assertEquals(result.get(1).getIp(), TestValues.IP);

    }

    /**
     * Test of count method, of class TraceServiceImpl.
     */
    @Test
    public void testCount() {
        when(dao.count()).thenReturn(2L);
        Long result = svc.count();
        assertEquals((Long) result, (Long) 2L);
    }

}
