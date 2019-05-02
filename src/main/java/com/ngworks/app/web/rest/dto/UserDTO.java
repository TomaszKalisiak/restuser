package com.ngworks.app.web.rest.dto;

import com.ngworks.app.dao.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String hashedPassword;

    public final static UserDTO from(User user) {
        return new UserDTO(user.getName(), user.getHashedPassword());
    }

    @Override
    public String toString() {
        return String.format("UserDTO [ name=%s , hashedPassword=%s ]", name, hashedPassword);
    }
}
