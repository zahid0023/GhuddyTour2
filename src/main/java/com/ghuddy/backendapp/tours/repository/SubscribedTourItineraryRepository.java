package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourItineraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribedTourItineraryRepository extends JpaRepository<SubscribedTourItineraryEntity, Long> {
}