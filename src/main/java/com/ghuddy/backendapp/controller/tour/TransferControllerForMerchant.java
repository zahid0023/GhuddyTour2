package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import com.ghuddy.backendapp.tours.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
//@Api(tags = "Tour - Transfer Controller For Merchant", description = "This controller is used to manager transfer packages by merchants.")
public class TransferControllerForMerchant {
    private final TransferService transferService;
    private final TourSubscriptionService tourSubscriptionService;


    public TransferControllerForMerchant(TransferService transferService,
                                         TourSubscriptionService tourSubscriptionService) {
        this.transferService = transferService;
        this.tourSubscriptionService = tourSubscriptionService;
    }

    @RequestMapping(path = "/tour/transfer/package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourTransferPackage(@RequestBody TransferPackageAddRequest transferOptionAddRequest) {
        try {
            SubscribedTourEntity tourPackageEntity = tourSubscriptionService.getSubscribedTourEntityById(transferOptionAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(transferService.addTransferPackage(tourPackageEntity, transferOptionAddRequest.getTransferPackageRequest(), transferOptionAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), transferOptionAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/transfer/package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourTransferPackages(@RequestBody TransferPackageListAddRequest transferPackageListAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(transferPackageListAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(transferService.addTransferPackages(subscribedTourEntity, transferPackageListAddRequest.getTransferPackageRequestList(), transferPackageListAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), transferPackageListAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/transfer/package/get/all/by/{subscribed-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourMealPackages(@PathVariable("subscribed-tour-id") Long subscribedTourId, @RequestParam String requestId) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
            return new ResponseEntity<>(transferService.getAllTransferPackagesForSubscribedTour(subscribedTourEntity, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}
