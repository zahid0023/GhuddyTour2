package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourItineraryEntity;
import com.ghuddy.backendapp.tours.repository.TourItineraryRepository;
import com.ghuddy.backendapp.tours.service.ActivityService;
import com.ghuddy.backendapp.tours.service.TourItineraryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TourItineraryServiceImpl implements TourItineraryService {
    private final TourItineraryRepository tourItineraryRepository;
    private final ActivityService activityService;

    public TourItineraryServiceImpl(TourItineraryRepository tourItineraryRepository, ActivityService activityService) {
        this.tourItineraryRepository = tourItineraryRepository;
        this.activityService = activityService;
    }

    @Override
    public List<TourItineraryEntity> setTourActivities(TourEntity tourEntity, Set<Long> tourActivityIds) {

        Map<Long, ActivityEntity> activityEntityMap = activityService.getActivityEntityMapByIDs(tourActivityIds);
        List<TourItineraryEntity> tourItineraryEntities = tourActivityIds.stream()
                .map(activityId -> {
                    TourItineraryEntity tourItineraryEntity = new TourItineraryEntity();
                    tourItineraryEntity.setTourEntity(tourEntity);
                    tourItineraryEntity.setActivity(activityEntityMap.get(activityId));
                    tourItineraryEntity.setIsActive(true);
                    return tourItineraryEntity;
                })
                .collect(Collectors.toList());
        return tourItineraryEntities;
    }
}
