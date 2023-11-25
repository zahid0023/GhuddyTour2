package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.ActivityDAO;
import com.ghuddy.backendapp.tours.dto.request.activity.*;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.activity.ActivityListResponse;
import com.ghuddy.backendapp.tours.dto.response.activity.ActivityTypeListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityData;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityTypeData;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityImageEntity;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourItineraryEntity;
import com.ghuddy.backendapp.tours.repository.ActivityRepository;
import com.ghuddy.backendapp.tours.repository.ActivityTypeRepository;
import com.ghuddy.backendapp.tours.service.ActivityService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    private final ActivityTypeRepository activityTypeRepository;
    private final ActivityRepository activityRepository;
    private final ActivityDAO activityDAO;

    public ActivityServiceImpl(ActivityTypeRepository activityTypeRepository,
                               ActivityRepository activityRepository,
                               ActivityDAO activityDAO) {
        this.activityTypeRepository = activityTypeRepository;
        this.activityRepository = activityRepository;
        this.activityDAO = activityDAO;
    }

    @Override
    public InsertAcknowledgeResponse addActivityType(ActivityTypeAddRequest activityTypeAddRequest) {
        ActivityTypeData activityTypeData = addActivityTypes(List.of(activityTypeAddRequest.getActivityType())).get(0);
        return new InsertAcknowledgeResponse(activityTypeData, activityTypeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addActivityTypes(ActivityTypeListAddRequest activityTypeListAddRequest) {
        List<ActivityTypeData> activityTypeDataList = addActivityTypes(activityTypeListAddRequest.getActivityTypes());
        return new InsertAcknowledgeListResponse(activityTypeDataList, activityTypeListAddRequest.getRequestId());
    }

    private List<ActivityTypeData> addActivityTypes(List<ActivityTypeRequest> activities) {
        List<ActivityTypeEntity> activityTypeEntities = activities.stream()
                .map(activityTypeRequest -> {
                    ActivityTypeEntity activityTypeEntity = new ActivityTypeEntity();
                    activityTypeEntity.setActivityTypeName(activityTypeRequest.getActivityTypeName());
                    activityTypeEntity.setDescription(activityTypeRequest.getDescription());
                    return activityTypeEntity;
                })
                .collect(Collectors.toList());
        return activityTypeRepository.saveAll(activityTypeEntities).stream()
                .map(activityTypeEntity -> new ActivityTypeData(activityTypeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ActivityTypeEntity getActivityType(Long activityTypeID) {
        return activityTypeRepository.findById(activityTypeID).orElseThrow(() -> new EntityNotFoundException("ActivityTypeEntity Not Found"));
    }

    @Override
    public ActivityTypeListResponse getAllActivityTypes(String requestId) throws EmptyListException {
        List<ActivityTypeEntity> activityTypeEntityList = activityTypeRepository.findAll();
        if (activityTypeEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<ActivityTypeData> activityTypeDataList = activityTypeEntityList.stream()
                .map(activityTypeEntity -> new ActivityTypeData(activityTypeEntity))
                .toList();
        return new ActivityTypeListResponse(activityTypeDataList, requestId);
    }

    @Override
    public ActivityTypeListResponse getAllActivityTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<ActivityTypeEntity> activityTypeEntityList = activityTypeRepository.findAll(pageable).getContent();
        if (activityTypeEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<ActivityTypeData> activityTypeDataList = activityTypeEntityList.stream()
                .map(activityTypeEntity -> new ActivityTypeData(activityTypeEntity))
                .toList();
        return new ActivityTypeListResponse(activityTypeDataList, requestId);
    }

    @Override
    public InsertAcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest) {
        ActivityData activityData = addActivities(List.of(activityAddRequest.getActivity())).get(0);
        return new InsertAcknowledgeResponse(activityData, activityAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addActivities(ActivityListAddRequest activityListAddRequest) {
        List<ActivityData> activityDataList = addActivities(activityListAddRequest.getActivities());
        return new InsertAcknowledgeListResponse(activityDataList, activityListAddRequest.getRequestId());
    }

    @Transactional
    public List<ActivityData> addActivities(List<ActivityRequest> activities) {
        Set<Long> activityTypeIDs = activities.stream()
                .map(ActivityRequest::getActivityTypeID)
                .collect(Collectors.toSet());
        Map<Long, ActivityTypeEntity> activityTypeEntityMap = EntityUtil.findEntitiesByIds(activityTypeIDs, activityTypeRepository, ActivityTypeEntity::getId, "ActivityTypeEntity");
        List<ActivityEntity> activityEntities = activities.stream()
                .map(activityRequest -> {
                    ActivityEntity activityEntity = new ActivityEntity();
                    activityEntity.setActivityTypeEntity(activityTypeEntityMap.get(activityRequest.getActivityTypeID()));
                    activityEntity.setActivityName(activityRequest.getActivityName());
                    activityEntity.setShortLocation(activityRequest.getShortLocation());
                    if (activityRequest.getActivityImages() != null && !activityRequest.getActivityImages().isEmpty()) {
                        List<ActivityImageEntity> activityImageEntities = activityRequest.getActivityImages().stream()
                                .map(imageRequest -> {
                                    ActivityImageEntity activityImageEntity = new ActivityImageEntity();
                                    activityImageEntity.setActivityEntity(activityEntity);
                                    activityImageEntity.setFileName(imageRequest.getFileName());
                                    activityImageEntity.setImageUrl(imageRequest.getImageURL());
                                    activityImageEntity.setImageUrl(imageRequest.getImageURL());
                                    activityImageEntity.setCaption(imageRequest.getImageCaption());
                                    return activityImageEntity;
                                })
                                .collect(Collectors.toList());
                        activityEntity.setActivityImageEntities(activityImageEntities);
                    } else log.warn("No image with this activity!");

                    return activityEntity;
                })
                .collect(Collectors.toList());
        return activityRepository.saveAll(activityEntities).stream()
                .map(activityEntity -> new ActivityData(activityEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ActivityEntity getActivity(Long activityID) {
        return activityRepository.findById(activityID).orElseThrow(() -> new EntityNotFoundException("ActivityEntity not found"));
    }

    @Override
    public ActivityListResponse getAllActivities(String requestId) throws EmptyListException {
        List<ActivityEntity> activityEntityList = activityRepository.findAll();
        if (activityEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<ActivityData> activityDataList = activityEntityList.stream()
                .map(activityEntity -> new ActivityData(activityEntity))
                .toList();
        return new ActivityListResponse(activityDataList, requestId);
    }

    @Override
    public ActivityListResponse getAllActivitiesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<ActivityEntity> activityEntityList = activityRepository.findAll(pageable).getContent();
        if (activityEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<ActivityData> activityDataList = activityEntityList.stream()
                .map(activityEntity -> new ActivityData(activityEntity))
                .toList();
        return new ActivityListResponse(activityDataList, requestId);
    }

    @Override
    public Map<Long, ActivityEntity> getActivityEntityMapByIDs(Set<Long> activityIDs) {
        return EntityUtil.findEntitiesByIds(activityIDs, activityRepository, ActivityEntity::getId, "ActivityEntity");
    }

    // Tour Activity

    @Override
    public List<SubscribedTourItineraryEntity> setSubscribedTourItinerary(SubscribedTourEntity subscribedTourEntity,
                                                                          List<SubscribedTourActivityRequest> subscribedTourActivityDataList,
                                                                          Map<Long, ActivityEntity> activityEntityMap) {

        // Create SubscribedTourItineraryEntities
        List<SubscribedTourItineraryEntity> subscribedTourItineraryEntities = subscribedTourActivityDataList.stream()
                .map(subscribedTourActivityRequest -> {
                    SubscribedTourItineraryEntity subscribedTourItineraryEntity = new SubscribedTourItineraryEntity();

                    subscribedTourItineraryEntity.setSubscribedTourEntity(subscribedTourEntity);
                    subscribedTourItineraryEntity.setActivityEntity(activityEntityMap.get(subscribedTourActivityRequest.getActivityId()));
                    subscribedTourItineraryEntity.setActivityDayNumber(subscribedTourActivityRequest.getDayNumber());
                    subscribedTourItineraryEntity.setActivityStartTime(subscribedTourActivityRequest.getStartTime());
                    subscribedTourItineraryEntity.setActivityEndTime(subscribedTourActivityRequest.getEndTime());
                    subscribedTourItineraryEntity.setIsActive(true);

                    return subscribedTourItineraryEntity;
                })
                .collect(Collectors.toList());

        return subscribedTourItineraryEntities;
    }
}
