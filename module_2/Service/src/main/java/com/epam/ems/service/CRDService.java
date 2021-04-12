package com.epam.ems.service;

import com.epam.ems.exceptions.DaoException;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface CRDService<T> {

    List<T> getAll();

    void insertIntoDB(MultiValueMap<String, String> allRequestParams);

    T getById(int id) throws DaoException;

    void deleteById(int id) ;

    List<T> doFilter(MultiValueMap<String, String> allRequestParams);
}
