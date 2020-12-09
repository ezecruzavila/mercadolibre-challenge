/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.mapper;

import com.meli.challenge.dto.ResponseTraceDTO;
import com.meli.challenge.dto.external.CountryDTO;
import com.meli.challenge.entity.TraceEntity;
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
public abstract class ResponseTraceMapper {

    @BeforeMapping
    protected void format(CountryDTO restCountry, @MappingTarget ResponseTraceDTO dto) {
        if (restCountry != null) {
            if (!restCountry.getTimezones().isEmpty()) {
                restCountry.getTimezones().forEach(timezone -> {
                    dto.getTimes().add(FormatUtils.getTime(timezone));
                });
            }

            if (!restCountry.getCurrencies().isEmpty()) {
                restCountry.getCurrencies().forEach(currency -> {
                    dto.getCurrencies().add(FormatUtils.getCurrency(currency));
                });
            }
            if (!restCountry.getLanguages().isEmpty()) {
                restCountry.getLanguages().forEach(language -> {
                    dto.getLanguages().add(FormatUtils.getLanguage(language));
                });
            }
            dto.setEstimatedDistance(FormatUtils.getDistanceToBuenosAires(
                    restCountry.getLatlng().get(0), restCountry.getLatlng().get(1)));
        }
    }

    @Mappings({
        @Mapping(source = "ip", target = "ip"),
        @Mapping(source = "restCountry.alpha2Code", target = "countryCode"),
        @Mapping(source = "restCountry.name", target = "countryName"),
        @Mapping(target = "currencies", ignore = true),
        @Mapping(target = "languages", ignore = true)
    })
    public abstract ResponseTraceDTO toCompleteDTO(String ip, CountryDTO restCountry);

    public abstract ResponseTraceDTO toReducedDTO(TraceEntity trace);
}
