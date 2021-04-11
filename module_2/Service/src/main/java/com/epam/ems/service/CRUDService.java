package com.epam.ems.service;

import org.springframework.util.MultiValueMap;

public interface CRUDService<T> extends CRDService<T>{

    void update(MultiValueMap<String, String> allRequestParams);
}
