package com.epam.ems.logic.creator;

import org.springframework.util.MultiValueMap;

public interface Renovator <T>{
    T updateObject(T newEntity,T oldEntity);
}
