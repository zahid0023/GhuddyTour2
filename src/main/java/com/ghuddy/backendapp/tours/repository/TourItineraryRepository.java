package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.tour.TourItineraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourItineraryRepository extends JpaRepository<TourItineraryEntity, Long> {
}