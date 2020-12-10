/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.dto;

import com.meli.challenge.utilities.TestValues;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public class RequestDTO implements Serializable {

    @ApiModelProperty(notes = "IP sobre la que se desea obtener trace", example = TestValues.IP)
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
