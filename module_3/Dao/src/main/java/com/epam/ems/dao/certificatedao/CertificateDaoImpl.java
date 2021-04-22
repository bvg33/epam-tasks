package com.epam.ems.dao.certificatedao;

import com.epam.ems.dao.CRDDao;
import com.epam.ems.dao.CRUDDao;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import static com.epam.ems.dto.fields.Constants.*;


@Repository
public class CertificateDaoImpl implements CRUDDao<Certificate> {

    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    @Autowired
    private CRDDao<Tag> tagDao;

    @Autowired
    public CertificateDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


    public List<Certificate> getAll(int page, int elements) {
        CriteriaQuery<Certificate> criteriaQuery = criteriaBuilder.createQuery(Certificate.class);
        Root<Certificate> root = criteriaQuery.from(Certificate.class);
        criteriaQuery.select(root);
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page - 1) * elements)
                .getResultList();
    }

    public Certificate getById(int id) {
        return entityManager.find(Certificate.class, id);
    }

    @Transactional
    public void removeById(int id) {
        CriteriaDelete<Certificate> criteriaDelete = criteriaBuilder.createCriteriaDelete(Certificate.class);
        Root<Certificate> root = criteriaDelete.from(Certificate.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        entityManager.getTransaction().begin();
        entityManager.createQuery(criteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void update(Certificate item) {
        entityManager.getTransaction().begin();
        entityManager.merge(item);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void insert(Certificate item) {
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }

    public List<Certificate> getByNamePart(String name, int page, int elements) {
        name = "%" + name + "%";
        CriteriaQuery<Certificate> criteriaQuery = criteriaBuilder.createQuery(Certificate.class);
        Root<Certificate> root = criteriaQuery.from(Certificate.class);
        criteriaQuery.where(criteriaBuilder.like(root.get(CERTIFICATE_NAME), name));
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page - 1) * elements)
                .getResultList();
    }

    public List<Certificate> getByTagName(String name, int page, int elements) {
        CriteriaQuery<Certificate> criteriaQuery = criteriaBuilder.createQuery(Certificate.class);
        Root<Certificate> root = criteriaQuery.from(Certificate.class);
        Expression<Collection<Tag>> tags = root.get("tags");
        Tag tag = tagDao.getByTagName(name, 1, 5).get(0);
        Predicate containsTag = criteriaBuilder.isMember(tag, tags);
        criteriaQuery.where(containsTag);
        return entityManager
                .createQuery(criteriaQuery)
                .setFirstResult((page - 1) * elements)
                .setMaxResults(elements)
                .getResultList();
    }

    public List<Certificate> getEntitiesSortedByParameter(String sortType, String param, int page, int elements) {
        CriteriaQuery<Certificate> criteriaQuery = criteriaBuilder.createQuery(Certificate.class);
        Root<Certificate> root = criteriaQuery.from(Certificate.class);
        if (param == SORT_BY_NAME) {
            chooseSortType(sortType, criteriaQuery, root, CERTIFICATE_NAME);
        }
        if (param == SORT_BY_DATE) {
            chooseSortType(sortType, criteriaQuery, root, "createDate");
        }
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page - 1) * elements)
                .getResultList();
    }

    private void chooseSortType(String sortType, CriteriaQuery<Certificate> criteriaQuery,
                                Root<Certificate> root, String field) {
        if (sortType.equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(field)));
        } else if (sortType.equals("asc")) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(field)));
        }
    }
}
