package org.swagger.lab2_swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.swagger.lab2_swagger.model.User;

public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    User findByUsername(String username);
}
