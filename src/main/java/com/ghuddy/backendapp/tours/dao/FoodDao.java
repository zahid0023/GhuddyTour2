package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.food.FoodItemData;
import com.ghuddy.backendapp.tours.model.data.food.MealTypeData;

import java.util.List;

public interface FoodDao {
    List<FoodItemData> getAllFoodItems(Integer pageSize, Integer pageNumber) throws EmptyListException;
    List<MealTypeData> getAllMealTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;
}
