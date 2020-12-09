/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.service;

import com.meli.challenge.dto.ResponseTraceDTO;
import com.meli.challenge.dto.external.FixerDTO;
import com.meli.challenge.dto.external.IpToCountryDTO;
import com.meli.challenge.dto.external.CountryDTO;
import com.meli.challenge.mapper.ResponseTraceMapper;

import com.meli.challenge.utilities.ExternalAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Service("external")
public class ExternalAPIService {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ResponseTraceMapper mapper;

    public CountryDTO getRestCountry(String countryCode) {
        return this.getRestTemplate().getForObject(ExternalAPI.RESTCOUNTRY_BASE_URL + countryCode, CountryDTO.class);
    }

    public IpToCountryDTO getIp2Country(String ip) {
        return this.getRestTemplate().getForObject(ExternalAPI.IP2COUNTRY_BASE_URL + ip, IpToCountryDTO.class);
    }

    public String getEuroRate(String currencyCode) {
        String rate = "";
        FixerDTO res = this.getRestTemplate().getForObject(ExternalAPI.FIXER_BASE_URL + ExternalAPI.FIXER_ACCESS_KEY, FixerDTO.class);
        if (res != null && res.getRates().containsKey(currencyCode)) {
            rate = res.getRates().get(currencyCode);
        }
        return rate;
    }

    public ResponseTraceDTO getDataFromIP(String ip) {
        IpToCountryDTO ip2Country = this.getIp2Country(ip);
        CountryDTO restCountry = this.getRestCountry(ip2Country.getCountryCode());
        restCountry.getCurrencies().forEach(currency -> {
            currency.setEuroRate(this.getEuroRate(currency.getCode()));
        });
        return mapper.toCompleteDTO(ip, restCountry);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
