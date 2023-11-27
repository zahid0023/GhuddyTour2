package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.AllComponentsOptionCombinationListResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageTypeListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageTypeEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TourPackageService {
    // tour package type
    InsertAcknowledgeResponse addTourPackageType(TourPackageTypeRequest tourPackageTypeRequest, String requestId);

    InsertAcknowledgeListResponse addTourPackageTypes(List<TourPackageTypeRequest> tourPackageTypeRequestList, String requestId);

    TourPackageTypeEntity getTourPackageTypeEntityByPackageTypeID(Long tourPackageTypeID);

    Map<Long, TourPackageTypeEntity> getTourPackageTypeEntitiesByPackageTypeIDs(Set<Long> tourPackageTypeIDs);

    TourPackageTypeListResponse getAllTourPackageTypes() throws EmptyListException;

    TourPackageTypeListResponse getAllTourPackageTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    AvailableTourPackageEntity getAvailableTourPackageEntityById(Long availableTourPackageId);

    AllComponentsOptionCombinationListResponse getAllOptionsCombinations(Long availableTourPackageId, String requestId);
}
