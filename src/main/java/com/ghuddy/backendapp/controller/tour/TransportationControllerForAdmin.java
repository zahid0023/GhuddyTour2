package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.transporation.*;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.service.TransportationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/admin")
//@Api(tags = "Tour - Transportation Controller For Admin", description = "This controller is used to manage tour transportations by admins.")
public class TransportationControllerForAdmin {
    private final TransportationService transportationService;

    public TransportationControllerForAdmin(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    // transportation brand
    @RequestMapping(path = "/transportation/brand/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationBrand(@RequestBody TransportationBrandAddRequest transportationBrandAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationBrand(transportationBrandAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transportation/brand/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationBrands(@RequestBody TransportationBrandListAddRequest transportationBrandListAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationBrands(transportationBrandListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transportation/brand/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationBrands(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationBrands(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.info(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transportation/brand/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationBrandsPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationBrandsPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.info(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // transportation mode
    @RequestMapping(path = "/transportation/mode/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationMode(@RequestBody TransportationModeAddRequest transportationModeAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationMode(transportationModeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transportation/mode/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationModes(@RequestBody TransportationModeListAddRequest transportationModeListAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationModes(transportationModeListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transportation/mode/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationModes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationModes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transportation/mode/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationModesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationModesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // transportation providers
    @RequestMapping(path = "/transportation/provider/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationProvider(@RequestBody TransportationProviderAddRequest transportationProviderAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationProvider(transportationProviderAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transportation/provider/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationProviders(@RequestBody TransportationProviderListAddRequest transportationProviderListAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationProviders(transportationProviderListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transportation/provider/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationProviders(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationProviders(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transportation/provider/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationProvidersPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationProvidersPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            log.error(ex.toString());

            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // transportation routes
    @RequestMapping(path = "/transportation/route/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationRoute(@RequestBody TransportationRouteAddRequest transportationRouteAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationRoute(transportationRouteAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transportation/route/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTransportationRoutes(@RequestBody TransportationRouteListAddRequest transportationRouteListAddRequest) {
        return new ResponseEntity<>(transportationService.addTransportationRoutes(transportationRouteListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transportation/route/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationRoutes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationRoutes(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transportation/route/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationRoutesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationRoutesPaginated(pageSize, pageNumber,requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}
