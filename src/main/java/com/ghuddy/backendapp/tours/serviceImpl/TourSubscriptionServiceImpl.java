package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.model.UserEntity;
import com.ghuddy.backendapp.repository.UserRepository;
import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.dto.request.activity.SubscribedTourActivityRequest;
import com.ghuddy.backendapp.tours.dto.request.tour.TourSubscriptionRequest;
import com.ghuddy.backendapp.tours.dto.response.tour.TourSubscriptionResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.SubscribedTourData;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.repository.SubscribedTourRepository;
import com.ghuddy.backendapp.tours.service.ActivityService;
import com.ghuddy.backendapp.tours.service.TourService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TourSubscriptionServiceImpl implements TourSubscriptionService {
    private final TourService tourService;
    private final UserRepository merchantRepository;
    private final ActivityService activityService;
    private final SubscribedTourRepository subscribedTourRepository;
    private final TourDAO tourDAO;

    public TourSubscriptionServiceImpl(TourService tourService,
                                       UserRepository merchantRepository,
                                       ActivityService activityService,
                                       SubscribedTourRepository subscribedTourRepository,
                                       TourDAO tourDAO) {
        this.tourService = tourService;
        this.merchantRepository = merchantRepository;
        this.activityService = activityService;
        this.subscribedTourRepository = subscribedTourRepository;
        this.tourDAO = tourDAO;
    }


    /**
     * @param tourSubscriptionRequest the DTO for tour subscription
     * @param requestId               to keep track of the request served by this service
     * @return TourSubscriptionResponse
     * @throws TourNotFoundException when the tour for this id is not found to be subscribed
     */
    @Override
    public TourSubscriptionResponse subscribeTour(TourSubscriptionRequest tourSubscriptionRequest, String requestId) throws TourNotFoundException, EntityNotFoundException {
        TourEntity tourEntity = tourService.getCreatedTourEntityById(tourSubscriptionRequest.getTourId());
        UserEntity userEntity = merchantRepository.findById(tourSubscriptionRequest.getMerchantId()).orElseThrow(() -> new EntityNotFoundException("MerchantEntity Not Found"));
        SubscribedTourData subscribedTourData = saveSubscribedTours(tourEntity, userEntity, tourSubscriptionRequest);
        return new TourSubscriptionResponse(subscribedTourData, requestId);
    }

    @Transactional
    public SubscribedTourData saveSubscribedTours(TourEntity tourEntity,
                                                  UserEntity merchantEntity,
                                                  TourSubscriptionRequest tourSubscriptionRequest) {

        SubscribedTourEntity subscribedTourEntity = prepareSubscribedTourEntity(tourEntity, merchantEntity, tourSubscriptionRequest);
        subscribedTourEntity = subscribedTourRepository.save(subscribedTourEntity);

        return new SubscribedTourData(subscribedTourEntity);
    }

    private SubscribedTourEntity prepareSubscribedTourEntity(TourEntity tourEntity,
                                                             UserEntity merchantEntity,
                                                             TourSubscriptionRequest tourSubscriptionRequest) {

        Set<Long> activityIds = tourSubscriptionRequest.getSubscribedTourActivityList().stream()
                .map(SubscribedTourActivityRequest::getActivityId)
                .collect(Collectors.toSet());

        Map<Long, ActivityEntity> activityEntityMap = activityService.getActivityEntityMapByIDs(activityIds);

        // Create SubscribedTourEntity
        SubscribedTourEntity subscribedTourEntity = new SubscribedTourEntity();

        // Set properties of SubscribedTourEntity
        subscribedTourEntity.setTourEntity(tourEntity);
        subscribedTourEntity.setMerchantEntity(merchantEntity);
        subscribedTourEntity.setSubscribedTourItineraryEntities(activityService.setSubscribedTourItinerary(subscribedTourEntity, tourSubscriptionRequest.getSubscribedTourActivityList(), activityEntityMap));
        subscribedTourEntity.setTourReportingTime(tourSubscriptionRequest.getTourReportingTime());
        subscribedTourEntity.setTourReportingPlace(tourSubscriptionRequest.getTourReportingPlace());

        return subscribedTourEntity;
    }

    /**
     * @param id the id of the subscribed tour
     * @return SubscribedTourEntity
     * @throws TourNotFoundException when the subscribed tour for this id is not found
     */
    @Override
    public SubscribedTourEntity getSubscribedTourEntityById(Long id) throws TourNotFoundException {
        return subscribedTourRepository.findById(id).orElseThrow(() -> new TourNotFoundException(ErrorCode.TOUR_NOT_FOUND));
    }
}
