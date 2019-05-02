package com.ngworks.app.service.impl;

import com.ngworks.app.dao.model.User;
import com.ngworks.app.dao.repositories.UserRepository;
import com.ngworks.app.security.Sha256PasswordHasher;
import com.ngworks.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByName(String name) {
        return userRepository.findById(name);
    }

    public User save(String name, String password) {
        return userRepository.save(new User(name, Sha256PasswordHasher.hashPassword(name, password)));
    }
}
