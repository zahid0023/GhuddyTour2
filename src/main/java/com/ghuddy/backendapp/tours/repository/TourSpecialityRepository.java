package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.tour.TourSpecialityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourSpecialityRepository extends JpaRepository<TourSpecialityEntity, Long> {
}