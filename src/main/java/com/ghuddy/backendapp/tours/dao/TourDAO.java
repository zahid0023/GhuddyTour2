package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourData;
import com.ghuddy.backendapp.tours.model.data.tour.SubscribedTourData;
import com.ghuddy.backendapp.tours.model.data.tour.TourData;

import java.util.List;

public interface TourDAO {
    List<AddedTourData> getAllAddedTours(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TourData> getAllCreatedTours(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<SubscribedTourData> getAllSubscribedToursForMerchant(Integer pageSize, Integer pageNumber, Long merchantId);


}
