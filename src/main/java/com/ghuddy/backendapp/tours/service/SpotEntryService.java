package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.spot.entry.SpotEntryPackageData;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SpotEntryService {
    InsertAcknowledgeResponse addSpotEntryPackage(SubscribedTourEntity subscribedTourEntity, SpotEntryPackageRequest spotEntryPackageRequest, String requestId);
    InsertAcknowledgeListResponse addSpotEntryPackages(SubscribedTourEntity subscribedTourEntity, List<SpotEntryPackageRequest> spotEntryPackageRequestList, String requestId);
    List<SpotEntryPackageEntity> setTourSpotEntryPackages(SubscribedTourEntity subscribedTourEntity, List<SpotEntryPackageRequest> spotEntryPackageRequestList);
    Map<Long, SpotEntryPackageEntity> getSpotEntryPackageEntitiesById(Set<Long> spotEntryPackageIds);
    List<SpotEntryPackageData> getAllSpotEntryPackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId);


}
