package com.ghuddy.backendapp.tours.utils;

import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class CombinationGenerator {
    public static List<List<?>> generateCombinations(List<List<?>> lists) {
        List<List<?>> combinations = new LinkedList<>();

        // Initialize the product with a single empty combination
        combinations.add(new LinkedList<>());


        // Iterate over each list in the input
        for (List<?> currentList : lists) {
            List<List<?>> tempCombinations = new LinkedList<>();

            // Iterate over each element in the current list
            for (Object element : currentList) {
                // Extend the existing combinations with the current element
                for (List<?> combination : combinations) {
                    List<Object> newCombination = new LinkedList<>(combination);
                    newCombination.add(element);
                    tempCombinations.add(newCombination);
                }
            }

            // Update the result with the new combinations
            combinations = tempCombinations;
        }

        return combinations;
    }

    public static List<List<MealPackageRequest>> generateMealPackagesCombination(List<List<MealPackageRequest>> mealPackages) {
        return Lists.cartesianProduct(mealPackages);
    }
}









