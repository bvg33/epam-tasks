package com.epam.ems.logic.service.user;

import com.epam.ems.dao.certificatedao.CertificateDaoImpl;
import com.epam.ems.dao.orderdao.OrderDaoImpl;
import com.epam.ems.dao.tagdao.TagDaoImpl;
import com.epam.ems.dao.userdao.UserDaoImpl;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.dto.User;
import com.epam.ems.dto.UserOrderInfo;
import com.epam.ems.logic.creator.CertificateRenovator;
import com.epam.ems.logic.handler.DateHandler;
import com.epam.ems.logic.serviceconfig.HSQLDBConfig;
import com.epam.ems.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserService.class, CertificateDaoImpl.class, UserDaoImpl.class,
        OrderDaoImpl.class, TagDaoImpl.class, HSQLDBConfig.class, CertificateRenovator.class, DateHandler.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class UserServiceTest {

    private final List<User> users = asList(new User("dima", 5,
                    asList(new Certificate("sad", "dsf", 4, 5,
                            "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000",
                            asList(new Tag("tag1"), new Tag("tag2")))), 10),
            new User("vasya", 5, asList(new Certificate("name1", "description", 5, 5,
                    "2021-04-03 21:07:00", "2021-04-04 20:46:00", asList(new Tag("tag2")))), 10));

    @Autowired
    private UserService service;

    @Test
    public void testGetAllUsers() {
        List<User> actual = service.getAllUsers(1, 2);
        List<User> expected = new ArrayList<>(users);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserCertificates() {
        List<Certificate> actual = service.getUserCertificates(1, 1, 4);
        List<Certificate> expected = users.get(0).getCertificates();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserOrdersInfo() {
        List<UserOrderInfo> actual = service.getUserOrdersInfo(1,1,5);
        List<UserOrderInfo> expected = asList(new UserOrderInfo(1, 1, 5, "2021-11-10 00:00:00"));
        assertEquals(expected,actual);
    }
}