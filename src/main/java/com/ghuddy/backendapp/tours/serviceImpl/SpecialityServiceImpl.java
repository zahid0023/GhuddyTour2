package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.tour.TourSpecialityRequest;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.TourSpecialityEntity;
import com.ghuddy.backendapp.tours.repository.TourSpecialityRepository;
import com.ghuddy.backendapp.tours.service.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {
    private final TourSpecialityRepository tourSpecialityRepository;

    public SpecialityServiceImpl(TourSpecialityRepository tourSpecialityRepository) {
        this.tourSpecialityRepository = tourSpecialityRepository;
    }

    @Override
    public List<TourSpecialityEntity> setTourSpecialities(TourEntity tourEntity, List<TourSpecialityRequest> specialities) {
        List<TourSpecialityEntity> tourSpecialityEntities = specialities.stream()
                .map(specialityData -> {
                    TourSpecialityEntity tourSpecialityEntity = new TourSpecialityEntity();
                    tourSpecialityEntity.setTitle(specialityData.getTourSpecialityTitle());
                    tourSpecialityEntity.setDescription(specialityData.getTourSpecialityDescription());
                    tourSpecialityEntity.setIconUrl(specialityData.getTourSpecialityIconURL());
                    tourSpecialityEntity.setTourEntity(tourEntity);
                    return tourSpecialityEntity;
                })
                .collect(Collectors.toList());

        return tourSpecialityEntities;
    }
}
