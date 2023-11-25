package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.accommodation.TourRoomCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRoomCategoryRepository extends JpaRepository<TourRoomCategoryEntity, Long> {
}