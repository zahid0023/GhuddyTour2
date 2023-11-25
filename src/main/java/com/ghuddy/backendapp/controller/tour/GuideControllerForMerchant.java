package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.guide.GuidePackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.guide.GuidePackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.GuideService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/merchant")
public class GuideControllerForMerchant {
    private final GuideService guideService;
    private final TourSubscriptionService tourSubscriptionService;

    public GuideControllerForMerchant(GuideService guideService,
                                      TourSubscriptionService tourSubscriptionService) {
        this.guideService = guideService;
        this.tourSubscriptionService = tourSubscriptionService;
    }

    // Tour Package Accommodation
    @RequestMapping(path = "/tour/guide/package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourGuidePackage(@RequestBody GuidePackageAddRequest guidePackageAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(guidePackageAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(guideService.addGuidePackage(subscribedTourEntity, guidePackageAddRequest.getGuidePackageRequest(), guidePackageAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), guidePackageAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/guide/package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourGuidePackages(@RequestBody GuidePackageListAddRequest guidePackageListAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(guidePackageListAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(guideService.addGuidePackages(subscribedTourEntity, guidePackageListAddRequest.getGuidePackageRequestList(), guidePackageListAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), guidePackageListAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/guide/package/get/all/by/{subscribed-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourGuidePackages(@PathVariable("subscribed-tour-id") Long subscribedTourId, @RequestParam String requestId) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
            return new ResponseEntity<>(guideService.getAllGuidePackagesForSubscribedTour(subscribedTourEntity, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}
