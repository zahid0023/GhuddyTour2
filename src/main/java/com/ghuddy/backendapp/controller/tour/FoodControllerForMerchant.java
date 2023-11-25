package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.food.MealPackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.FoodService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
// @Api(tags = "Tour - Food Controller For Merchant", description = "This controller is used to manage tour foods by merchants.")
public class FoodControllerForMerchant {
    private final FoodService foodService;
    private final TourSubscriptionService tourSubscriptionService;

    public FoodControllerForMerchant(FoodService foodService,
                                     TourSubscriptionService tourSubscriptionService) {
        this.foodService = foodService;
        this.tourSubscriptionService = tourSubscriptionService;
    }

    // food item
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
    @RequestMapping(path = "/meal/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMealTypes(@RequestParam String requestId) {
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

    // meal package

    @RequestMapping(path = "/tour/food/meal-package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMealPackage(@RequestBody MealPackageAddRequest mealPackageAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(mealPackageAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(foodService.addTourMealPackage(subscribedTourEntity, mealPackageAddRequest.getMealPackageRequest(), mealPackageAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), mealPackageAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/food/meal-package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMealPackages(@RequestBody MealPackageListAddRequest mealPackageListAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(mealPackageListAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(foodService.addTourMealPackages(subscribedTourEntity, mealPackageListAddRequest.getMealPackageRequestList(), mealPackageListAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), mealPackageListAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(path = "/tour/meal/package/get/all/by/{subscribed-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourMealPackages(@PathVariable("subscribed-tour-id") Long subscribedTourId, @RequestParam String requestId) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
            return new ResponseEntity<>(foodService.getAllMealPackagesForSubscribedTour(subscribedTourEntity, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}
