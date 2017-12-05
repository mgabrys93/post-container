package com.example.service;

import java.util.List;

public interface DefaultService<T> {
    void save(T t);
    T findOne(Long id);
    List<T> findAll();
    void delete(Long id);
}
