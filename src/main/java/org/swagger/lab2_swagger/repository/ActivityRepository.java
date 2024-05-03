package org.swagger.lab2_swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.swagger.lab2_swagger.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long>, CrudRepository<Activity, Long> {
}
