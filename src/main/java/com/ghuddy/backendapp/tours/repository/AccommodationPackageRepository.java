package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationPackageRepository extends JpaRepository<AccommodationPackageEntity, Long> {
}