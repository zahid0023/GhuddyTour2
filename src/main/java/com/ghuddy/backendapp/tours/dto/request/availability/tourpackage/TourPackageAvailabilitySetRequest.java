package com.ghuddy.backendapp.tours.dto.request.availability.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.availability.accommodation.AccommodationOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.food.FoodOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.guide.GuideOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.spot.entry.SpotEntryOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.transfer.TransferOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.transportation.TransportationPackageRequestForAvailability;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TourPackageAvailabilitySetRequest extends BaseRequest {
    @Schema(description = "Subscribed Tour Id")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;
    @Schema(description = "The tour package type id for which merchant want to generate availability", required = true, example = "1")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeId;
    @Schema(description = "The start date of the tour", required = true, example = "2023-11-14")
    @JsonProperty("tour_start_date")
    private LocalDate tourStartDate;
    @Schema(description = "The total seats available for this tour", required = true, example = "5")
    @JsonProperty("total_seats")
    private Integer totalSeats;
    @Schema(description = "Total bookable seats in tour", required = true, example = "5")
    @JsonProperty("bookable_seats")
    private Integer bookableSeats;

    @Schema(description = "Whether accommodation is inclusive in this available tour package or can be purchased")
    @JsonProperty("tour_package_accommodation_is_inclusive")
    private Boolean tourPackageAccommodationIsInclusive;
    @Schema(description = "The list of accommodation options offered in this tour package")
    @JsonProperty("tour_package_accommodation_options")
    private List<AccommodationOptionRequestForAvailability> accommodationOptionRequestForAvailabilityList;
    @Schema(description = "Whether food is inclusive in this available tour package or can be purchased")
    @JsonProperty("tour_package_food_is_inclusive")
    private Boolean tourPackageFoodIsInclusive;
    @Schema(description = "The list of food options offered in this tour package")
    @JsonProperty("tour_package_food_options")
    private List<FoodOptionRequestForAvailability> foodOptionRequestForAvailabilityList;
    @Schema(description = "Whether transfer is inclusive in this available tour package or can be purchased")
    @JsonProperty("tour_package_transfer_is_inclusive")
    private Boolean tourPackageTransferIsInclusive;
    @Schema(description = "The list of transfer options offered in this tour package")
    @JsonProperty("tour_package_transfer_options")
    private List<TransferOptionRequestForAvailability> transferOptionRequestForAvailabilityList;
    @Schema(description = "The list of transportation packages offered in this tour package")
    @JsonProperty("tour_package_transportation_packages")
    private List<TransportationPackageRequestForAvailability> transportationPackageRequestForAvailabilityList;
    @Schema(description = "Whether guide is inclusive in this available tour package or can be purchased")
    @JsonProperty("tour_package_guide_is_inclusive")
    private Boolean tourPackageGuideIsInclusive;
    @Schema(description = "The list of guide options offered in this tour package")
    @JsonProperty("tour_package_guide_options")
    private List<GuideOptionRequestForAvailability> guideOptionRequestForAvailabilityList;
    @Schema(description = "Whether spot is inclusive in this available tour package or can be purchased")
    @JsonProperty("tour_package_spot_entry_is_inclusive")
    private Boolean tourPackageSpotEntryIsInclusive;
    @Schema(description = "The list of spot entries options offered in this tour package")
    @JsonProperty("tour_package_spot_entry_options")
    private List<SpotEntryOptionRequestForAvailability> spotEntryOptionRequestForAvailabilityList;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}
