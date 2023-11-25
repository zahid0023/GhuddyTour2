package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.activity.ActivityAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.activity.ActivityTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1/admin")
//@Api(tags = "Tour - Activity Controller For Admin", description = "This controller is used to manage tour activities(itineraries) by admins.")
public class ActivityControllerForAdmin {
    private final ActivityService activityService;

    public ActivityControllerForAdmin(ActivityService activityService) {
        this.activityService = activityService;
    }


    // Activity Type
    @RequestMapping(path = "/activity/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addActivityType(@RequestBody ActivityTypeAddRequest activityTypeAddRequest) {
        return new ResponseEntity<>(activityService.addActivityType(activityTypeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/activity/type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addActivityTypes(@RequestBody ActivityTypeListAddRequest activityTypeListAddRequest) {
        return new ResponseEntity<>(activityService.addActivityTypes(activityTypeListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/activity/type/get/{activity-type-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityTypeByID(@PathVariable("activity-type-id") Long activityTypeID, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Operation(summary = "This controller is used to get all the activity type", description = "Call this controller when you need to show all the activity types.")
    @RequestMapping(path = "/activity/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivityTypes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(activityService.getAllActivityTypes(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "This API is used to get all the tour types in a paginated way.")
    @RequestMapping(path = "/activity/type/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivityTypesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(activityService.getAllActivityTypesPaginated(pageSize, pageNumber, requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/activity/type/search", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityTypeByKeyword(@RequestParam String keyword, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/type/search/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivityTypeByKeywordPaginated(@RequestParam String keyword, @RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/type/delete/{activity-id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteActivityType(@PathVariable("activity-id") Long activityID, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // Activity
    @RequestMapping(path = "/activity/add", method = RequestMethod.POST)
    public ResponseEntity<?> addActivity(@RequestBody ActivityAddRequest activityAddRequest) {
        return new ResponseEntity<>(activityService.addActivity(activityAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/activity/add-list", method = RequestMethod.POST)
    public ResponseEntity<?> addActivities(@RequestBody ActivityListAddRequest activityListAddRequest) {
        return new ResponseEntity<>(activityService.addActivities(activityListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/activity/get/{activity-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityByID(@PathVariable("activity-id") Long activityID, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Operation(summary = "This API is used to get all the activities", description = "The API does not sort or paginate the activities in any way")
    @RequestMapping(path = "/activity/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivities(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(activityService.getAllActivities(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "This API is used to get all the activities in a paginated way.")
    @RequestMapping(path = "/activity/get/all-paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivitiesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(activityService.getAllActivitiesPaginated(pageSize, pageNumber, requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/activity/search", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityByKeyword(@RequestParam String keyword, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/search/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivitiesByKeywordPaginated(@RequestParam String keyword, @RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @RequestMapping(path = "/activity/delete/{activity-id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteActivity(@PathVariable("activity-id") Long activityID, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
