package com.ghuddy.backendapp.service;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;

import java.util.Map;
import java.util.Set;

public interface DestinationLocationService {

    DestinationLocationEntity getDestinationLocationEntityById(Long id) throws LocationNotFoundException;

    Map<Long, DestinationLocationEntity> getDestinationLocationByIDs(Set<Long> locationIDs);
}
