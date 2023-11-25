package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationBrandData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationModeData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationProviderData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationRouteData;

import java.util.List;

public interface TransportationDao {
    List<TransportationRouteData> getAllTransportationRoutes(Integer pageSize, Integer pagNumber) throws EmptyListException;

    List<TransportationModeData> getAllTransportationModes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TransportationProviderData> getAllTransportationProviders(Integer pageSize, Integer pageNumber) throws EmptyListException;
    List<TransportationBrandData> getAllTransportationBrands(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
