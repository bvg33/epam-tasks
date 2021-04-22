package com.epam.ems.dto.lists;

import com.epam.ems.dto.User;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class UserList extends RepresentationModel<UserList> {
    private List<User> users;

    public UserList() {
    }

    public UserList(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
