package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.accommodation.TourRoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRoomTypeRepository extends JpaRepository<TourRoomTypeEntity, Long> {
}