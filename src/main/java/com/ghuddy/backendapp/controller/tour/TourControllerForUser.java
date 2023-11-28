package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.es.service.ESTourPackageService;
import com.ghuddy.backendapp.tours.es.service.ESTourService;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.service.TourService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/open")
//@Api(tags = "Tour - Tour Controller For User", description = "This controller is used to manage tour by users.")
public class TourControllerForUser {
    private final ESTourService esTourService;
    private final TourService tourService;
    private final TourSubscriptionService tourSubscriptionService;
    private final ESTourPackageService esTourPackageService;

    public TourControllerForUser(TourService tourService,
                                 TourSubscriptionService tourSubscriptionService,
                                 ESTourService esTourService,
                                 ESTourPackageService esTourPackageService) {
        this.tourService = tourService;
        this.tourSubscriptionService = tourSubscriptionService;
        this.esTourService = esTourService;
        this.esTourPackageService = esTourPackageService;
    }

    @RequestMapping(path = "/tours/get/tour/details/by/{tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourDetails(@PathVariable("tour-id") Long tourId, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(esTourService.getTour(tourId, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/tours/get/available/tour-package/all/by/{tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailableTourPackagesByTourId(@PathVariable("tour-id") Long tourId, @RequestParam String requestId) {
        return new ResponseEntity<>(esTourPackageService.getAvailableTourPackagesByTourId(tourId, requestId), HttpStatus.OK);
    }

}
