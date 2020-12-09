/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meli.challenge.repository;

import com.meli.challenge.entity.TraceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Repository
public interface TraceRepository extends CrudRepository<TraceEntity, Long>, TraceRepositoryCustom{

    @Override
    @Query("SELECT t.countryCode, COUNT(*) FROM TraceEntity t GROUP BY t.countryCode")
    public List<Object[]> countCountryCodeGroupByCountryCode();
}
