package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.availability.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;

public interface TourPackageAvailabilityService {
    InsertAcknowledgeResponse generateTourPackageAvailabilityOptions(SubscribedTourEntity subscribedTourEntity, TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest);
    //TourPackageAllComponentListResponse getAllComponentOptionsByTourPackage(TourPackageEntity tourPackageEntity, String requestId);

}
