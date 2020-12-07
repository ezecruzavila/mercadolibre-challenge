/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.service;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RunWith(SpringRunner.class)
public class ExternalAPIServiceTest {
    
    @InjectMocks
    RestTemplate restTemplateMock;
    
    public ExternalAPIServiceTest() {
    }

    /**
     * Test of getRestCountry method, of class ExternalAPIService.
     */
    @Test
    public void testGetRestCountry() {
    }

    /**
     * Test of getIp2Country method, of class ExternalAPIService.
     */
    @Test
    public void testGetIp2Country() {
    }

    /**
     * Test of getEuroRate method, of class ExternalAPIService.
     */
    @Test
    public void testGetEuroRate() {
    }
    
}
