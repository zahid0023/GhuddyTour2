package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.guide.GuidePackageRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.guide.GuidePackageData;
import com.ghuddy.backendapp.tours.model.entities.guide.GuidePackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.repository.GuidePackageRepository;
import com.ghuddy.backendapp.tours.service.GuideService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GuideServiceImpl implements GuideService {
    private final GuidePackageRepository guidePackageRepository;

    public GuideServiceImpl(GuidePackageRepository guidePackageRepository) {
        this.guidePackageRepository = guidePackageRepository;
    }

    @Transactional
    @Override
    public InsertAcknowledgeResponse addGuidePackage(SubscribedTourEntity subscribedTourEntity, GuidePackageRequest guidePackageRequest, String requestId) {
        GuidePackageEntity guidePackageEntity = setTourGuidePackages(subscribedTourEntity, List.of(guidePackageRequest)).get(0);
        GuidePackageData guidePackageData = new GuidePackageData(guidePackageRepository.save(guidePackageEntity));
        return new InsertAcknowledgeResponse(guidePackageData, requestId);
    }

    @Transactional
    @Override
    public InsertAcknowledgeListResponse addGuidePackages(SubscribedTourEntity subscribedTourEntity, List<GuidePackageRequest> guidePackageRequestList, String requestId) {
        List<GuidePackageEntity> guidePackageEntityList = setTourGuidePackages(subscribedTourEntity, guidePackageRequestList);
        List<GuidePackageData> guidePackageDataList = guidePackageRepository.saveAll(guidePackageEntityList).stream()
                .map(guidePackageEntity -> new GuidePackageData(guidePackageEntity))
                .toList();
        return new InsertAcknowledgeListResponse(guidePackageDataList, requestId);
    }

    @Override
    public List<GuidePackageEntity> setTourGuidePackages(SubscribedTourEntity subscribedTourEntity, List<GuidePackageRequest> guidePackageRequestList) {
        return guidePackageRequestList.stream()
                .map(guidePackageRequest -> {
                    GuidePackageEntity guidePackageEntity = new GuidePackageEntity();
                    guidePackageEntity.setSubscribedTourEntity(subscribedTourEntity);
                    guidePackageEntity.setNumberOfGuideForDay(guidePackageRequest.getNumberOfGuidesForDay());
                    guidePackageEntity.setTotalGuidePriceForDay(guidePackageRequest.getPerDayGuidePrice());
                    return guidePackageEntity;
                })
                .toList();
    }

    /**
     * @param guidePackageIds
     * @return
     */
    @Override
    public Map<Long, GuidePackageEntity> getGuidePackageEntitiesById(Set<Long> guidePackageIds) {
        return EntityUtil.findEntitiesByIds(guidePackageIds, guidePackageRepository, GuidePackageEntity::getId, "GuidePackageEntity");
    }

    @Override
    public List<GuidePackageData> getAllGuidePackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId) {
        return subscribedTourEntity.getGuidePackageEntities().stream()
                .map(guidePackageEntity -> new GuidePackageData(guidePackageEntity))
                .toList();
    }
}
