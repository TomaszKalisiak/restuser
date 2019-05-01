package com.ngworks.app.web.representation;

import java.util.ArrayList;
import java.util.List;

public class UsersDTO {

    private List<UserDTO> usersCredentials;

    public List<UserDTO> getUsersCredentials() {
        if (usersCredentials == null) {
            usersCredentials = new ArrayList<>();
        }
        return usersCredentials;
    }

    public void setUsersCredentials(List<UserDTO> usersCredentials) {
        this.usersCredentials = usersCredentials;
    }
}
