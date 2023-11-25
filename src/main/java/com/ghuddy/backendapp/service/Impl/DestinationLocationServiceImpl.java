package com.ghuddy.backendapp.service.Impl;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.repository.DestinationLocationRepository;
import com.ghuddy.backendapp.service.DestinationLocationService;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class DestinationLocationServiceImpl implements DestinationLocationService {
    private final DestinationLocationRepository destinationLocationRepository;

    public DestinationLocationServiceImpl(DestinationLocationRepository destinationLocationRepository) {
        this.destinationLocationRepository = destinationLocationRepository;
    }

    @Override
    public DestinationLocationEntity getDestinationLocationEntityById(Long id) throws LocationNotFoundException {
        return destinationLocationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(ErrorCode.LOCATION_NOT_FOUND));
    }

    @Override
    public Map<Long, DestinationLocationEntity> getDestinationLocationByIDs(Set<Long> locationIDs) {
        return EntityUtil.findEntitiesByIds(locationIDs, destinationLocationRepository, DestinationLocationEntity::getId, "DestinationLocationEntity");
    }
}

