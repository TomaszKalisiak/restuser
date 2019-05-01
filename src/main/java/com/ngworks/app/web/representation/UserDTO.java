package com.ngworks.app.web.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String hashedPassword;

    @Override
    public String toString() {
        return String.format("UserDTO [ name=%s , hashedPassword=%s ]", name, hashedPassword);
    }
}
