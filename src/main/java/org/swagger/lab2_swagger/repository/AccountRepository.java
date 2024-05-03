package org.swagger.lab2_swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.swagger.lab2_swagger.model.Account;
import org.swagger.lab2_swagger.model.User;

public interface AccountRepository extends JpaRepository<Account, Long>, CrudRepository<Account, Long> {

    Account findByUser(User user);

}
