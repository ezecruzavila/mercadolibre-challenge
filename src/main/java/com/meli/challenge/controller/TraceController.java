/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.controller;

import com.meli.challenge.dto.RequestDTO;
import com.meli.challenge.dto.ResponseStatsDTO;
import com.meli.challenge.dto.ResponseTraceDTO;
import com.meli.challenge.entity.DistanceEntity;
import com.meli.challenge.entity.TraceEntity;
import com.meli.challenge.mapper.DistanceMapper;
import com.meli.challenge.mapper.ResponseTraceMapper;
import com.meli.challenge.service.DistanceServiceImpl;
import com.meli.challenge.service.ExternalAPIService;
import com.meli.challenge.service.StatsService;
import com.meli.challenge.service.TraceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RestController
@RequestMapping("trace")
@Api(value = "Trace Controller", description = "Operaciones pertinences al tracing de IPs maliciosas. Utiliza APIs externas para obtener la informacion")
public class TraceController {

    @Autowired
    TraceServiceImpl traceService;

    @Autowired
    StatsService statsService;

    @Autowired
    ExternalAPIService externalService;

    @Autowired
    DistanceServiceImpl distanceServiceImpl;

    @Autowired
    ResponseTraceMapper responseTraceMapper;

    @Autowired
    DistanceMapper distanceMapper;

    @ApiOperation(value = "Obtiene información de la IP invocando APIs externas. Retorna la informacion y luego persiste ",
            response = ResponseTraceDTO.class,
            produces = "application/json")
    @ApiResponse(code = 200,
            message = "Informacion solicitada sobre la IP",
            response = ResponseTraceDTO.class)

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity save(@RequestBody @ApiParam(value = "IP que se desea tracear",name = "ip") RequestDTO requestDTO) {
        ResponseTraceDTO response = externalService.getDataFromIP(requestDTO.getIp());
        TraceEntity trace = new TraceEntity(response.getIp(), response.getCountryCode());
        traceService.save(trace);
        DistanceEntity distance = distanceMapper.toEntity(response);
        distanceServiceImpl.saveIfNotExists(distance);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Estadisticas sobre las IPs traceadas. Retornando distancia máxima, mínima y promedio de las invocaciones.",
            response = ResponseStatsDTO.class)
    @ApiResponse(code = 201,
            message = "Estadísticas basicas sobre las IPs",
            response = ResponseStatsDTO.class)
    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity stats() {
        ResponseStatsDTO dto = statsService.getDistanceStats();
        return new ResponseEntity(dto, HttpStatus.OK);
    }

}
