package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.food.FoodItemListResponse;
import com.ghuddy.backendapp.tours.dto.response.food.MealPackageAddListResponse;
import com.ghuddy.backendapp.tours.dto.response.food.MealTypeListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.food.MealPackageData;
import com.ghuddy.backendapp.tours.model.entities.food.FoodItemEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FoodService {
    // food item
    InsertAcknowledgeResponse addFoodItem(FoodItemAddRequest foodItemAddRequest);

    InsertAcknowledgeListResponse addFoodItems(FoodItemListAddRequest foodItemListAddRequest);

    FoodItemEntity getFoodItemEntityByID(Long foodItemID);

    Map<Long, FoodItemEntity> getFoodItemEntitiesByIDs(Set<Long> foodItemIDs);

    FoodItemListResponse getAllFoodItems() throws EmptyListException;

    FoodItemListResponse getAllFoodItemsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // meal type
    InsertAcknowledgeResponse addMealType(MealTypeAddRequest mealTypeAddRequest);

    InsertAcknowledgeListResponse addMealTypes(MealTypeListAddRequest mealTypeListAddRequest);

    MealTypeEntity getMealTypeEntityByID(Long mealTypeID);

    Map<Long, MealTypeEntity> getMealTypeEntitiesByIDs(Set<Long> mealTypeIDs);

    MealTypeListResponse getAllMealTypes() throws EmptyListException;

    MealTypeListResponse getAllMealTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // tour meal package
    InsertAcknowledgeResponse addTourMealPackage(SubscribedTourEntity subscribedTourEntity, MealPackageRequest mealPackageRequest, String requestId);
    MealPackageAddListResponse addTourMealPackages(SubscribedTourEntity subscribedTourEntity, List<MealPackageRequest> mealPackageRequestList, String requestId);
    List<MealPackageEntity> setTourMealPackages(SubscribedTourEntity subscribedTourEntity, List<MealPackageRequest> mealPackageRequestList);
    Map<Long, MealPackageEntity> getMealPackageEntitiesByIds(Set<Long> mealPackageIds);
    Map<Long, List<MealPackageData>> getAllMealPackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId);

}
