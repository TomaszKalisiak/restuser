package com.ngworks.app.dao.repositories;

import com.ngworks.app.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
