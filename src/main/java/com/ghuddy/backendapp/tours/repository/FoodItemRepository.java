package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.food.FoodItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItemEntity, Long> {
}