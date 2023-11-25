package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferPackageData;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationModeEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationProviderEntity;
import com.ghuddy.backendapp.tours.repository.TransferPackageRepository;
import com.ghuddy.backendapp.tours.service.TransferService;
import com.ghuddy.backendapp.tours.service.TransportationService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransferServiceImpl implements TransferService {
    private final TransportationService transportationService;
    private final TransferPackageRepository transferPackageRepository;

    public TransferServiceImpl(TransportationService transportationService,
                               TransferPackageRepository transferPackageRepository) {
        this.transportationService = transportationService;
        this.transferPackageRepository = transferPackageRepository;
    }

    /**
     * @param subscribedTourEntity
     * @param transferPackageRequest
     * @return
     */
    @Override
    public InsertAcknowledgeResponse addTransferPackage(SubscribedTourEntity subscribedTourEntity, TransferPackageRequest transferPackageRequest, String requestId) {
        TransferPackageEntity transferPackageEntity = setTourTransferPackages(subscribedTourEntity, List.of(transferPackageRequest)).get(0);
        TransferPackageData transferPackageData = new TransferPackageData(transferPackageRepository.save(transferPackageEntity));
        return new InsertAcknowledgeResponse<>(transferPackageData, requestId);
    }

    /**
     * @param subscribedTourEntity
     * @param transferPackageRequestList
     * @return
     */
    @Override
    public InsertAcknowledgeListResponse addTransferPackages(SubscribedTourEntity subscribedTourEntity, List<TransferPackageRequest> transferPackageRequestList, String requestId) {
        List<TransferPackageEntity> transferPackageEntityList = setTourTransferPackages(subscribedTourEntity, transferPackageRequestList);
        List<TransferPackageData> transferPackageDataList = transferPackageRepository.saveAll(transferPackageEntityList).stream()
                .map(transferPackageEntity -> new TransferPackageData(transferPackageEntity))
                .collect(Collectors.toList());
        return new InsertAcknowledgeListResponse<>(transferPackageDataList, requestId);
    }

    /**
     * @param subscribedTourEntity
     * @param transferPackageRequestList
     * @return
     */
    @Override
    public List<TransferPackageEntity> setTourTransferPackages(SubscribedTourEntity subscribedTourEntity, List<TransferPackageRequest> transferPackageRequestList) {
        Map<String, Set<Long>> idMaps = new HashMap<>();
        transferPackageRequestList.forEach((transferPackageRequest -> {
            idMaps.computeIfAbsent("mode", key -> new HashSet<>())
                    .add(transferPackageRequest.getTransferModeId());
            idMaps.computeIfAbsent("provider", key -> new HashSet<>())
                    .add(transferPackageRequest.getTransferProviderId());
        }));

        Map<Long, TransportationModeEntity> transportationModeEntityMap = transportationService.getTransportationModeEntitiesByIDs(idMaps.get("mode"));
        Map<Long, TransportationProviderEntity> transportationProviderEntityMap = transportationService.getTransportationProviderEntitiesByIDs(idMaps.get("provider"));

        List<TransferPackageEntity> transferPackageEntityList = transferPackageRequestList.stream()
                .map(transferPackageRequest -> {
                    TransferPackageEntity transferPackageEntity = new TransferPackageEntity();
                    transferPackageEntity.setSubscribedTourEntity(subscribedTourEntity);
                    transferPackageEntity.setTransportationModeEntity(transportationModeEntityMap.get(transferPackageRequest.getTransferModeId()));
                    transferPackageEntity.setTransportationProviderEntity(transportationProviderEntityMap.get(transferPackageRequest.getTransferProviderId()));
                    transferPackageEntity.setIsAc(transferPackageRequest.getIsAc());
                    transferPackageEntity.setSuitableForPersons(transferPackageRequest.getSuitableForPersons());
                    transferPackageEntity.setUnitPrice(transferPackageRequest.getTransferUnitPrice());
                    transferPackageEntity.setTransferRoute(transferPackageRequest.getTransferRoute());
                    transferPackageEntity.setTripType(transferPackageRequest.getTripType());
                    return transferPackageEntity;
                })
                .collect(Collectors.toList());
        return transferPackageEntityList;
    }

    /**
     * @param transferPackageIds
     * @return
     */
    @Override
    public Map<Long, TransferPackageEntity> getTransferPackageEntitiesById(Set<Long> transferPackageIds) {
        return EntityUtil.findEntitiesByIds(transferPackageIds, transferPackageRepository, TransferPackageEntity::getId, "TransferPackageEntity");
    }

    @Override
    public List<TransferPackageData> getAllTransferPackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId) {
        return subscribedTourEntity.getTransferPackageEntities().stream()
                .map(transferPackageEntity -> new TransferPackageData(transferPackageEntity))
                .toList();
    }
}
