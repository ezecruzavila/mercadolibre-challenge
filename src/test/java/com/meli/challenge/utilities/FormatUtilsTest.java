/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.utilities;

import com.meli.challenge.dto.external.CurrencyDTO;
import com.meli.challenge.dto.external.LanguageDTO;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public class FormatUtilsTest {

    final String IP = "34.4.103.255";
    final String COUNTRY_CODE = "USA";
    final String COUNTRY_NAME = "United States";
    final String TIMEZONE = "UTC-03:00";
    final String CURRENCY_CODE = "USD";
    final String EURO_RATE = "1.211096";
    final String LANGUAGE = "English (en)";
    final String DISTANCE_USA = "8701 km";
    final Double LAT_USA = 38.0;
    final Double LONG_USA = -97.0;

    public FormatUtilsTest() {
    }

    /**
     * Test of getTime method, of class FormatUtils.
     */
    @Test
    public void testGetTime() {
        String date = LocalTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("hh:mm:ss")) + " (UTC)";
        assertEquals(date, FormatUtils.getTime("UTC"));
    }

    /**
     * Test of getCurrency method, of class FormatUtils.
     */
    @Test
    public void testGetCurrency_whenEuroRateNotNull() {
        CurrencyDTO dto = new CurrencyDTO();
        dto.setCode(CURRENCY_CODE);
        dto.setEuroRate(EURO_RATE);
        assertEquals("USD (1USD = 0.826 EUR)", FormatUtils.getCurrency(dto));
    }

    @Test
    public void testGetCurrency_whenEuroRateIsNull() {
        CurrencyDTO dto = new CurrencyDTO();
        dto.setCode(CURRENCY_CODE);
        dto.setEuroRate(null);
        assertEquals(CURRENCY_CODE, FormatUtils.getCurrency(dto));
    }

    /**
     * Test of getLanguage method, of class FormatUtils.
     */
    @Test
    public void testGetLanguage() {
        LanguageDTO dto = new LanguageDTO();
        dto.setName("English");
        dto.setIso639_1("en");
        assertEquals(LANGUAGE, FormatUtils.getLanguage(dto));
    }

    /**
     * Test of getDistanceToBuenosAires method, of class FormatUtils.
     */
    @Test
    public void testGetDistanceToBuenosAires() {
        assertEquals(DISTANCE_USA, FormatUtils.getDistanceToBuenosAires(LAT_USA, LONG_USA));
    }

    /**
     * Test of distanceStringToLong method, of class FormatUtils.
     */
    @Test
    public void testDistanceStringToLong() {
        assertEquals((Long) 8701L, FormatUtils.distanceStringToLong(DISTANCE_USA));
    }

    /**
     * Test of distanceLongToString method, of class FormatUtils.
     */
    @Test
    public void testDistanceLongToString() {
        assertEquals(DISTANCE_USA, FormatUtils.distanceLongToString(8701L));
    }

}
