package com.meli.challenge.dto;

import com.meli.challenge.utilities.TestValues;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public class ResponseTraceDTO implements Serializable {

    @ApiModelProperty(notes = "IP sobre la que se hizo la peticion", example = TestValues.IP)
    private String ip;
    @ApiModelProperty(notes = "Nombre del pais al que pertenece la IP", example = TestValues.COUNTRY_NAME)
    private String countryName;
    @ApiModelProperty(notes = "Código ISO de 2 dígitos del pais", example = TestValues.COUNTRY_CODE_US)
    private String countryCode;
    @ApiModelProperty(notes = "Fecha en que se hizo la peticion")
    private LocalDate date;
    @ApiModelProperty(notes = "Idiomas que se hablan en el pais al que pertenece la IP", example = "English (en)")
    private List<String> languages = new ArrayList();
    @ApiModelProperty(notes = "Monedas y cotizacion en euros del pais al que pertenece la IP", example = "USD (1USD = 0.826 EUR)")
    private List<String> currencies = new ArrayList();
    @ApiModelProperty(notes = "Hora y zonas horarias del pais al que pertenece la IP", example = "23:54:12 (UTC-03:00)")
    private List<String> times = new ArrayList();
    @ApiModelProperty(notes = "Distancia aproximada al pais al que pertenece la IP", example = "8701 km")
    private String estimatedDistance;

    public ResponseTraceDTO() {
        date = LocalDate.now();
    }

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
