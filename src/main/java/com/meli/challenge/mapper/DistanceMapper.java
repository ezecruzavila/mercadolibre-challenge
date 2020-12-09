/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.mapper;

import com.meli.challenge.dto.ResponseTraceDTO;
import com.meli.challenge.entity.DistanceEntity;
import com.meli.challenge.utilities.FormatUtils;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DistanceMapper {

    @BeforeMapping
    protected void format(ResponseTraceDTO trace, @MappingTarget DistanceEntity countryDistance){
        countryDistance.setDistance(FormatUtils.distanceStringToLong(trace.getEstimatedDistance()));
    }
    
    
    @Mapping(source = "trace.countryCode", target = "countryCode")
    public abstract DistanceEntity toEntity(ResponseTraceDTO trace);
}
