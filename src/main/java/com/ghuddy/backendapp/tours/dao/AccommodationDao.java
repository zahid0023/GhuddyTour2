package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationData;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationTypeData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourRoomCategoryData;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourRoomTypeData;

import java.util.List;

public interface AccommodationDao {
    List<TourRoomCategoryData> getTourRoomCategories(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<TourRoomTypeData> getTourRoomTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<AccommodationTypeData> getTourAccommodationTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<AccommodationData> getTourAccommodations(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
