package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.service.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Tour Controller For Merchant", description = "This controller is used to manage tours by merchants.")
public class TourControllerForMerchant {
    private final TourService tourService;

    public TourControllerForMerchant(TourService tourService) {
        this.tourService = tourService;
    }

    @RequestMapping(path = "/tours/created-tours/get/by/{tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCreatedTourById(@PathVariable("tour-id") Long tourId, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourService.getCreatedTourByCreatedTourId(tourId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tours/created-tours/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCreatedTours(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourService.getAllCreatedTours(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tours/created-tours/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCreatedToursPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourService.getAllCreatedToursPaginated(pageSize, pageNumber, requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}
