package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.tour.TourAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.service.AddedTourService;
import com.ghuddy.backendapp.tours.service.TourService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/admin")
//@Api(tags = "Tour - Tour Controller For Admin", description = "This controller is used to manage tours by admins.")
public class TourControllerForAdmin {
    private final AddedTourService addedTourService;
    private final TourService tourService;

    public TourControllerForAdmin(AddedTourService addedTourService,
                                  TourService tourService) {
        this.addedTourService = addedTourService;
        this.tourService = tourService;
    }

    // ADD Tour
    @Operation(summary = "This API is used to add a Tour", description = "This API is used to add a tour. " +
            "Adding tour just means that you are adding a tour in a place with " +
            "number of days and number of nights! " +
            "It will be a real tour when you create itineraries for the added tour i.e. " +
            "you map activities to a added tour")
    @RequestMapping(path = "/tours/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTour(@RequestBody TourAddRequest tourAddRequest) {
        try {
            return new ResponseEntity<>(addedTourService.addTour(tourAddRequest), HttpStatus.OK);
        } catch (LocationNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tours/delete/{tour-id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAddedTour(@PathVariable("tour-id") Long tourID, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/tours/get/added/tour/by/{added-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAddedTourByAddedTourId(@PathVariable("added-tour-id") Long addedTourId, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(addedTourService.getAddedTourByAddedTourId(addedTourId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "This API is used to get all the Added Tours in the database",
            description = "This API does not return the created tours but the added tours. " +
                    "The tours returned by this API does not have any activities associated with it i.e. it does not have any itinerary. " +
                    "This API is mainly used to get all the added tours so that admin can associate activities with this tour and create a tour.")
    @RequestMapping(path = "/tours/added-tours/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAddedTours(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(addedTourService.getAllAddedTours(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "This API is used to get all the Added Tours in the database in a paginated way",
            description = "This API does not return the created tours but the added tours. " +
                    "You have to provide pageSize and pageNumber to get the paginated tours. " +
                    "The tours returned by this API does not have any activities associated with it i.e. it does not have any itinerary. " +
                    "This API is mainly used to get all the added tours so that admin can associate activities with this tour and create a tour.")
    @RequestMapping(path = "/tours/added-tours/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAddedToursPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(addedTourService.getAllAddedToursPaginated(pageSize, pageNumber, requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tours/get-all-by-destination-location/{location-name}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllToursByDestinationLocation(@PathVariable("location-name") String locationName, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/tours/get-all-by-destination-location/paginated/{location-name}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllToursPaginatedByDestinationLocation(@PathVariable("location-name") String locationName, @RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // Create Tour
    @Operation(summary = "This API is used to create a tour. ",
            description = "When you added the tour you did not assign any activity to the tour. " +
                    "A tour will be a real tour when you assign activities to the tour. " +
                    "This is the API where you assign activities to a previously added tour creating itinerary for the tour.")
    @RequestMapping(path = "/tours/create", method = RequestMethod.POST)
    public ResponseEntity<?> createTour(@RequestBody TourCreateRequest tourCreateRequest) {
        try {
            return new ResponseEntity<>(tourService.createTour(tourCreateRequest), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourCreateRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "This API is used to get a created tour. This API is different from the one that is used to get the added tour.",
            description = "This API is used to get a created tour. By created tour it means that the tour that has some activities i.e. an ititnerary" +
                    "associated with it. This API returns the tour that you create using the /api/v1/tours/create end point.")
    @RequestMapping(path = "/tours/get/created/tour/by/{created-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCreatedTour(@PathVariable("created-tour-id") Long createdTourId, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourService.getCreatedTourByCreatedTourId(createdTourId), HttpStatus.OK);
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
