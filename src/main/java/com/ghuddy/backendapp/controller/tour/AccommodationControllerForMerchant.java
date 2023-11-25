package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationPackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationPackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.AccommodationService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Accommodation Controller For Merchant", description = "This controller is used to manage tour accommodation by merchants.")
public class AccommodationControllerForMerchant {
    private final AccommodationService accommodationService;
    private final TourSubscriptionService tourSubscriptionService;

    public AccommodationControllerForMerchant(AccommodationService accommodationService,
                                              TourSubscriptionService tourSubscriptionService) {
        this.accommodationService = accommodationService;
        this.tourSubscriptionService = tourSubscriptionService;
    }

    // room category
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

    // room type
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

    // accommodation
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
        }
    }

    // Tour Package Accommodation
    @RequestMapping(path = "/tour/accommodation/package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourAccommodationPackage(@RequestBody AccommodationPackageAddRequest accommodationPackageAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(accommodationPackageAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(accommodationService.addAccommodationPackage(subscribedTourEntity, accommodationPackageAddRequest.getAccommodationPackageRequest(), accommodationPackageAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), accommodationPackageAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/accommodation/package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourAccommodationPackages(@RequestBody AccommodationPackageListAddRequest accommodationPackageListAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(accommodationPackageListAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(accommodationService.addAccommodationPackages(subscribedTourEntity, accommodationPackageListAddRequest.getAccommodationPackageRequestList(), accommodationPackageListAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), accommodationPackageListAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/accommodation/package/get/all/by/{subscribed-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourAccommodationPackages(@PathVariable("subscribed-tour-id") Long subscribedTourId, @RequestParam String requestId) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
            return new ResponseEntity<>(accommodationService.getAllAccommodationPackagesForSubscribedTour(subscribedTourEntity, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}
