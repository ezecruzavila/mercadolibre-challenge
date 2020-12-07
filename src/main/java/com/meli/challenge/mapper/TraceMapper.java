/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.mapper;

import com.meli.challenge.dto.external.IpToCountryDTO;
import com.meli.challenge.dto.external.RestCountryDTO;
import com.meli.challenge.dto.TraceDTO;
import com.meli.challenge.utilities.FormatUtils;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TraceMapper {
    
    @BeforeMapping
    protected void format(RestCountryDTO restCountry, @MappingTarget TraceDTO dto) {
        restCountry.getTimezones().forEach(timezone -> {
            dto.getTimes().add(FormatUtils.getTime(timezone));
        });
        restCountry.getCurrencies().forEach(currency -> {
            dto.getCurrencies().add(FormatUtils.getCurrency(currency));
        });
        restCountry.getLanguages().forEach(language -> {
            dto.getLanguages().add(FormatUtils.getLanguage(language));
        });
        dto.setEstimatedDistance(FormatUtils.getDistanceBetween(
                restCountry.getLatlng().get(0), restCountry.getLatlng().get(1)));
        
    }
    
    @Mappings({
        @Mapping(source = "ip", target = "ip"),
        @Mapping(source = "ipToCountry.countryCode", target = "isoCode"),
        @Mapping(source = "restCountry.name", target = "countryName"),
        @Mapping(target = "currencies", ignore = true),
        @Mapping(target = "languages", ignore = true)
    })
    public abstract TraceDTO toDto(String ip, IpToCountryDTO ipToCountry, RestCountryDTO restCountry);
    
}
