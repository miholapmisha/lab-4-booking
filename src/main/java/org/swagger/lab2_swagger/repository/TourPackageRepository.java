package org.swagger.lab2_swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.swagger.lab2_swagger.model.TourPackage;

public interface TourPackageRepository extends JpaRepository<TourPackage, Long>, CrudRepository<TourPackage, Long> {
}
