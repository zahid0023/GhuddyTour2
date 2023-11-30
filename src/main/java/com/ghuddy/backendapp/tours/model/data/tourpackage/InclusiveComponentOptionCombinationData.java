package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionPriceData;
import com.ghuddy.backendapp.tours.model.data.accommodation.AvailableAccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.AvailableFoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.AvailableGuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.AvailableSpotEntryOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailableTransferOptionData;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableTourPackageOptionEntity;
import lombok.Data;

@Data
public class InclusiveComponentOptionCombinationData {
    @JsonProperty("tour_package_accommodation_option")
    private AvailableAccommodationOptionData availableAccommodationOptionData;
    @JsonProperty("tour_package_food_option")
    private AvailableFoodOptionData availableFoodOptionData;
    @JsonProperty("tour_package_transfer_option")
    private AvailableTransferOptionData availableTransferOptionData;
    @JsonProperty("tour_package_guide_option")
    private AvailableGuideOptionData availableGuideOptionData;
    @JsonProperty("tour_package_spot_entry_option")
    private AvailableSpotEntryOptionData availableSpotEntryOptionData;
    @JsonProperty("tour_package_option_price_details_per_person")
    private OptionPriceData optionPriceData;
    public InclusiveComponentOptionCombinationData(AvailableTourPackageOptionEntity availableComponentsInclusiveOptionsCombinationEntity){
        this.availableAccommodationOptionData = new AvailableAccommodationOptionData(availableComponentsInclusiveOptionsCombinationEntity.getAvailableAccommodationOptionEntity());
        this.availableFoodOptionData = new AvailableFoodOptionData(availableComponentsInclusiveOptionsCombinationEntity.getAvailableFoodOptionEntity());
        this.availableTransferOptionData = new AvailableTransferOptionData(availableComponentsInclusiveOptionsCombinationEntity.getAvailableTransferOptionEntity());
        this.availableGuideOptionData = new AvailableGuideOptionData(availableComponentsInclusiveOptionsCombinationEntity.getAvailableGuideOptionEntity());
        this.availableSpotEntryOptionData = new AvailableSpotEntryOptionData(availableComponentsInclusiveOptionsCombinationEntity.getAvailableSpotEntryOptionEntity());
        this.optionPriceData = new OptionPriceData(availableComponentsInclusiveOptionsCombinationEntity);
    }
}
