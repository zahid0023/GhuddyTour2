package com.ghuddy.backendapp.tours.daoImpl;

import com.ghuddy.backendapp.tours.dao.FoodDao;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.food.FoodItemData;
import com.ghuddy.backendapp.tours.model.data.food.MealTypeData;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodDaoImpl implements FoodDao {
    private final JdbcTemplate jdbcTemplate;

    public FoodDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // used both for paginated and non paginated
    @Override
    public List<FoodItemData> getAllFoodItems(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select 
                       id                    as food_item_id,
                       food_item_name        as food_item_name
                from food_item
                """;
        try {
            List<FoodItemData> foodItems = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, FoodItemData.class, jdbcTemplate);
            return foodItems;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }

    @Override
    public List<MealTypeData> getAllMealTypes(Integer pageSize, Integer pageNumber) throws EmptyListException {
        String query = """
                select id             as meal_type_id,
                       meal_type_name as meal_type_name
                from meal_type
                                """;
        try {
            List<MealTypeData> mealTypes = EntityUtil.getAllEntitiesPaginated(query, pageSize, pageNumber, MealTypeData.class, jdbcTemplate);
            return mealTypes;
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }
    }
}
