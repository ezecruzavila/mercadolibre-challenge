/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.service;

import com.meli.challenge.dto.external.IpToCountryDTO;
import com.meli.challenge.dto.external.RestCountryDTO;
import com.meli.challenge.mapper.TraceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Service("trace")
public class TraceService {
    
    @Autowired
    ExternalAPIService externalAPIService;
    

    @Autowired
    TraceMapper traceMapper;
    
    public ResponseEntity getTrace(String ip){
        
        IpToCountryDTO ip2Country = externalAPIService.getIp2Country(ip);
        RestCountryDTO restCountry = externalAPIService.getRestCountry(ip2Country.getCountryCode());
        restCountry.getCurrencies().forEach(currency -> {
            currency.setEuroRate(externalAPIService.getEuroRate(currency.getCode()));
        });
        return ResponseEntity.ok(traceMapper.toDto(ip, ip2Country, restCountry));
    }
            
    
    
    
}
