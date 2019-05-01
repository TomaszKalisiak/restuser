package com.ngworks.app.service;

import com.ngworks.app.dao.model.User;
import com.ngworks.app.dao.repositories.UserRepository;
import com.ngworks.app.security.Sha256PasswordHasher;
import com.ngworks.app.web.representation.UserDTO;
import com.ngworks.app.web.representation.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CredentialStoreService {

    private static Logger LOG = LoggerFactory.getLogger(CredentialStoreService.class);

    private UserRepository userRepository;

    @Autowired
    public CredentialStoreService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersDTO getAllUsers() {
        LOG.debug("Getting all users.");
        List<User> users = userRepository.findAll();
        return UserMapper.mapUsers(users);
    }

    public UserDTO save(String name, String password) {
        LOG.debug(String.format("Saving name/hashedPassword [name=%s, hashedPassword=%s]", name, password));
        User user = userRepository.save(new User(name, Sha256PasswordHasher.hashPassword(name, password)));
        return new UserDTO(user.getName(), user.getHashedPassword());
    }
}
