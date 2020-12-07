/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.utilities;

import com.meli.challenge.dto.external.CurrencyDTO;
import com.meli.challenge.dto.external.LanguageDTO;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public final class FormatUtils {

    private FormatUtils() {
    }

    ;
    
    public static String getTime(String timezone) {
        ZoneOffset zoneOffset = ZoneOffset.of(timezone.substring(3, timezone.length()));
        ZoneId zoneId = ZoneId.ofOffset("UTC", zoneOffset);
        LocalTime offsetTime = LocalTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String formattedTime = offsetTime.format(formatter);
        formattedTime = formattedTime + " (" + timezone + ")";
        return formattedTime;
    }

    public static String getCurrency(CurrencyDTO dto) {
        if (dto.getEuroRate() != null) {
            return dto.getCode() + " (1" + dto.getCode() + " = " + dto.getEuroRate() + "EUR)";
        }
        return dto.getCode();
    }

    public static String getLanguage(LanguageDTO dto) {
        return dto.getName() + " (" + dto.getIso639_1() + ")";
    }

    public static String getDistanceBetween(double lattitude, double longitude) {
        lattitude = Math.toRadians(lattitude);
        longitude = Math.toRadians(longitude);
        double bsasLattitude = -34.0;
        double bsasLongitud = -64.0;
        double lat2 = Math.toRadians(bsasLattitude);
        double lon2 = Math.toRadians(bsasLongitud);

        double earthRadius = 6371.01; //Kilometers
        double distance = earthRadius * Math.acos(Math.sin(lattitude) * Math.sin(lat2) + Math.cos(lattitude) * Math.cos(lat2) * Math.cos(longitude - lon2));
        return String.valueOf((int) distance) + " km";       
    }
}
