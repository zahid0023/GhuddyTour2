package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.es.service.ESTourService;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/admin/es")
//@Api(tags = "Tour - ES Tour Controller For Admin", description = "This controller is used to manage tour indexing by admins.")
public class ESTourControllerForAdmin {
    private final ESTourService esTourService;

    public ESTourControllerForAdmin(ESTourService esTourService) {
        this.esTourService = esTourService;
    }

    @RequestMapping(path = "/tour/index/by/{created-tour-id}", method = RequestMethod.POST)
    public ResponseEntity<?> indexTourByTourId(@PathVariable("created-tour-id") Long createdTourId, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(esTourService.indexTourByTourId(createdTourId, requestId), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}
