package com.ngworks.app.web.rest.controller;

import com.ngworks.app.dao.model.User;
import com.ngworks.app.service.UserService;
import com.ngworks.app.web.rest.dto.UserDTO;
import com.ngworks.app.web.rest.dto.UsersDTO;
import com.ngworks.app.web.rest.exception.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "View the list of all available name and hashed passwords", response = UsersDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of users"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON)
    public UsersDTO getUsers() {
        return UsersDTO.from(userService.getAllUsers());
    }

    @ApiOperation(value = "Get user by name.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "/users/{name}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<UserDTO> getUser(
            @ApiParam(value = "User name to search for.", required = true)
            @PathVariable(value = "name") @NotBlank
            @Size(max = 100, message = "name must not be greated then 100 characters")
                    String name)
            throws ResourceNotFoundException {

        User user = userService.findUserByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for given name :: " + name));
        UserDTO userDTO = UserDTO.from(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @ApiOperation(value = "Save the user and password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User saved successfuly"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(path = "/users/{name}", produces = MediaType.APPLICATION_JSON)
    public UserDTO save(
            @ApiParam(value = "User name to save.", required = true)
            @PathVariable("name") @NotBlank
            @Size(max = 100, message = "name must not be greated then 100 characters") String name,
            @ApiParam(value = "User password to hash and save.", required = true)
            @Valid @NotBlank(message = "password must be provided.") @RequestBody String password) {

        User user = userService.save(name, password);
        return UserDTO.from(user);
    }

    @ApiOperation(value = "Update the password for given user name.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the user."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping(path = "/users/{name}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<UserDTO> updateUser(
            @ApiParam(value = "User name to update.", required = true)
            @PathVariable(value = "name") @NotBlank
            @Size(max = 100, message = "name must not be greated then 100 characters") String name,
            @ApiParam(value = "User password to hash and update.", required = true)
            @Valid @NotBlank(message = "password must be provided.") @RequestBody String password)
            throws ResourceNotFoundException {

        User user = userService.findUserByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for given name :: " + name));
        User updatedUser = userService.save(user.getName(), password);
        final UserDTO userDTO = UserDTO.from(updatedUser);
        return ResponseEntity.ok().body(userDTO);
    }
}