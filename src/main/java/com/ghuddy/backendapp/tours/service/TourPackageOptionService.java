package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.model.entities.combination.AvailableTourPackageOptionEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TourPackageOptionService {
    AvailableTourPackageOptionEntity getOptionById(Long optionId);

    Map<Long, AvailableTourPackageOptionEntity> getAllOptionsByIds(Set<Long> optionsIds);
    List<AvailableTourPackageOptionEntity> getAllOptionsByIds(List<Long> optionsIds);
}
