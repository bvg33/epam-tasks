package com.epam.ems.service.certificate;

import com.epam.ems.dao.CRDDao;
import com.epam.ems.dao.CRUDDao;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.creator.Creator;
import com.epam.ems.service.AbstractService;
import com.epam.ems.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static com.epam.ems.dto.SortParameters.SORT_BY_DATE;
import static com.epam.ems.dto.SortParameters.SORT_BY_NAME;

@Service
public class CertificateService extends AbstractService<Certificate> implements CRUDService<Certificate> {

    private CRUDDao<Certificate> dao;

    @Autowired
    private CRDDao<Tag> tagDao;

    @Autowired
    private Creator<Certificate> creator;

    @Autowired
    public CertificateService(CRUDDao<Certificate> dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public void insertIntoDB(MultiValueMap<String, String> allRequestParams) {
        Certificate entity = creator.createObject(allRequestParams);
        List<Tag> requestTags =entity.getTags();
        List<Tag> createdTags =tagDao.getAll();
        saveNewTags(requestTags,createdTags);
        dao.insert(entity);
    }

    @Override
    public void update(MultiValueMap<String, String> allRequestParams) {
        Certificate certificate = creator.createObject(allRequestParams);
        List<Tag> requestTags = certificate.getTags();
        List<Tag> createdTags = tagDao.getAll();
        saveNewTags(requestTags, createdTags);
        dao.update(certificate);
    }

    @Override
    public List<Certificate> doFilter(MultiValueMap<String, String> allRequestParams) {
        List<Certificate> sortedByParam = getListSortedByParameter(allRequestParams);
        List<Certificate> foundByTagNames = findByTagNames(allRequestParams);
        List<Certificate> foundByPartOfName = findByPartOfName(allRequestParams);
        List<Certificate> foundedCertificates = new ArrayList<>();
        fillListIfEmpty(foundByPartOfName, foundByTagNames, sortedByParam);
        foundByTagNames.stream().filter(foundByPartOfName::contains).
                forEach(foundedCertificates::add);
        if (!sortedByParam.isEmpty()) {
            sortedByParam.retainAll(foundedCertificates);
            foundedCertificates = sortedByParam;
        }
        return foundedCertificates;
    }

    private void saveNewTags(List<Tag> requestTags, List<Tag> createdTags) {
        if (requestTags != null) {
            requestTags.stream().filter(tag -> !createdTags.contains(tag)).forEach(tag -> tagDao.insert(tag));
        }
    }

    private List<Certificate> getListSortedByParameter(MultiValueMap<String, String> allRequestParams) {
        String sortType;
        List<Certificate> sortedList = new ArrayList<>();
        if (allRequestParams.containsKey(SORT_BY_NAME)) {
            sortType = getFilteredList(SORT_BY_NAME, allRequestParams);
            sortedList = dao.getEntitiesSortedByParameter(sortType, SORT_BY_NAME);
        } else if (allRequestParams.containsKey(SORT_BY_DATE)) {
            sortType = getFilteredList(SORT_BY_DATE, allRequestParams);
            sortedList = dao.getEntitiesSortedByParameter(sortType, SORT_BY_DATE);
        }
        return sortedList;
    }
}
