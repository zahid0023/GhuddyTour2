package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.food.MealTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface MealTypeRepository extends JpaRepository<MealTypeEntity, Long> {
    // Custom query to retrieve MealTypeEntity objects by a list of IDs
    @Query("SELECT m FROM MealTypeEntity m WHERE m.id IN :ids")
    List<MealTypeEntity> findAllByIds(@Param("ids") Set<Long> ids);

    default Map<Long, MealTypeEntity> findMealTypeEntitiesByIds(Set<Long> ids) {
        List<MealTypeEntity> mealTypeEntities = findAllByIds(ids);
        System.out.println(ids);
        // Use Java stream to create a mapping of mealtypeId to MealTypeEntity
        Map<Long, MealTypeEntity> mealTypeEntityMap = mealTypeEntities.stream()
                .collect(Collectors.toMap(MealTypeEntity::getId, mealTypeEntity -> mealTypeEntity));

        // Check if the retrieved meal type entities are less than the input IDs
        if (mealTypeEntities.size() < ids.size()) {
            // Find unmatched meal type IDs
            List<Long> unmatchedIds = new ArrayList<>(ids);
            unmatchedIds.removeAll(mealTypeEntityMap.keySet());
            throw new IllegalArgumentException("Some meal type entities were not found for the given IDs.");
        }
        return mealTypeEntityMap;
    }
}