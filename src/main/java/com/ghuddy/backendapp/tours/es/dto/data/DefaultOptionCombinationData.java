package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DefaultOptionCombinationData {
    @JsonProperty("tour_package_default_accommodation_option_id")
    private Long defaultAccommodationOptionId;
    @JsonProperty("tour_package_default_food_option_id")
    private Long defaultFoodOptionId;
    @JsonProperty("tour_package_default_transfer_option_id")
    private Long defaultTransferOptionId;
    @JsonProperty("tour_package_default_transportation_package_id")
    private Long defaultTransportationPackageId;
    @JsonProperty("tour_package_default_guide_option_id")
    private Long defaultGuideOptionId;
    @JsonProperty("tour_package_default_spot_entry_option_id")
    private Long defaultSpotEntryOptionId;

    public DefaultOptionCombinationData(Long defaultAccommodationOptionId,
                                        Long defaultFoodOptionId,
                                        Long defaultTransferOptionId,
                                        Long defaultTransportationPackageId,
                                        Long defaultGuideOptionId,
                                        Long defaultSpotEntryOptionId){
        this.defaultAccommodationOptionId = defaultAccommodationOptionId;
        this.defaultFoodOptionId = defaultFoodOptionId;
        this.defaultTransferOptionId = defaultTransferOptionId;
        this.defaultTransportationPackageId = defaultTransportationPackageId;
        this.defaultGuideOptionId = defaultGuideOptionId;
        this.defaultSpotEntryOptionId = defaultSpotEntryOptionId;
    }
}
