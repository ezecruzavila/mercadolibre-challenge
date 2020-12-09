/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.service;

import com.meli.challenge.entity.TraceEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transactional;
import com.meli.challenge.repository.TraceRepository;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Service("trace")
public class TraceServiceImpl implements ITraceService<TraceEntity, Long> {

    @Autowired
    TraceRepository dao;

    @Transactional
    public HashMap<String, Long>countTraceByCountryCode() {
        List<Object[]> counts =  dao.countCountryCodeGroupByCountryCode();
        HashMap<String, Long> tracesCountHashMap = new HashMap<>();
        counts.forEach(count -> {
            tracesCountHashMap.put((String) count[0], (Long) count[1]);
        });
        return tracesCountHashMap;
    }

    @Override
    public TraceEntity save(TraceEntity entity) {
        return dao.save(entity);
    }

    @Override
    public TraceEntity findById(Long id) {
        return dao.findById(id).get();
    }

    @Override
    public void delete(TraceEntity entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<TraceEntity> findAll() {
        return (List<TraceEntity>) dao.findAll();
    }

    public Long count() {
        return (Long) dao.count();
    }

}
