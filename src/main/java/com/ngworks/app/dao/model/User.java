package com.ngworks.app.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "hashed_password", length = 64)
    private String hashedPassword;

    @Override
    public String toString() {
        return String.format("User [ name=%s , hashedPassword=%s ]", name, hashedPassword);
    }
}
