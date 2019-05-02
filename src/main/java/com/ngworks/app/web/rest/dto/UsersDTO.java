package com.ngworks.app.web.rest.dto;

import com.ngworks.app.dao.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDTO {

    private List<UserDTO> users;

    public List<UserDTO> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public final static UsersDTO from(List<User> users) {
        if (users == null || users.isEmpty()) {
            return new UsersDTO();
        }
        UsersDTO usersDtoRepresentation = new UsersDTO();
        for (User user : users) {
            usersDtoRepresentation.getUsers()
                    .add(new UserDTO(user.getName(), user.getHashedPassword()));
        }
        return usersDtoRepresentation;

    }
}
