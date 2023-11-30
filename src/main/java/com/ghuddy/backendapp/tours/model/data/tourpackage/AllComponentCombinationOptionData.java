package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionPriceData;
import com.ghuddy.backendapp.tours.model.data.accommodation.AvailableAccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.AvailableFoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.AvailableGuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.AvailableSpotEntryOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailableTransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.AvailableTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableTourPackageOptionEntity;
import lombok.Data;

@Data
public class AllComponentCombinationOptionData {
    @JsonProperty("tour_package_accommodation_option")
    private AvailableAccommodationOptionData availableAccommodationOptionData;
    @JsonProperty("tour_package_food_option")
    private AvailableFoodOptionData availableFoodOptionData;
    @JsonProperty("tour_package_transfer_option")
    private AvailableTransferOptionData availableTransferOptionData;
    @JsonProperty("tour_package_transportation_package")
    private AvailableTransportationPackageData availableTransportationPackageData;
    @JsonProperty("tour_package_guide_option")
    private AvailableGuideOptionData availableGuideOptionData;
    @JsonProperty("tour_package_spot_entry_option")
    private AvailableSpotEntryOptionData availableSpotEntryOptionData;
    @JsonProperty("tour_package_option_price_details_per_person")
    private OptionPriceData optionPriceData;
    public AllComponentCombinationOptionData(AvailableTourPackageOptionEntity availableTourPackageOptionEntity){
        this.availableAccommodationOptionData = new AvailableAccommodationOptionData(availableTourPackageOptionEntity.getAvailableAccommodationOptionEntity());
        this.availableFoodOptionData = new AvailableFoodOptionData(availableTourPackageOptionEntity.getAvailableFoodOptionEntity());
        this.availableTransferOptionData = new AvailableTransferOptionData(availableTourPackageOptionEntity.getAvailableTransferOptionEntity());
        this.availableTransportationPackageData = new AvailableTransportationPackageData(availableTourPackageOptionEntity.getAvailableTransportationPackageEntity());
        this.availableGuideOptionData = new AvailableGuideOptionData(availableTourPackageOptionEntity.getAvailableGuideOptionEntity());
        this.availableSpotEntryOptionData = new AvailableSpotEntryOptionData(availableTourPackageOptionEntity.getAvailableSpotEntryOptionEntity());
        this.optionPriceData = new OptionPriceData(availableTourPackageOptionEntity);
    }
}
