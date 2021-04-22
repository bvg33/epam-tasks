package com.epam.ems.dao.orderdao;

import com.epam.ems.dto.UserOrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderDaoImpl {

    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    @Autowired
    public OrderDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<UserOrderInfo> getUserCertificatesInfo(int userId, int page, int elements) {
        CriteriaQuery<UserOrderInfo> criteriaQuery = criteriaBuilder.createQuery(UserOrderInfo.class);
        Root<UserOrderInfo> root = criteriaQuery.from(UserOrderInfo.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("userId"), userId));
        return entityManager
                .createQuery(criteriaQuery)
                .setMaxResults(elements)
                .setFirstResult((page - 1) * elements)
                .getResultList();
    }

    @Transactional
    public void addCertificateToUser(UserOrderInfo orderInfo) {
        entityManager.getTransaction().begin();
        entityManager.persist(orderInfo);
        entityManager.getTransaction().commit();

    }
}
