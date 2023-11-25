package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.guide.GuidePackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuidePackageRepository extends JpaRepository<GuidePackageEntity, Long> {
}