package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.*;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ESTourPackageData {
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId; // for merchant reference
    @JsonProperty("tour_package_available_tour_package_id")
    private Long availableTourPackageId;
    @JsonProperty("tour_package_available_type_id")
    private Long tourPackageTypeId;
    @JsonProperty("tour_package_available_start_date")
    private LocalDate tourStartDate;
    @JsonProperty("tour_package_available_reporting_time")
    private LocalTime tourReportingTime;
    @JsonProperty("tour_package_available_reporting_place")
    private String tourReportingPlace;
    @JsonProperty("tour_package_available_total_seats")
    private Integer totalSeats;
    @JsonProperty("tour_package_available_bookable_seats")
    private Integer bookableSeats;
    @JsonProperty("tour_package_available_is_accommodation_included")
    private Boolean isAccommodationInclusive;
    @JsonProperty("tour_package_available_is_food_included")
    private Boolean isFoodInclusive;
    @JsonProperty("tour_package_available_is_transfer_included")
    private Boolean isTransferInclusive;
    @JsonProperty("tour_package_available_is_guide_included")
    private Boolean isGuideInclusive;
    @JsonProperty("tour_package_available_is_spot_entry_included")
    private Boolean isSpotEntryInclusive;
    @JsonProperty("tour_package_available_accommodation_options")
    private List<ESAccommodationOptionData> esAccommodationOptionDataList;
    @JsonProperty("tour_package_available_food_options")
    private List<ESFoodOptionData> esFoodOptionDataList;
    @JsonProperty("tour_package_available_transfer_options")
    private List<ESTransferOptionData> esTransferOptionDataList;
    @JsonProperty("tour_package_available_transportation_options")
    private List<ESTransportationPackageData> esTransportationPackageDataList;
    @JsonProperty("tour_package_available_itinerary")
    private List<ESSubscribedTourItineraryData> esSubscribedTourItineraryDataList;

    @JsonProperty("tour_package_default_accommodation_option_id")
    private Long defaultAccommodationOptionId = 0L ;
    @JsonProperty("tour_package_default_food_option_id")
    private Long defaultFoodOptionId = 0L;
    @JsonProperty("tour_package_default_transfer_option_id")
    private Long defaultTransferOptionId = 0L;
    @JsonProperty("tour_package_default_transportation_package_id")
    private Long defaultTransportationPackageId = 0L;
    @JsonProperty("tour_package_default_guide_option_id")
    private Long defaultGuideOptionId = 0L;
    @JsonProperty("tour_package_default_spot_entry_option_id")
    private Long defaultSpotEntryOptionId = 0L;

    public ESTourPackageData(ESTourPackageDocument esTourPackageDocument) {
        this.subscribedTourId = esTourPackageDocument.getSubscribedTourId();
        this.availableTourPackageId = esTourPackageDocument.getAvailableTourPackageId();
        this.tourPackageTypeId = esTourPackageDocument.getTourPackageTypeId();
        this.tourStartDate = esTourPackageDocument.getTourStartDate();
        this.tourReportingTime = LocalTime.parse(esTourPackageDocument.getTourReportingTime());
        this.tourReportingPlace = esTourPackageDocument.getTourReportingPlace();
        this.totalSeats = esTourPackageDocument.getTotalSeats();
        this.bookableSeats = esTourPackageDocument.getBookableSeats();

        this.isAccommodationInclusive = esTourPackageDocument.getIsAccommodationInclusive();
        this.isFoodInclusive = esTourPackageDocument.getIsFoodInclusive();
        this.isTransferInclusive = esTourPackageDocument.getIsTransferInclusive();
        this.isGuideInclusive = esTourPackageDocument.getIsGuideInclusive();
        this.isSpotEntryInclusive = esTourPackageDocument.getIsSpotEntryInclusive();

        this.esAccommodationOptionDataList = getAvailableAccommodationOptions(esTourPackageDocument.getEsAccommodationOptionDocumentList());
        this.esFoodOptionDataList = getAvailableFoodOptions(esTourPackageDocument.getEsFoodOptionDataList());
        this.esTransferOptionDataList = getAvailableTransferOptions(esTourPackageDocument.getEsTransferOptionDocumentList());
        this.esTransportationPackageDataList = getAvailableTransportationPackages(esTourPackageDocument.getEsTransportationPackageDocumentList());
        this.esSubscribedTourItineraryDataList = getTourItinerary(esTourPackageDocument.getEsSubscribedTourItineraryDocumentList());

        if (isAccommodationInclusive)
            this.defaultAccommodationOptionId = esAccommodationOptionDataList.get(0).getAvailableAccommodationOptionId();
        if (isFoodInclusive)
            this.defaultFoodOptionId = 5L;
        if (isTransferInclusive)
            this.defaultTransferOptionId = esTransferOptionDataList.get(0).getAvailableTransferOptionId();

    }

    private List<ESAccommodationOptionData> getAvailableAccommodationOptions(List<ESAccommodationOptionDocument> esAccommodationOptionDocumentList) {
        return esAccommodationOptionDocumentList.stream()
                .map(esAccommodationOptionDocument -> new ESAccommodationOptionData(esAccommodationOptionDocument))
                .toList();
    }

    private List<ESFoodOptionData> getAvailableFoodOptions(List<ESFoodOptionDocument> esFoodOptionDocumentList){
     return esFoodOptionDocumentList.stream()
    .map(esFoodOptionDocument -> new ESFoodOptionData(esFoodOptionDocument))
          .toList();
    }

    private List<ESTransferOptionData> getAvailableTransferOptions(List<ESTransferOptionDocument> esTransferOptionDocumentList) {
        return esTransferOptionDocumentList.stream()
                .map(esTransferOptionDocument -> new ESTransferOptionData(esTransferOptionDocument))
                .toList();
    }

    private List<ESTransportationPackageData> getAvailableTransportationPackages(List<ESTransportationPackageDocument> esTransportationPackageDocumentList) {
        return esTransportationPackageDocumentList.stream()
                .map(esTransportationPackageDocument -> new ESTransportationPackageData(esTransportationPackageDocument))
                .toList();
    }

    private List<ESSubscribedTourItineraryData> getTourItinerary(List<ESSubscribedTourItineraryDocument> esSubscribedTourItineraryDocumentList) {
        return esSubscribedTourItineraryDocumentList.stream()
                .map(esSubscribedTourItineraryDocument -> new ESSubscribedTourItineraryData(esSubscribedTourItineraryDocument))
                .toList();
    }

}
