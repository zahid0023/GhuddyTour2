package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotEntryPackageRepository extends JpaRepository<SpotEntryPackageEntity, Long> {
}