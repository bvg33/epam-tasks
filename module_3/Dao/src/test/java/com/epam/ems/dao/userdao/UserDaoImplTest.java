package com.epam.ems.dao.userdao;

import com.epam.ems.dao.config.HSQLDBConfig;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.dto.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserDaoImpl.class, HSQLDBConfig.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class UserDaoImplTest {

    @Autowired
    private UserDaoImpl dao;
    private final List<User> users = asList(new User("dima", 5,
            asList(new Certificate("sad", "dsf", 4, 5,
                    "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000",
                    asList(new Tag("tag1"), new Tag("tag2")))), 10),
            new User("vasya",5,asList(new Certificate("name1","description",5,5,
                    "2021-04-03 21:07:00","2021-04-04 20:46:00",asList(new Tag("tag2")))),10));

    @Test
    public void testGetAll() {
        List<User> actual = dao.getAll(1, 5);
        assertEquals(users, actual);
    }

    @Test
    public void testGetById() {
        User actual = dao.getById(1);
        assertEquals(users.get(0), actual);
    }

    @Test
    public void testGetUserCertificatesById() {
        List<Certificate> actual = dao.getUserCertificatesById(1, 1, 5);
        List<Certificate> expected = asList(new Certificate("sad", "dsf", 4, 5,
                "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000",
                asList(new Tag("tag1"), new Tag("tag2"))));
        assertEquals(expected, actual);
    }
}
