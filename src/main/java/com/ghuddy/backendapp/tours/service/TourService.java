package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.TourListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.TourData;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;

public interface TourService {
    InsertAcknowledgeResponse<TourData> createTour(TourCreateRequest tourCreateRequest) throws TourNotFoundException;

    TourEntity getCreatedTourEntityById(Long createdTourId) throws TourNotFoundException;

    TourData getCreatedTourByCreatedTourId(Long createdTourEntityId) throws TourNotFoundException;

    TourListResponse getAllCreatedTours(String requestId) throws EmptyListException;

    TourListResponse getAllCreatedToursPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;

}
