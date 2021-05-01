package com.epam.ems.logic.service.user;

import com.epam.ems.dao.userdao.UserDaoImpl;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.User;
import com.epam.ems.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
public class UserServiceUnitTest {
    private List<Certificate> certificates = Arrays.asList(new Certificate("name1", "desc1", 5, 6, new String(), new String(), new ArrayList<>()),
            new Certificate("name2", "desc2", 3, 4, new String(), new String(), new ArrayList<>()),
            new Certificate("name3", "desc3", 1, 2, new String(), new String(), new ArrayList<>()));

    private List<User> users = Arrays.asList(new User("name", 10, certificates, 5),
            new User("name1", 5, certificates, 2));

    @Mock
    private UserDaoImpl dao = Mockito.mock(UserDaoImpl.class);

    @InjectMocks
    private UserService service;

    @Test
    public void testGetAllUsers() {
        when(dao.getAll(anyInt(), anyInt())).thenReturn(users);
        List<User> actual = service.getAllUsers(1, 2);
        List<User> expected = new ArrayList<>(users);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserCertificates() {
        when(dao.getUserCertificatesById(anyInt(), anyInt(), anyInt())).thenReturn(certificates);
        List<Certificate> actual = service.getUserCertificates(1, 1, 4);
        List<Certificate> expected = new ArrayList<>(certificates);
        assertEquals(expected, actual);
    }
}
