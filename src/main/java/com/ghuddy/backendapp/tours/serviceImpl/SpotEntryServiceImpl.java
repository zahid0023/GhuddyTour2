package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.spot.entry.SpotEntryPackageData;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.repository.SpotEntryPackageRepository;
import com.ghuddy.backendapp.tours.service.ActivityService;
import com.ghuddy.backendapp.tours.service.SpotEntryService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpotEntryServiceImpl implements SpotEntryService {
    private final ActivityService activityService;
    private final SpotEntryPackageRepository spotEntryPackageRepository;

    public SpotEntryServiceImpl(ActivityService activityService,
                                SpotEntryPackageRepository spotEntryPackageRepository) {
        this.activityService = activityService;
        this.spotEntryPackageRepository = spotEntryPackageRepository;
    }

    /**
     * @param subscribedTourEntity
     * @param spotEntryPackageRequest
     * @param requestId
     * @return
     */
    @Override
    public InsertAcknowledgeResponse addSpotEntryPackage(SubscribedTourEntity subscribedTourEntity, SpotEntryPackageRequest spotEntryPackageRequest, String requestId) {
        SpotEntryPackageEntity spotEntryPackageEntity = setTourSpotEntryPackages(subscribedTourEntity, List.of(spotEntryPackageRequest)).get(0);
        SpotEntryPackageData spotEntryPackageData = new SpotEntryPackageData(spotEntryPackageRepository.save(spotEntryPackageEntity));
        return new InsertAcknowledgeResponse(spotEntryPackageData, requestId);
    }

    /**
     * @param subscribedTourEntity
     * @param spotEntryPackageRequestList
     * @param requestId
     * @return
     */
    @Override
    public InsertAcknowledgeListResponse addSpotEntryPackages(SubscribedTourEntity subscribedTourEntity, List<SpotEntryPackageRequest> spotEntryPackageRequestList, String requestId) {
        List<SpotEntryPackageEntity> spotEntryPackageEntityList = setTourSpotEntryPackages(subscribedTourEntity, spotEntryPackageRequestList);
        List<SpotEntryPackageData> spotEntryPackageDataList = spotEntryPackageRepository.saveAll(spotEntryPackageEntityList).stream()
                .map(spotEntryPackageEntity -> new SpotEntryPackageData(spotEntryPackageEntity))
                .toList();
        return new InsertAcknowledgeListResponse(spotEntryPackageDataList, requestId);
    }

    /**
     * @param spotEntryPackageRequestList
     * @return
     */
    @Override
    public List<SpotEntryPackageEntity> setTourSpotEntryPackages(SubscribedTourEntity subscribedTourEntity, List<SpotEntryPackageRequest> spotEntryPackageRequestList) {
        Set<Long> activityIds = spotEntryPackageRequestList.stream()
                .map(spotEntryRequest -> spotEntryRequest.getActivityId())
                .collect(Collectors.toSet());

        Map<Long, ActivityEntity> activityEntityMap = activityService.getActivityEntityMapByIDs(activityIds);

        List<SpotEntryPackageEntity> spotEntryPackageEntityList = spotEntryPackageRequestList.stream()
                .map(spotEntryRequest -> {
                    SpotEntryPackageEntity spotEntryPackageEntity = new SpotEntryPackageEntity();
                    spotEntryPackageEntity.setSubscribedTourEntity(subscribedTourEntity);
                    spotEntryPackageEntity.setActivityEntity(activityEntityMap.get(spotEntryRequest.getActivityId()));
                    spotEntryPackageEntity.setPricePerPerson(spotEntryRequest.getPricePerPerson());
                    spotEntryPackageEntity.setRemark(spotEntryRequest.getRemark());
                    spotEntryPackageEntity.setActive(true);
                    return spotEntryPackageEntity;
                })
                .toList();
        return spotEntryPackageEntityList;
    }

    /**
     * @param spotEntryPackageIds
     * @return
     */
    @Override
    public Map<Long, SpotEntryPackageEntity> getSpotEntryPackageEntitiesById(Set<Long> spotEntryPackageIds) {
        return EntityUtil.findEntitiesByIds(spotEntryPackageIds, spotEntryPackageRepository, SpotEntryPackageEntity::getId, "SpotEntryPackageEntity");
    }

    @Override
    public List<SpotEntryPackageData> getAllSpotEntryPackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId) {
        return subscribedTourEntity.getSpotEntryPackageEntities().stream()
                .map(spotEntryPackageEntity -> new SpotEntryPackageData(spotEntryPackageEntity))
                .toList();
    }
}
