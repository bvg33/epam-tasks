package com.epam.ems.service.tag;

import com.epam.ems.dao.CRDDao;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.creator.Creator;
import com.epam.ems.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class TagServiceImpl extends AbstractService<Tag> {

    private CRDDao<Tag> dao;

    private static final String SORT_BY_NAME_PARAM = "sortByName";

    private Creator<Tag> creator;

    @Autowired
    public TagServiceImpl(CRDDao<Tag> dao, Creator<Tag> creator) {
        super(dao);
        this.dao = dao;
        this.creator = creator;
    }

    @Override
    public void insertIntoDB(MultiValueMap<String, String> allRequestParams) {
        Tag entity = creator.createObject(allRequestParams);
        dao.insert(entity);
    }

    @Override
    public List<Tag> doFilter(MultiValueMap<String, String> allRequestParams) {
        List<Tag> sortedByParam = getTagsSortedByNames(allRequestParams);
        List<Tag> foundByTagNames = findByTagNames(allRequestParams);
        List<Tag> foundByPartOfName = findByPartOfName(allRequestParams);
        List<Tag> foundedTags = new ArrayList<>();
        fillListIfEmpty(foundByPartOfName, foundByTagNames, sortedByParam);
        foundByTagNames.stream().filter(foundByPartOfName::contains).
                forEach(foundedTags::add);
        if (!sortedByParam.isEmpty()) {
            sortedByParam.retainAll(foundedTags);
            foundedTags = sortedByParam;
        }
        return foundedTags;
    }

    private List<Tag> getTagsSortedByNames(MultiValueMap<String, String> allRequestParams) {
        String sortType = getFilteredList(SORT_BY_NAME_PARAM, allRequestParams);
        List<Tag> sortedList = new ArrayList<>();
        if (!sortType.isEmpty()) {
            sortedList = dao.getEntitiesSortedByParameter(sortType, SORT_BY_NAME_PARAM);
        }
        return sortedList;
    }
}
