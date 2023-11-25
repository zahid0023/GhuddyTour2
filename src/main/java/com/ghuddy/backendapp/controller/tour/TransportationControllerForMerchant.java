package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import com.ghuddy.backendapp.tours.service.TransportationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Transportation Controller For Merchant", description = "This controller is used to manage tour transportation by merchants.")
public class TransportationControllerForMerchant {
    private final TransportationService transportationService;
    private final TourSubscriptionService tourSubscriptionService;

    public TransportationControllerForMerchant(TransportationService transportationService,
                                               TourSubscriptionService tourSubscriptionService) {
        this.transportationService = transportationService;
        this.tourSubscriptionService = tourSubscriptionService;
    }

    // transportation route
    @RequestMapping(path = "/tour/transportation/route/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationRoutes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationRoutes(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/transportation/route/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationRoutesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationRoutesPaginated(pageSize, pageNumber, requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // transportation mode
    @RequestMapping(path = "/tour/transportation/mode/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationModes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationModes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/transportation/mode/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationModesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationModesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // transportation brand
    @RequestMapping(path = "/tour/transportation/brand/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationBrands(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationBrands(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.info(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/transportation/brand/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationBrandsPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationBrandsPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.info(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // transportation provider
    @RequestMapping(path = "/tour/transportation/provider/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationProviders(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationProviders(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/transportation/provider/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationProvidersPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationProvidersPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            log.error("Error:" + ex);
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }


    // tour transportation packages
    @RequestMapping(path = "/tour/transportation/package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourTransportationPackage(@RequestBody TransportationPackageAddRequest transportationPackageAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(transportationPackageAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(transportationService.addTransportationPackage(subscribedTourEntity, transportationPackageAddRequest.getTourPackageTransportation(),transportationPackageAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), transportationPackageAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/transportation/package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourTransportationPackages(@RequestBody TransportationPackageListAddRequest transportationPackageListAddRequest) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(transportationPackageListAddRequest.getSubscribedTourId());
            return new ResponseEntity<>(transportationService.addTransportationPackages(subscribedTourEntity, transportationPackageListAddRequest.getTourPackageTransportations(),transportationPackageListAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), transportationPackageListAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/transportation/package/get/all/by/{subscribed-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourTransportationPackages(@PathVariable("subscribed-tour-id") Long subscribedTourId, @RequestParam String requestId) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
            return new ResponseEntity<>(transportationService.getAllTransportationPackagesForSubscribedTour(subscribedTourEntity, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}
