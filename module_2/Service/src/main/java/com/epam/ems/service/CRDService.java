package com.epam.ems.service;

import org.springframework.util.MultiValueMap;

import java.util.List;

public interface CRDService<T> {

    List<T> getAll();

    void insertIntoDB(MultiValueMap<String, String> allRequestParams);

    T getById(int id);

    void deleteById(int id) ;

    List<T> doFilter(MultiValueMap<String, String> allRequestParams);
}
