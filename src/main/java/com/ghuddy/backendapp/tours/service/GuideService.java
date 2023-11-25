package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.guide.GuidePackageRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.guide.GuidePackageData;
import com.ghuddy.backendapp.tours.model.entities.guide.GuidePackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GuideService {
    InsertAcknowledgeResponse addGuidePackage(SubscribedTourEntity subscribedTourEntity, GuidePackageRequest guidePackageRequest, String requestId);
    InsertAcknowledgeListResponse addGuidePackages(SubscribedTourEntity subscribedTourEntity, List<GuidePackageRequest> guidePackageRequestList, String requestId);
    List<GuidePackageEntity> setTourGuidePackages(SubscribedTourEntity subscribedTourEntity, List<GuidePackageRequest> guidePackageRequestList);
    Map<Long, GuidePackageEntity> getGuidePackageEntitiesById(Set<Long> guidePackageIds);
    List<GuidePackageData> getAllGuidePackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId);

}
