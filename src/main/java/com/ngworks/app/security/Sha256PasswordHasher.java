package com.ngworks.app.security;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;

public class Sha256PasswordHasher {

    public static String hashPassword(String name, String password) throws IllegalArgumentException {
        if (name == null || password == null || name.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Name and/or password null/empty.");
        }
        return DigestUtils.sha256Hex(name + password);
    }
}
