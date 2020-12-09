/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.service;

import com.meli.challenge.dto.ResponseTraceDTO;
import com.meli.challenge.dto.external.CurrencyDTO;
import com.meli.challenge.dto.external.FixerDTO;
import com.meli.challenge.dto.external.IPInfoDTO;
import com.meli.challenge.dto.external.CountryDTO;
import com.meli.challenge.mapper.ResponseTraceMapper;
import com.meli.challenge.utilities.ExternalAPI;
import com.meli.challenge.utilities.FormatUtils;
import com.meli.challenge.utilities.TestValues;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RunWith(SpringRunner.class)
public class ExternalAPIServiceTest {

    @Mock
    RestTemplate rt;

    @Mock
    ResponseTraceMapper mapper;

    @InjectMocks
    ExternalAPIService svc;

    CountryDTO restCountryDTO = new CountryDTO();
    IPInfoDTO itcdtoDTO = new IPInfoDTO();
    FixerDTO fixerDTO = new FixerDTO();
    ResponseTraceDTO responseTraceDTO = new ResponseTraceDTO();

    @Before
    public void setup() {
        List<String> times = new ArrayList();
        CurrencyDTO currencyDTO = new CurrencyDTO();
        List<CurrencyDTO> currenciesDTOs = new ArrayList<>();
        times.add(FormatUtils.getTime(TestValues.TIMEZONE));
        List<String> languages = new ArrayList();
        List<String> currencies = new ArrayList<>();
        currencies.add(TestValues.CURRENCY_CODE + " (1" + TestValues.CURRENCY_CODE + " = " + TestValues.USD_RATE + " EUR)");
        languages.add(TestValues.LANGUAGE_NAME + " (" + TestValues.LANGUAGE_ISO + ")");
        currencyDTO.setCode(TestValues.CURRENCY_CODE);
        currencyDTO.setEuroRate(TestValues.EURO_RATE);

        restCountryDTO.setName(TestValues.COUNTRY_NAME);
        restCountryDTO.setCurrencies(currenciesDTOs);
        itcdtoDTO.setCountryCode(TestValues.COUNTRY_CODE_US);
        itcdtoDTO.setCountryName(TestValues.COUNTRY_NAME);
        fixerDTO.getRates().put(TestValues.CURRENCY_CODE, TestValues.USD_RATE);
        responseTraceDTO.setIp(TestValues.IP);
        responseTraceDTO.setCountryName(TestValues.COUNTRY_NAME);
        responseTraceDTO.setCountryCode(TestValues.COUNTRY_CODE_US);
        responseTraceDTO.setLanguages(languages);
        responseTraceDTO.setCurrencies(currencies);
        responseTraceDTO.setTimes(times);
        responseTraceDTO.setEstimatedDistance(TestValues.ESTIMATED_DISTANCE_US_STR);
    }

    public ExternalAPIServiceTest() {
    }

    /**
     * Test of getDataFromIP method, of class ExternalAPIService.
     */
    @Test
    public void testGetDataFromIP() {
        when(rt.getForObject(ExternalAPI.FIXER_BASE_URL + ExternalAPI.FIXER_ACCESS_KEY, FixerDTO.class)).thenReturn(fixerDTO);
        when(rt.getForObject(ExternalAPI.RESTCOUNTRY_BASE_URL + TestValues.COUNTRY_CODE_US, CountryDTO.class)).thenReturn(restCountryDTO);
        when(rt.getForObject(ExternalAPI.IP2COUNTRY_BASE_URL + TestValues.IP, IPInfoDTO.class)).thenReturn(itcdtoDTO);
        when(mapper.toCompleteDTO(anyString(), any(CountryDTO.class))).thenReturn(responseTraceDTO);
        ResponseTraceDTO result = svc.getDataFromIP(TestValues.IP);

        assertEquals(responseTraceDTO.getIp(), result.getIp());
        assertEquals(responseTraceDTO.getCountryName(), result.getCountryName());
        assertEquals(responseTraceDTO.getCountryCode(), result.getCountryCode());
        assertEquals(responseTraceDTO.getLanguages().get(0), result.getLanguages().get(0));
        assertEquals(responseTraceDTO.getCurrencies().get(0), result.getCurrencies().get(0));
        assertEquals(responseTraceDTO.getTimes().get(0), result.getTimes().get(0));
        assertEquals(responseTraceDTO.getEstimatedDistance(), result.getEstimatedDistance());

    }

    /**
     * Test of getRestCountry method, of class ExternalAPIService.
     */
    @Test
    public void testGetRestCountry() {
        Mockito.when(rt.getForObject(ExternalAPI.RESTCOUNTRY_BASE_URL + TestValues.COUNTRY_CODE_US, CountryDTO.class)).thenReturn(restCountryDTO);
        CountryDTO response = svc.getRestCountry(TestValues.COUNTRY_CODE_US);
        assertEquals(restCountryDTO, response);
    }

    /**
     * Test of getIp2Country method, of class ExternalAPIService.
     */
    @Test
    public void testGetIp2Country() {
        Mockito.when(rt.getForObject(ExternalAPI.IP2COUNTRY_BASE_URL + TestValues.IP, IPInfoDTO.class)).thenReturn(itcdtoDTO);
        IPInfoDTO response = svc.getIp2Country(TestValues.IP);
        assertEquals(itcdtoDTO, response);
    }

    /**
     * Test of getEuroRate method, of class ExternalAPIService.
     */
    @Test
    public void testGetEuroRate() {
        Mockito.when(rt.getForObject(ExternalAPI.FIXER_BASE_URL + ExternalAPI.FIXER_ACCESS_KEY, FixerDTO.class)).thenReturn(fixerDTO);
        String response = svc.getEuroRate(TestValues.CURRENCY_CODE);
        assertEquals(TestValues.USD_RATE, response);
    }

    @Test
    public void testGetEuroRateWhenEmpty() {
        fixerDTO.getRates().remove(TestValues.CURRENCY_CODE);
        Mockito.when(rt.getForObject(ExternalAPI.FIXER_BASE_URL + ExternalAPI.FIXER_ACCESS_KEY, FixerDTO.class)).thenReturn(fixerDTO);
        String response = svc.getEuroRate(TestValues.CURRENCY_CODE);
        assertEquals("", response);
    }

    @Test
    public void testGetEuroRateWhenNull() {
        Mockito.when(rt.getForObject(ExternalAPI.FIXER_BASE_URL + ExternalAPI.FIXER_ACCESS_KEY, FixerDTO.class)).thenReturn(null);
        String response = svc.getEuroRate(TestValues.CURRENCY_CODE);
        assertEquals("", response);
    }

}
