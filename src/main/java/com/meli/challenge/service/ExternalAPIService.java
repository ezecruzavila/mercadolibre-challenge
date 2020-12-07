/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.service;

import com.meli.challenge.dto.external.FixerDTO;
import com.meli.challenge.dto.external.IpToCountryDTO;
import com.meli.challenge.dto.external.RestCountryDTO;
import com.meli.challenge.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Service("external-api")
public class ExternalAPIService extends BaseService {
    
    private final String FIXER_ACCESS_KEY = "09ece863ff52bd32a5587f0083f38898";

    private final String IP2COUNTRY_BASE_URL = "https://api.ip2country.info";

    private final String RESTCOUNTRY_BASE_URL = "https://restcountries.eu/rest";

    private final String FIXER_BASE_URL = "http://data.fixer.io/api/latest?access_key=";

    public RestCountryDTO getRestCountry(String countryCode) {
        return this.getRestTemplate().getForObject(this.RESTCOUNTRY_BASE_URL + "/v2/alpha/" + countryCode, RestCountryDTO.class);
    }

    public IpToCountryDTO getIp2Country(String ip) {
        return this.getRestTemplate().getForObject(this.IP2COUNTRY_BASE_URL + "/ip?" + ip, IpToCountryDTO.class);
    }

    public String getEuroRate(String currencyCode) {
        FixerDTO res = this.getRestTemplate().getForObject(this.FIXER_BASE_URL + this.FIXER_ACCESS_KEY, FixerDTO.class);
        if (res.getRates().containsKey(currencyCode)) {
            return res.getRates().get(currencyCode);
        }
        return null;
    }
}
