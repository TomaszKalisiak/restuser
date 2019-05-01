package com.ngworks.app.web.controller;

import com.ngworks.app.service.CredentialStoreService;
import com.ngworks.app.web.representation.UserDTO;
import com.ngworks.app.web.representation.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private CredentialStoreService credentialStoreService;

    @Autowired
    public UserController(CredentialStoreService credentialStoreService) {
        this.credentialStoreService = credentialStoreService;
    }

    @GetMapping(path = "/test")
    public String helloMapping() {
        return "Hello Tomasz Kalisiak.";
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDTO getUsers() {
        return credentialStoreService.getAllUsers();
    }

    @PutMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO save(@RequestParam ("name") String name,
                        @RequestParam("password") String password) {
        return credentialStoreService.save(name, password);
    }
}