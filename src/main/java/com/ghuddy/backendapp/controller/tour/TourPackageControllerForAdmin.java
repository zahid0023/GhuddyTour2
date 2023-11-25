package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/admin")
//@Api(tags = "Tour - Tour Package Controller For Admin", description = "This controller is used to manage tour packages by admins.")
public class TourPackageControllerForAdmin {

    private TourPackageService tourPackageService;

    public TourPackageControllerForAdmin(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }


    @RequestMapping(path = "/tour/package/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageType(@RequestBody TourPackageTypeAddRequest tourPackageTypeAddRequest) {
        return new ResponseEntity<>(tourPackageService.addTourPackageType(tourPackageTypeAddRequest.getTourPackageTypeRequest(), tourPackageTypeAddRequest.getRequestId()), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/tour/package/type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageTypes(@RequestBody TourPackageTypeListAddRequest tourPackageTypeListAddRequest) {
        return new ResponseEntity<>(tourPackageService.addTourPackageTypes(tourPackageTypeListAddRequest.getTourPackageTypes(), tourPackageTypeListAddRequest.getRequestId()), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/tour/package/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTourPackageTypes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourPackageService.getAllTourPackageTypes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour/package/type/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTourPackageTypesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourPackageService.getAllTourPackageTypesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}

