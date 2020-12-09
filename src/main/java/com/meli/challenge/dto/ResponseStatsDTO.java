/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.dto;

import java.io.Serializable;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public class ResponseStatsDTO implements Serializable{
    
    private String furthestDistanceCall = "0 km";
    private String closestDistanceCall = "0 km";
    private String averageDistanceCall = "0 km";

    public String getFurthestDistanceCall() {
        return furthestDistanceCall;
    }

    public void setFurthestDistanceCall(String furthestDistanceCall) {
        this.furthestDistanceCall = furthestDistanceCall;
    }

    public String getClosestDistanceCall() {
        return closestDistanceCall;
    }

    public void setClosestDistanceCall(String closestDistanceCall) {
        this.closestDistanceCall = closestDistanceCall;
    }

    public String getAverageDistanceCall() {
        return averageDistanceCall;
    }

    public void setAverageDistanceCall(String averageDistanceCall) {
        this.averageDistanceCall = averageDistanceCall;
    }




}
