package com.ngworks.app.web.rest.controller;

import com.ngworks.app.dao.model.User;
import com.ngworks.app.service.UserService;
import com.ngworks.app.web.rest.dto.UserDTO;
import com.ngworks.app.web.rest.dto.UsersDTO;
import com.ngworks.app.web.rest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public UsersDTO getUsers() {
        return UsersDTO.from(userService.getAllUsers());
    }

    @GetMapping(path = "/users/{name}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        User user = userService.findUserByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for given name ::" + name));
        UserDTO userDTO = UserDTO.from(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping(path = "/users/{name}")
    public UserDTO save(@PathVariable ("name") String name,
                        @Valid @RequestBody String password) {
        User user = userService.save(name, password);
        return UserDTO.from(user);
    }

    @PutMapping(path = "/users/{name}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "name") String name,
                                              @Valid @RequestBody String password)
            throws ResourceNotFoundException {
        User user = userService.findUserByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for given name ::" + name));
        User updatedUser = userService.save(user.getName(), password);
        final UserDTO userDTO = UserDTO.from(updatedUser);
        return ResponseEntity.ok().body(userDTO);
    }
}