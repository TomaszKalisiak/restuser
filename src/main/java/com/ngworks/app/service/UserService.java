package com.ngworks.app.service;

import com.ngworks.app.dao.model.User;
import com.ngworks.app.dao.repositories.UserRepository;
import com.ngworks.app.security.Sha256PasswordHasher;
import com.ngworks.app.web.rest.dto.UserDTO;
import com.ngworks.app.web.rest.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> getAllUsers();

    Optional<User> findUserByName(String name);

    User save(String name, String password);
}
