package com.ngworks.app.web.controller;

import com.ngworks.app.service.UserService;
import com.ngworks.app.web.representation.UserDTO;
import com.ngworks.app.web.representation.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/test")
    public String helloMapping() {
        return "Hello Tomasz Kalisiak.";
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDTO getUsers() {
        return userService.getAllUsers();
    }

    @PutMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO save(@RequestParam ("name") String name,
                        @RequestParam("password") String password) {
        return userService.save(name, password);
    }
}