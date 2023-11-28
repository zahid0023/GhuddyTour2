package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.es.dto.response.ESTourPackageResponse;
import com.ghuddy.backendapp.tours.es.model.data.ESTourPackageData;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourPackageDocument;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;

import java.util.List;
import java.util.Map;

public interface ESTourPackageService {
    Boolean indexAvailableTourPackages(TourEntity tourEntity, String requestId);
    ESTourPackageResponse getAvailableTourPackagesByTourId(Long tourId, String requestId);

}
