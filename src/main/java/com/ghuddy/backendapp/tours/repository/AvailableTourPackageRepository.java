package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableTourPackageRepository extends JpaRepository<AvailableTourPackageEntity, Long> {
}