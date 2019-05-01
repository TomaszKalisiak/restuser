package com.ngworks.app.service;

import com.ngworks.app.dao.model.User;
import com.ngworks.app.web.representation.UserDTO;
import com.ngworks.app.web.representation.UsersDTO;

import java.util.List;

public class UserMapper {

    public static User mapUser(UserDTO userDto) {
        return new User(userDto.getName(), userDto.getHashedPassword());
    }

    public static UsersDTO mapUsers(List<User> users) {
        if (users == null || users.isEmpty()) {
            return new UsersDTO();
        }
        UsersDTO usersDtoRepresentation = new UsersDTO();
        for (User user : users) {
            usersDtoRepresentation.getUsersCredentials()
                    .add(new UserDTO(user.getName(), user.getHashedPassword()));
        }
        return usersDtoRepresentation;
    }
}
