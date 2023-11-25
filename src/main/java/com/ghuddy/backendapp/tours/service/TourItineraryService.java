package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourItineraryEntity;

import java.util.List;
import java.util.Set;

public interface TourItineraryService {
    List<TourItineraryEntity> setTourActivities(TourEntity tourEntity, Set<Long> tourActivityIds);
}
