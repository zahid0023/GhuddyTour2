package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityData;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityTypeData;

import java.util.List;

public interface ActivityDAO {
    List<ActivityTypeData> getAllActivityTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;

    List<ActivityData> getAllActivities(Integer pageSize, Integer pageNumber);
}
