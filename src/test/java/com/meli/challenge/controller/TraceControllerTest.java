/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.controller;

import com.meli.challenge.dto.TraceDTO;
import com.meli.challenge.service.TraceService;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = TraceController.class)
public class TraceControllerTest {

    @MockBean
    private TraceService traceService;

    private TraceDTO mockDTO;

    @Before
    public void setUp() {
        mockDTO = new TraceDTO();
        mockDTO.setIp("45.4.103.255");
        mockDTO.setCountryName("Argentina");
        mockDTO.setIsoCode("ARG");
        mockDTO.setDate(LocalDate.now());
        mockDTO.getLanguages().add("");
    }

    /**
     * Test of trace method, of class TraceController.
     */
    @Test
    public void testTrace(){

        ResponseEntity mockRes = ResponseEntity.ok(mockDTO);
        Mockito.when(traceService.getTrace(Mockito.anyString())).thenReturn(mockRes);
        assertEquals(traceService.getTrace("45.4.103.255"), mockRes);
    }

    public TraceControllerTest() {
    }

    /**
     * Test of stats method, of class TraceController.
     */
    @Test
    public void testStats() {
    }

}
