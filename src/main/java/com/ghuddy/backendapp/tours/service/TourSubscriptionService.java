package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourSubscriptionRequest;
import com.ghuddy.backendapp.tours.dto.response.tour.TourSubscriptionResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;

import javax.persistence.EntityNotFoundException;

public interface TourSubscriptionService {
    TourSubscriptionResponse subscribeTour(TourSubscriptionRequest tourSubscriptionRequest, String requestId) throws TourNotFoundException, EntityNotFoundException;

    SubscribedTourEntity getSubscribedTourEntityById(Long id) throws TourNotFoundException;
}
