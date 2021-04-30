package com.epam.ems.dao.tagdao;

import com.epam.ems.dao.CRDDao;
import com.epam.ems.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.epam.ems.dto.fields.Constants.*;

@Component
public class TagDaoImpl implements CRDDao<Tag> {

    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    @Autowired
    public TagDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager=entityManagerFactory.createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Tag getById(int id) {
        return entityManager.find(Tag.class, id);
    }

    public List<Tag> getByTagName(String name,int page,int elements) {
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> root = criteriaQuery.from(Tag.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get(TAG_NAME),name));
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page-1)*elements)
                .getResultList();
    }

    public List<Tag> getByNamePart(String name,int page,int elements) {
        name="%"+name+"%";
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> root = criteriaQuery.from(Tag.class);
        criteriaQuery.where(criteriaBuilder.like(root.get(TAG_NAME),name));
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page-1)*elements)
                .getResultList();
    }

    public List<Tag> getAll(int page,int elements) {
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> root = criteriaQuery.from(Tag.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult((page-1)*elements)
                .setMaxResults(elements)
                .getResultList();
    }

    @Transactional
    public void removeById(int id) {
        CriteriaDelete<Tag> criteriaDelete = criteriaBuilder.createCriteriaDelete(Tag.class);
        Root<Tag> root = criteriaDelete.from(Tag.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get(TAG_ID), id));
        entityManager.getTransaction().begin();
        entityManager.createQuery(criteriaDelete).executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void insert(Tag item){
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }

    public List<Tag> getEntitiesSortedByParameter(String sortType, String param,int page,int elements) {
        if (param == SORT_BY_NAME) {
            CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
            Root<Tag> root = criteriaQuery.from(Tag.class);
            chooseSortType(sortType,criteriaQuery,root);
            return entityManager.createQuery(criteriaQuery)
                    .setFirstResult((page-1)*elements)
                    .setMaxResults(elements)
                    .getResultList();
        }
        return new ArrayList<>();
    }

    private void chooseSortType(String sortType,CriteriaQuery<Tag> criteriaQuery,Root<Tag> root){
        if(sortType.equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(TAG_NAME)));
        }else if(sortType.equals("asc")){
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(TAG_NAME)));
        }
    }
}
