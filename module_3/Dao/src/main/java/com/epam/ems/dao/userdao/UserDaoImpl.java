package com.epam.ems.dao.userdao;

import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

import static com.epam.ems.dto.fields.Constants.*;

@Repository
public class UserDaoImpl {

    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    @Autowired
    private void UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<User> getAll(int page, int elements) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page - 1) * elements)
                .getResultList();
    }

    @Transactional
    public void updateUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<Certificate> getUserCertificatesById(int id, int page, int elements) {
        CriteriaQuery<Certificate> criteriaQuery = criteriaBuilder.createQuery(Certificate.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot.get(CERTIFICATES)).where(criteriaBuilder.equal(userRoot.get(USER_ID), id));
        return entityManager
                .createQuery(criteriaQuery)
                .setFirstResult((page - 1) * elements)
                .setMaxResults(elements)
                .getResultList();
    }

    public Tag getTheMostWidelyUsedTag() {
        int userId = findUserWithMaxOrderPrice();
        CriteriaQuery<Tag> tagCriteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<User> userRoot = tagCriteriaQuery.from(User.class);
        tagCriteriaQuery.select(userRoot.join(CERTIFICATES).join(TAGS));
        tagCriteriaQuery.where(criteriaBuilder.equal(userRoot.get(USER_ID), userId));
        tagCriteriaQuery.groupBy(userRoot.join(CERTIFICATES).join(TAGS).get(TAG_NAME));
        tagCriteriaQuery.orderBy(criteriaBuilder.asc(criteriaBuilder.count(userRoot.join(CERTIFICATES).join(TAGS).get(TAG_NAME))));
        return entityManager.createQuery(tagCriteriaQuery).getSingleResult();

    }

    private int findUserWithMaxOrderPrice() {
        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(criteriaBuilder.max(userRoot.get(OVERAGE_ORDER_PRICE)));
        int maxOrderPrice = entityManager.createQuery(criteriaQuery).getSingleResult();
        return findUserIdByMaxOrderPrice(maxOrderPrice);
    }

    private int findUserIdByMaxOrderPrice(int maxOrderPrice) {
        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot.get(USER_ID));
        criteriaQuery.where(criteriaBuilder.equal(userRoot.get(OVERAGE_ORDER_PRICE), maxOrderPrice));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
