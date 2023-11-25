package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.tour.TourSubscriptionRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Tour Subscription Controller For Merchant", description = "This controller is used to subscribe tour/tour packages by merchants.")
public class TourSubscriptionControllerForMerchant {
    private final TourSubscriptionService tourSubscriptionService;

    public TourSubscriptionControllerForMerchant(TourSubscriptionService tourSubscriptionService) {
        this.tourSubscriptionService = tourSubscriptionService;
    }

    @RequestMapping(path = "/tour/subscribe", method = RequestMethod.POST)
    public ResponseEntity<?> subscribeTour(@RequestBody TourSubscriptionRequest tourSubscriptionRequest) {
        try {
            return new ResponseEntity<>(tourSubscriptionService.subscribeTour(tourSubscriptionRequest, tourSubscriptionRequest.getRequestId()), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourSubscriptionRequest.getRequestId()), HttpStatus.BAD_REQUEST);
        }
    }
}
