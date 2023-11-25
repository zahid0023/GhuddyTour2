package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.food.FoodItemAddRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodItemListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/admin")
//@Api(tags = "Tour - Food Controller For Admin", description = "This controller is used to manage tour foods by admins.")
public class FoodControllerForAdmin {
    private final FoodService foodService;

    public FoodControllerForAdmin(FoodService foodService) {
        this.foodService = foodService;
    }


    // food item
    @RequestMapping(path = "/food/item/add", method = RequestMethod.POST)
    public ResponseEntity<?> addFoodItem(@RequestBody FoodItemAddRequest foodItemAddRequest) {
        return new ResponseEntity<>(foodService.addFoodItem(foodItemAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/item/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addFoodItems(@RequestBody FoodItemListAddRequest foodItemListAddRequest) {
        return new ResponseEntity<>(foodService.addFoodItems(foodItemListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/item/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllFoodItems(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(foodService.getAllFoodItems(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/food/item/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllFoodItemsPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(foodService.getAllFoodItemsPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    // meal type
    @RequestMapping(path = "/food/meal-type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMealType(@RequestBody MealTypeAddRequest mealTypeAddRequest) {
        return new ResponseEntity<>(foodService.addMealType(mealTypeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/meal-type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMealTypes(@RequestBody MealTypeListAddRequest mealTypeListAddRequest) {
        return new ResponseEntity<>(foodService.addMealTypes(mealTypeListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/meal/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMealTypes(String requestId) {
        try {
            return new ResponseEntity<>(foodService.getAllMealTypes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/meal/type/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMealTypesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(foodService.getAllMealTypesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }
}
