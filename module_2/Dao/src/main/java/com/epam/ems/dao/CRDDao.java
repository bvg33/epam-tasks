package com.epam.ems.dao;

//import com.epam.ems.exceptions.DaoException;

import com.epam.ems.exceptions.DaoException;

import java.util.List;

public interface CRDDao<T> {

    T getById(int id) throws DaoException;

    List<T> getAll();

    void insert(T item);

    void removeById(int id) ;

    List<T> getByTagName(String name);

    List<T> getEntitiesSortedByParameter(String sortType, String parameter);

    List<T> getByNamePart(String parameter);
}
