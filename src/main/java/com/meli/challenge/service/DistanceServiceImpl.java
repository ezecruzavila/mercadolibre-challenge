package com.meli.challenge.service;

import com.meli.challenge.entity.DistanceEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meli.challenge.repository.DistanceRepository;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
@Service("distance")
public class DistanceServiceImpl implements IDistanceService {

    @Autowired
    DistanceRepository repo;

    @Override
    public DistanceEntity save(DistanceEntity entity) {
        return repo.save(entity);
    }

    public DistanceEntity saveIfNotExists(DistanceEntity entity) {
        if (!repo.existsById(entity.getCountryCode())) {
            return repo.save(entity);
        }
        return null;
    }

    @Override
    public DistanceEntity findById(String id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(DistanceEntity entity) {
        repo.delete(entity);
    }

    @Override
    public void deleteById(String id) {
        repo.deleteById(id);
    }

    @Override
    public List<DistanceEntity> findAll() {
        return (List<DistanceEntity>) repo.findAll();
    }

}
