/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.dto.external;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public class FixerDTO implements Serializable{
    
    private boolean success;
    private String base;
    private HashMap<String, String> rates = new HashMap<>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }


    public HashMap<String, String> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, String> rates) {
        this.rates = rates;
    }
    
    
}
