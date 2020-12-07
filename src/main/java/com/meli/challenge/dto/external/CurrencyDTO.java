/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.dto.external;

import java.io.Serializable;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public class CurrencyDTO implements Serializable{
    
    private String code;
    private String name;
    private String symbol;
    private String euroRate;

    public String getEuroRate() {
        return euroRate;
    }

    public void setEuroRate(String euroRate) {
        this.euroRate = euroRate;
    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    
    
}
