package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.availability.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.TourPackageAvailabilityService;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/merchant/")
//@Api(tags = "Tour - Tour Package Availability Controller For Merchant", description = "This controller is used to manage tour package availability by merchants.")
public class TourPackageAvailabilityControllerForMerchant {
    private final TourPackageAvailabilityService tourPackageAvailabilityService;
    private final TourSubscriptionService tourSubscriptionService;

    public TourPackageAvailabilityControllerForMerchant(TourPackageAvailabilityService tourPackageAvailabilityService,
                                                        TourSubscriptionService tourSubscriptionService) {
        this.tourPackageAvailabilityService = tourPackageAvailabilityService;
        this.tourSubscriptionService = tourSubscriptionService;
    }

    @RequestMapping(value = "/tour-package/availability/set", method = RequestMethod.POST)
    public ResponseEntity<?> setTourPackageAvailability(@RequestBody TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(tourPackageAvailabilitySetRequest.getSubscribedTourId());
            tourPackageAvailabilityService.generateTourPackageAvailabilityOptions(subscribedTourEntity, tourPackageAvailabilitySetRequest);
            return null;
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourPackageAvailabilitySetRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }
}
