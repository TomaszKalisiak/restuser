package com.ngworks.app.web.rest.dto;

import com.ngworks.app.dao.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "Name and hashed password for a user")
public class UserDTO {

    @ApiModelProperty(notes = "Name of a user which is also the id.")
    private String name;

    @ApiModelProperty(notes = "Hashed user password.")
    private String hashedPassword;

    public final static UserDTO from(User user) {
        return new UserDTO(user.getName(), user.getHashedPassword());
    }

    @Override
    public String toString() {
        return String.format("UserDTO [ name=%s , hashedPassword=%s ]", name, hashedPassword);
    }
}
