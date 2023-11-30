package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableTourPackageOptionEntity;
import com.ghuddy.backendapp.tours.repository.TourPackageOptionRepository;
import com.ghuddy.backendapp.tours.service.TourPackageOptionService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class TourPackagePackageOptionServiceImpl implements TourPackageOptionService {
    private final TourPackageOptionRepository tourPackageOptionRepository;

    public TourPackagePackageOptionServiceImpl(TourPackageOptionRepository tourPackageOptionRepository) {
        this.tourPackageOptionRepository = tourPackageOptionRepository;
    }

    @Override
    public AvailableTourPackageOptionEntity getOptionById(Long optionId) {
        return tourPackageOptionRepository.findById(optionId).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));
    }

    @Override
    public Map<Long, AvailableTourPackageOptionEntity> getAllOptionsByIds(Set<Long> optionsIds) {
        return EntityUtil.findEntitiesByIds(optionsIds, tourPackageOptionRepository, AvailableTourPackageOptionEntity::getId, "TourPackageOptionEntity");
    }

    @Override
    public List<AvailableTourPackageOptionEntity> getAllOptionsByIds(List<Long> optionsIds) {
        return tourPackageOptionRepository.getAllByIdInAndDeletedFalse(optionsIds);
    }
}
