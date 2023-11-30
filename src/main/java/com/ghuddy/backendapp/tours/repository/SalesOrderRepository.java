package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.booking.SalesOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderRepository extends JpaRepository<SalesOrderEntity, Long> {
    SalesOrderEntity findByIdAndDeleted(Long id, boolean deleted);
    SalesOrderEntity findByOrderCodeAndDeleted(String orderCode, boolean deleted);
}