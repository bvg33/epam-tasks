package com.epam.ems.service;

import com.epam.ems.dao.CRDDao;
import com.epam.ems.logic.creator.Creator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T> implements CRDService<T> {

    @Autowired
    private Creator<T> creator;

    private static final String GET_BY_NAME_PART = "getByNamePart";

    private static final String GET_BY_TAG_NAME = "getByTagName";

    private final CRDDao<T> dao;

    public AbstractService(CRDDao<T> dao) {
        this.dao = dao;
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

    @Override
    public T getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void deleteById(int id)  {
        dao.removeById(id);
    }


    protected String getFilteredList(String paramName, MultiValueMap<String, String> allRequestParams) {
        String first = new String();
        if (allRequestParams.containsKey(paramName)) {
            first = allRequestParams.getFirst(paramName);
        }
        return first;
    }

    protected List<T> findByPartOfName(MultiValueMap<String, String> allRequestParams) {
        String parameter = getFilteredList(GET_BY_NAME_PART, allRequestParams);
        List<T> entities = new ArrayList<>();
        if (!parameter.isEmpty()) {
            entities = dao.getByNamePart(parameter);
        }
        return entities;
    }

    protected List<T> findByTagNames(MultiValueMap<String, String> allRequestParams) {
        String parameter = getFilteredList(GET_BY_TAG_NAME, allRequestParams);
        List<T> entities = new ArrayList<>();
        if (!parameter.isEmpty()) {
            entities = dao.getByTagName(parameter);
        }
        return entities;
    }

    protected void fillListIfEmpty(List<T> foundByPartOfName, List<T> foundByTagNames, List<T> sortedByParam) {
        if (foundByTagNames.isEmpty() && !foundByPartOfName.isEmpty()) {
            foundByTagNames.addAll(foundByPartOfName);
        } else if (foundByPartOfName.isEmpty() && !foundByTagNames.isEmpty()) {
            foundByPartOfName.addAll(foundByTagNames);
        } else if (foundByPartOfName.isEmpty() && foundByTagNames.isEmpty()) {
            foundByPartOfName.addAll(sortedByParam);
            foundByTagNames.addAll(sortedByParam);
        }
    }


}
