package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportationBrandRepository extends JpaRepository<TransportationBrandEntity, Long> {
}