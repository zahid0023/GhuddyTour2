package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryPackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryPackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.SpotEntryService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Spot Entry Controller For Admin", description = "This controller is used to manage tour spot entries by merchants.")
public class SpotEntryControllerForMerchant {
    private final SpotEntryService spotEntryService;
    private final TourSubscriptionService tourSubscriptionService;

    public SpotEntryControllerForMerchant(SpotEntryService spotEntryService,
                                          TourSubscriptionService tourSubscriptionService) {
        this.spotEntryService = spotEntryService;
        this.tourSubscriptionService = tourSubscriptionService;
    }

    @RequestMapping(path = "/tour/spot/entry/package/add", method = RequestMethod.POST)
    public ResponseEntity addTourSpotEntryPackage(@RequestBody SpotEntryPackageAddRequest spotEntryPackageAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(spotEntryPackageAddRequest.getSubscribedTourId());
            return new ResponseEntity(spotEntryService.addSpotEntryPackage(subscribedTourEntity, spotEntryPackageAddRequest.getSpotEntryPackageRequest(), spotEntryPackageAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity(new ErrorResponse(ex.getErrorCode(), spotEntryPackageAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/spot/entry/package/list/add", method = RequestMethod.POST)
    public ResponseEntity addTourSpotEntryPackages(@RequestBody SpotEntryPackageListAddRequest spotEntryPackageListAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(spotEntryPackageListAddRequest.getSubscribedTourId());
            return new ResponseEntity(spotEntryService.addSpotEntryPackages(subscribedTourEntity, spotEntryPackageListAddRequest.getSpotEntryPackageRequestList(), spotEntryPackageListAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity(new ErrorResponse(ex.getErrorCode(), spotEntryPackageListAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/spot/entry/package/get/all/by/{subscribed-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourSpotEntryPackages(@PathVariable("subscribed-tour-id") Long subscribedTourId, @RequestParam String requestId) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
            return new ResponseEntity<>(spotEntryService.getAllSpotEntryPackagesForSubscribedTour(subscribedTourEntity, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}
