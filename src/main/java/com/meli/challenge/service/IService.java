package com.meli.challenge.service;

import java.util.List;

/**
 *
 * @author Ezequiel Cruz Avila <ezecruzavila@gmail.com>
 */
public interface IService<T, K> {

    List<T> findAll();

    T save(T entity);

    T findById(K id);

    void delete(T entity);

    void deleteById(K id);

}
