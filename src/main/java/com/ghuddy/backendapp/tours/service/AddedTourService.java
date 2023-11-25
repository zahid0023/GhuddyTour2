package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.AddedTourListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourDataOptimized;
import com.ghuddy.backendapp.tours.model.entities.tour.AddedTourEntity;

public interface AddedTourService {
    InsertAcknowledgeResponse<AddedTourDataOptimized> addTour(TourAddRequest tourAddRequest) throws LocationNotFoundException;

    AddedTourEntity getAddedTourEntityById(Long addedTourEntityId) throws TourNotFoundException;

    AddedTourDataOptimized getAddedTourByAddedTourId(Long addedTourId) throws TourNotFoundException;

    AddedTourListResponse getAllAddedTours(String requestId) throws EmptyListException;

    AddedTourListResponse getAllAddedToursPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;
}
