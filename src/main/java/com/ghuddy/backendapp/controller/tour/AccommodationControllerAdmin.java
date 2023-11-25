package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.service.AccommodationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/admin")
//@Api(tags = "Tour - Accommodation Controller For Admin", description = "This controller is used to manage tour accommodation by admins.")
public class AccommodationControllerAdmin {
    private final AccommodationService accommodationService;

    public AccommodationControllerAdmin(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    // Room Category
    @RequestMapping(path = "/room/category/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoomCategory(@RequestBody RoomCategoryAddRequest roomCategoryAddRequest) {
        return new ResponseEntity<>(accommodationService.addRoomCategory(roomCategoryAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/room/category/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoomCategories(@RequestBody RoomCategoryListAddRequest roomCategoryListAddRequest) {
        return new ResponseEntity<>(accommodationService.addRoomCategories(roomCategoryListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/room/category/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoomCategories(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourRoomCategories(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/room/category/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoomCategoriesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourRoomCategoriesPaginated(pageSize, pageNumber, requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // Room Type
    @RequestMapping(path = "/room/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoomType(@RequestBody RoomTypeAddRequest roomTypeAddRequest) {
        return new ResponseEntity<>(accommodationService.addRoomType(roomTypeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/room/type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoomTypes(@RequestBody RoomTypeListAddRequest roomTypeListAddRequest) {
        return new ResponseEntity<>(accommodationService.addRoomTypes(roomTypeListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/room/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoomTypes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourRoomTypes(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/room/type/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoomTypesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourRoomTypesPaginated(pageSize, pageNumber, requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // Accommodation Type
    @RequestMapping(path = "/accommodation/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addAccommodationType(@RequestBody AccommodationTypeAddRequest accommodationTypeAddRequest) {
        return new ResponseEntity<>(accommodationService.addAccommodationType(accommodationTypeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/accommodation/type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addAccommodationTypes(@RequestBody AccommodationTypeListAddRequest accommodationTypeListAddRequest) {
        return new ResponseEntity<>(accommodationService.addAccommodationTypes(accommodationTypeListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/accommodation/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAccommodationTypes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourAccommodationTypes(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/accommodation/type/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAccommodationTypesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourAccommodationTypesPaginated(pageSize, pageNumber, requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // Accommodation
    @RequestMapping(path = "/accommodation/add", method = RequestMethod.POST)
    public ResponseEntity<?> addAccommodation(@RequestBody AccommodationAddRequest accommodationAddRequest) {
        return new ResponseEntity<>(accommodationService.addAccommodation(accommodationAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/accommodation/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addAccommodations(@RequestBody AccommodationListAddRequest accommodationListAddRequest) {
        return new ResponseEntity<>(accommodationService.addAccommodations(accommodationListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/accommodation/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAccommodations(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourAccommodations(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/accommodation/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAccommodationsPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourAccommodationsPaginated(pageSize, pageNumber, requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), "22222",requestId), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
