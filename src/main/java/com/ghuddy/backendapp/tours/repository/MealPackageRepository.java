package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPackageRepository extends JpaRepository<MealPackageEntity, Long> {
}