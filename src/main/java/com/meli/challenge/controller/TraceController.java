/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.controller;

import com.meli.challenge.dto.RequestDTO;
import com.meli.challenge.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@RestController
@RequestMapping("trace")
public class TraceController {

    @Autowired
    TraceService traceService;

    @GetMapping("/stats/")
    ResponseEntity stats() {

        return ResponseEntity.ok("STATS");
    }

    @PostMapping()
    ResponseEntity trace(@RequestBody RequestDTO dto) {
        return ResponseEntity.ok(traceService.getTrace(dto.getIp()));
    }

}
