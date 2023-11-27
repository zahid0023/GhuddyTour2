package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.accommodation.AvailableAccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.AvailableFoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.AvailableGuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.AvailableSpotEntryOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailableTransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.AvailableTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AvailableTourPackageData {
    @JsonProperty("tour_package_type_name")
    private String tourPackageTypeName;
    @JsonProperty("tour_start_date")
    private LocalDate tourStartDate;
    @JsonProperty("total_seats")
    private Integer totalSeats;
    @JsonProperty("bookable_seats")
    private Integer bookableSeats;
    @JsonProperty("is_accommodation_inclusive")
    private Boolean isAccommodationInclusive;
    @JsonProperty("is_food_inclusive")
    private Boolean isFoodInclusive;
    @JsonProperty("is_transfer_inclusive")
    private Boolean isTransferInclusive;
    @JsonProperty("is_guide_inclusive")
    private Boolean isGuideInclusive;
    @JsonProperty("is_spot_entry_inclusive")
    private Boolean isSpotEntryInclusive;
    @JsonProperty("tour_package_status")
    private String status;
    @JsonProperty("tour_package_name")
    private String tourPackageName;
    @JsonProperty("tour_package_description")
    private String tourPackageDescription;
    @JsonProperty("tour_package_available_accommodation_options")
    private List<AvailableAccommodationOptionData> availableAccommodationOptionDataList;
    @JsonProperty("tour_package_available_food_options")
    private List<AvailableFoodOptionData> availableFoodOptionDataList;
    @JsonProperty("tour_package_available_transfer_options")
    private List<AvailableTransferOptionData> availableTransferOptionDataList;
    @JsonProperty("tour_package_available_transportation_packages")
    private List<AvailableTransportationPackageData> availableTransportationPackageDataList;
    @JsonProperty("tour_package_available_guide_options")
    private List<AvailableGuideOptionData> availableGuideOptionDataList;
    @JsonProperty("tour_package_available_spot_entry_options")
    private List<AvailableSpotEntryOptionData> availableSpotEntryOptionDataList;

    public AvailableTourPackageData(AvailableTourPackageEntity availableTourPackageEntity) {
        this.tourPackageTypeName = availableTourPackageEntity.getTourPackageTypeEntity().getPackageTypeName();
        this.tourStartDate = availableTourPackageEntity.getTourStartDate();
        this.totalSeats = availableTourPackageEntity.getTotalSeats();
        this.bookableSeats = availableTourPackageEntity.getBookableSeats();
        this.isAccommodationInclusive = availableTourPackageEntity.getIsAccommodationInclusive();
        this.isFoodInclusive = availableTourPackageEntity.getIsFoodInclusive();
        this.isTransferInclusive = availableTourPackageEntity.getIsTransferInclusive();
        this.isGuideInclusive = availableTourPackageEntity.getIsGuideInclusive();
        this.isSpotEntryInclusive = availableTourPackageEntity.getIsSpotEntryInclusive();
        this.status = availableTourPackageEntity.getStatus();
        this.tourPackageName = availableTourPackageEntity.getTourPackageName();
        this.tourPackageDescription = availableTourPackageEntity.getTourPackageDescription();

        if (!listIsNullOrEmpty(availableTourPackageEntity.getAvailableAccommodationOptionEntities()))
            this.availableAccommodationOptionDataList = availableTourPackageEntity.getAvailableAccommodationOptionEntities().stream()
                    .map(availableAccommodationOptionEntity -> new AvailableAccommodationOptionData(availableAccommodationOptionEntity))
                    .toList();

        if (!listIsNullOrEmpty(availableTourPackageEntity.getAvailableFoodOptionEntities()))
            this.availableFoodOptionDataList = availableTourPackageEntity.getAvailableFoodOptionEntities().stream()
                    .map(availableFoodOptionEntity -> new AvailableFoodOptionData(availableFoodOptionEntity))
                    .toList();

        if (!listIsNullOrEmpty(availableTourPackageEntity.getAvailableTransferOptionEntities()))
            this.availableTransferOptionDataList = availableTourPackageEntity.getAvailableTransferOptionEntities().stream()
                    .map(availableTransferOptionEntity -> new AvailableTransferOptionData(availableTransferOptionEntity))
                    .toList();

        if (!listIsNullOrEmpty(availableTourPackageEntity.getAvailableTransportationPackageEntities()))
            this.availableTransportationPackageDataList = availableTourPackageEntity.getAvailableTransportationPackageEntities().stream()
                    .map(availableTransportationPackageEntity -> new AvailableTransportationPackageData(availableTransportationPackageEntity))
                    .toList();

        if (!listIsNullOrEmpty(availableTourPackageEntity.getAvailableGuideOptionEntities()))
            this.availableGuideOptionDataList = availableTourPackageEntity.getAvailableGuideOptionEntities().stream()
                    .map(availableGuideOptionEntity -> new AvailableGuideOptionData(availableGuideOptionEntity))
                    .toList();

        if (!listIsNullOrEmpty(availableTourPackageEntity.getAvailableSpotEntryOptionEntities()))
            this.availableSpotEntryOptionDataList = availableTourPackageEntity.getAvailableSpotEntryOptionEntities().stream()
                    .map(availableSpotEntryOptionEntity -> new AvailableSpotEntryOptionData(availableSpotEntryOptionEntity))
                    .toList();
    }

    private boolean listIsNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
