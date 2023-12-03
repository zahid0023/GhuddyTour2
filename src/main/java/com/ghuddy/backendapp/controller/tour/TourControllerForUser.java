package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.response.commons.ErrorResponse;
import com.ghuddy.backendapp.tours.dto.request.sales.BillCalculationRequest;
import com.ghuddy.backendapp.tours.es.dto.data.ESOptionCombinationData;
import com.ghuddy.backendapp.tours.es.dto.response.ESBillCalculationResponse;
import com.ghuddy.backendapp.tours.es.service.ESBillCalculationService;
import com.ghuddy.backendapp.tours.es.service.ESOptionsCombinationService;
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
    private final ESOptionsCombinationService esOptionsCombinationService;
    private final ESBillCalculationService esBillCalculationService;

    public TourControllerForUser(TourService tourService,
                                 TourSubscriptionService tourSubscriptionService,
                                 ESTourService esTourService,
                                 ESTourPackageService esTourPackageService,
                                 ESOptionsCombinationService esOptionsCombinationService,
                                 ESBillCalculationService esBillCalculationService) {
        this.tourService = tourService;
        this.tourSubscriptionService = tourSubscriptionService;
        this.esTourService = esTourService;
        this.esTourPackageService = esTourPackageService;
        this.esOptionsCombinationService = esOptionsCombinationService;
        this.esBillCalculationService = esBillCalculationService;
    }

    @RequestMapping(path = "/tours/get/tour/details/by/{tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourDetailsByTourId(@PathVariable("tour-id") Long tourId, @RequestParam String requestId) {
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

    @RequestMapping(path = "/tours/get/component/option/price", method = RequestMethod.GET)
    public ResponseEntity<?> getComponentOptionPrice(
            @RequestParam("available_tour_package_id") Long availableTourPackageId,
            @RequestParam("tour_package_accommodation_option_id") Long accommodationOptionId,
            @RequestParam("tour_package_food_option_id") Long foodOptionId,
            @RequestParam("tour_package_transfer_option_id") Long transferOptionId,
            @RequestParam("tour_package_transportation_package_id") Long transportationPackageId,
            @RequestParam("tour_guide_guide_option_id") Long guideOptionId,
            @RequestParam("tour_package_spot_entry_option_id") Long spotEntryId,
            @RequestParam String requestId) {

        ESOptionCombinationData esTourPackagePriceCalculationData = new ESOptionCombinationData(availableTourPackageId, accommodationOptionId, foodOptionId, transferOptionId, transportationPackageId, guideOptionId, spotEntryId);

        return new ResponseEntity<>(esOptionsCombinationService.getComponentCombinationPrice(esTourPackagePriceCalculationData, requestId), HttpStatus.OK);
    }

    @RequestMapping(value = "/tours/bill-calculation/for-query", method = RequestMethod.POST)
    public ResponseEntity<ESBillCalculationResponse> calculateBill(@RequestBody BillCalculationRequest request) throws IOException, AbstractException {
        return new ResponseEntity<>(esBillCalculationService.calculateBill(request), HttpStatus.OK);
    }
}
