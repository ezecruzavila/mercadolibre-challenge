/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.controller;

import com.meli.challenge.service.ExternalAPIService;
import com.meli.challenge.service.StatsService;
import com.meli.challenge.service.TraceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.meli.challenge.mapper.ResponseTraceMapper;
import com.meli.challenge.mapper.DistanceMapper;
import com.meli.challenge.service.DistanceServiceImpl;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TraceController.class)
public class TraceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    TraceServiceImpl traceService;

    @MockBean
    StatsService statsService;

    @MockBean
    ExternalAPIService externalService;

    @MockBean
    DistanceServiceImpl distanceServiceImpl;

    @MockBean
    ResponseTraceMapper responseTraceMapper;
    
    @MockBean
    DistanceMapper distanceMapper;

    @Test
    public void stats_ProperRequest_httpStatus200() throws Exception {
        mvc.perform(
                get("/trace/stats"))
                .andExpect(status().isOk());
    }

    public TraceControllerTest() {
    }

}
