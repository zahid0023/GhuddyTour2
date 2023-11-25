package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourSpecialityRequest;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourSpecialityEntity;

import java.util.List;

public interface SpecialityService {
    List<TourSpecialityEntity> setTourSpecialities(TourEntity tourEntity, List<TourSpecialityRequest> specialities);
}
