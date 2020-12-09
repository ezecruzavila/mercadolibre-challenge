package com.meli.challenge.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public class ResponseTraceDTO implements Serializable {

    private String ip;
    private String countryName;
    private String countryCode;
    private LocalDate date = LocalDate.now();
    private List<String> languages = new ArrayList();
    private List<String> currencies = new ArrayList();
    private List<String> times = new ArrayList();
    private String estimatedDistance;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public String getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(String estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }
    
    

}
