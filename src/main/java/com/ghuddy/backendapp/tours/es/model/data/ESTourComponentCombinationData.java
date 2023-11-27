package com.ghuddy.backendapp.tours.es.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ESTourComponentCombinationData {
    @JsonProperty("option_combination_id")
    private Long optionCombinationId;
    @JsonProperty("available_tour_package_id")
    private Long availableTourPackageId;
    @JsonProperty("accommodation_option_id")
    private Long accommodationOptionId = Long.valueOf(0);
    @JsonProperty("food_option_id")
    private Long foodOptionId = Long.valueOf(0);
    @JsonProperty("transfer_option_id")
    private Long transferOptionId = Long.valueOf(0);
    @JsonProperty("transportation_option_id")
    private Long transportationOptionId = Long.valueOf(0);
    @JsonProperty( "guide_option_id")
    private Long guideOptionId = Long.valueOf(0);
    @JsonProperty( "spot_entry_option_id")
    private Long spotEntryOptionId = Long.valueOf(0);
    @JsonProperty("black_price")
    private BigDecimal ghuddyWebsiteBlackPrice;
    @JsonProperty( "red_price")
    private BigDecimal ghuddyWebsiteRedPrice;

    public ESTourComponentCombinationData(ESTourComponentOptionCombinationDocument esTourComponentOptionCombinationDocument){
        this.optionCombinationId = esTourComponentOptionCombinationDocument.getOptionCombinationId();
        this.availableTourPackageId = esTourComponentOptionCombinationDocument.getAvailableTourPackageId();
        this.accommodationOptionId = esTourComponentOptionCombinationDocument.getAccommodationOptionId();
        this.foodOptionId = esTourComponentOptionCombinationDocument.getFoodOptionId();
        this.transferOptionId = esTourComponentOptionCombinationDocument.getTransferOptionId();
        this.transportationOptionId = esTourComponentOptionCombinationDocument.getTransportationOptionId();
        this.guideOptionId = esTourComponentOptionCombinationDocument.getGuideOptionId();
        this.spotEntryOptionId = esTourComponentOptionCombinationDocument.getSpotEntryOptionId();
        this.ghuddyWebsiteBlackPrice = esTourComponentOptionCombinationDocument.getGhuddyWebsiteBlackPrice();
        this.ghuddyWebsiteRedPrice = esTourComponentOptionCombinationDocument.getGhuddyWebsiteRedPrice();
    }

}
