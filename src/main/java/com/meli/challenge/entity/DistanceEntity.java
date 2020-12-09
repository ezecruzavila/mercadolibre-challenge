/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Entity
public class DistanceEntity  implements Comparable<DistanceEntity>{

    @Id
    private String countryCode;

    @Column
    private Long distance;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public DistanceEntity() {
    }

    public DistanceEntity(String countryCode, Long distance) {
        this.countryCode = countryCode;
        this.distance = distance;
    }

    @Override
    public int compareTo(DistanceEntity o) {
        return this.getDistance().compareTo(o.getDistance());
    }

}
