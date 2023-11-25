package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
public class TourPackageControllerForMerchant {
    private final TourPackageService tourPackageService;

    public TourPackageControllerForMerchant(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
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
