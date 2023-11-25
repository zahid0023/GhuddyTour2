package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.activity.*;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.activity.ActivityListResponse;
import com.ghuddy.backendapp.tours.dto.response.activity.ActivityTypeListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourItineraryEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ActivityService {

    // Activity Type
    InsertAcknowledgeResponse addActivityType(ActivityTypeAddRequest activityTypeAddRequest);

    InsertAcknowledgeListResponse addActivityTypes(ActivityTypeListAddRequest activityTypeListAddRequest);

    ActivityTypeEntity getActivityType(Long activityTypeID);

    ActivityTypeListResponse getAllActivityTypes(String requestId) throws EmptyListException;

    ActivityTypeListResponse getAllActivityTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;

    // Activity
    InsertAcknowledgeResponse addActivity(ActivityAddRequest activityAddRequest);

    InsertAcknowledgeListResponse addActivities(ActivityListAddRequest activityListAddRequest);

    ActivityEntity getActivity(Long ActivityID);

    Map<Long, ActivityEntity> getActivityEntityMapByIDs(Set<Long> activityIDs);

    ActivityListResponse getAllActivities(String requestId) throws EmptyListException;

    ActivityListResponse getAllActivitiesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException;

    // Tour Activity
    List<SubscribedTourItineraryEntity> setSubscribedTourItinerary(SubscribedTourEntity subscribedTourEntity,
                                                                   List<SubscribedTourActivityRequest> subscribedTourActivityDataList,
                                                                   Map<Long, ActivityEntity> activityEntityMap);
}
