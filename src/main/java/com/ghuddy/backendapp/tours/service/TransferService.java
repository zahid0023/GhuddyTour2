package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferPackageData;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TransferService {
    InsertAcknowledgeResponse addTransferPackage(SubscribedTourEntity subscribedTourEntity, TransferPackageRequest transferPackageRequest, String requestId);

    InsertAcknowledgeListResponse addTransferPackages(SubscribedTourEntity subscribedTourEntity, List<TransferPackageRequest> transferPackageRequestList, String requestId);

    List<TransferPackageEntity> setTourTransferPackages(SubscribedTourEntity subscribedTourEntity, List<TransferPackageRequest> transferPackageRequestList);

    Map<Long, TransferPackageEntity> getTransferPackageEntitiesById(Set<Long> transferPackageIds);
    List<TransferPackageData> getAllTransferPackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId);

}
