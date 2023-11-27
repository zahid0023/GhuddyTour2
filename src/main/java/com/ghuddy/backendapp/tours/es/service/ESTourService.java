package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.es.dto.response.ESTourResponse;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourDocument;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;

public interface ESTourService {
    Boolean indexTourByTourId(Long createdTourId, String requestId) throws TourNotFoundException;

    ESTourResponse getTour(Long tourId, String requestId) throws TourNotFoundException;

    ESTourDocument getTourById(Long tourId, String requestId) throws TourNotFoundException;


}
