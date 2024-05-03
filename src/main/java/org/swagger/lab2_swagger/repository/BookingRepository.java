package org.swagger.lab2_swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.swagger.lab2_swagger.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>, CrudRepository<Booking, Long> {
}
