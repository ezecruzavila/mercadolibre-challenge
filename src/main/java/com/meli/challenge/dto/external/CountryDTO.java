/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.dto.external;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public class CountryDTO implements Serializable{
    
    private String name;
    private String alpha2Code;
    private List<CurrencyDTO> currencies = new ArrayList<>();
    private List<LanguageDTO> languages = new ArrayList<>();
    private List<String> timezones = new ArrayList<>();
    private List<Double> latlng = new ArrayList<>();

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    
    
    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public List<CurrencyDTO> getCurrencies() {
        return currencies;
    }

    /**
     *
     * @param currencies
     */
    public void setCurrencies(List<CurrencyDTO> currencies) {
        this.currencies = currencies;
    }

    /**
     *
     * @return
     */
    public List<LanguageDTO> getLanguages() {
        return languages;
    }

    /**
     *
     * @param languages
     */
    public void setLanguages(List<LanguageDTO> languages) {
        this.languages = languages;
    }

    /**
     *
     * @return
     */
    public List<String> getTimezones() {
        return timezones;
    }

    /**
     *
     * @param timezones
     */
    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }
 
    
    
}
