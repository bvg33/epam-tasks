package com.epam.ems.logic.creator;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public interface Creator<T> {
    T createObject(MultiValueMap<String, String> allRequestParams);
}
