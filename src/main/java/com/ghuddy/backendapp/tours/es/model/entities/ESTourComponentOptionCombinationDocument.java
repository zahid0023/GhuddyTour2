package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.combination.AvailableComponentsAllOptionsCombinationEntity;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableComponentsInclusiveOptionsCombinationEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document(indexName = "available_tour_package_component_options_combination")
public class ESTourComponentOptionCombinationDocument {
    @Id
    @Field(name = "option_combination_id")
    private Long optionCombinationId;
    @Field(name = "available_tour_package_id", type = FieldType.Long)
    private Long availableTourPackageId;
    @Field(name = "accommodation_option_id", type = FieldType.Long)
    private Long accommodationOptionId;
    @Field(name = "food_option_id", type = FieldType.Long)
    private Long foodOptionId;
    @Field(name = "transfer_option_id", type = FieldType.Long)
    private Long transferOptionId;
    @Field(name = "transportation_option_id", type = FieldType.Long)
    private Long transportationOptionId;
    @Field(name = "guide_option_id", type = FieldType.Long)
    private Long guideOptionId;
    @Field(name = "spot_entry_option_id", type = FieldType.Long)
    private Long spotEntryOptionId;
    @Field(name = "black_price", type = FieldType.Double)
    private BigDecimal ghuddyWebsiteBlackPrice;
    @Field(name = "red_price", type = FieldType.Double)
    private BigDecimal ghuddyWebsiteRedPrice;

    public ESTourComponentOptionCombinationDocument(AvailableComponentsInclusiveOptionsCombinationEntity availableComponentsInclusiveOptionsCombinationEntity) {
        this.optionCombinationId = availableComponentsInclusiveOptionsCombinationEntity.getId();
        this.availableTourPackageId = availableComponentsInclusiveOptionsCombinationEntity.getAvailableTourPackageEntity() != null
                ? availableComponentsInclusiveOptionsCombinationEntity.getAvailableTourPackageEntity().getId()
                : 0;
        this.accommodationOptionId = availableComponentsInclusiveOptionsCombinationEntity.getAvailableAccommodationOptionEntity() != null
                ? availableComponentsInclusiveOptionsCombinationEntity.getAvailableAccommodationOptionEntity().getId()
                : 0;
        this.foodOptionId = availableComponentsInclusiveOptionsCombinationEntity.getAvailableFoodOptionEntity() != null
                ? availableComponentsInclusiveOptionsCombinationEntity.getAvailableFoodOptionEntity().getId()
                : 0;
        this.transferOptionId = availableComponentsInclusiveOptionsCombinationEntity.getAvailableTransferOptionEntity() != null
                ? availableComponentsInclusiveOptionsCombinationEntity.getAvailableTransferOptionEntity().getId()
                : 0;
        this.guideOptionId = availableComponentsInclusiveOptionsCombinationEntity.getAvailableGuideOptionEntity() != null
                ? availableComponentsInclusiveOptionsCombinationEntity.getAvailableGuideOptionEntity().getId()
                : 0;
        this.spotEntryOptionId = availableComponentsInclusiveOptionsCombinationEntity.getAvailableSpotEntryOptionEntity() != null
                ? availableComponentsInclusiveOptionsCombinationEntity.getAvailableSpotEntryOptionEntity().getId()
                : 0;

        this.ghuddyWebsiteBlackPrice = availableComponentsInclusiveOptionsCombinationEntity.getGhuddyWebsiteBlackPricePerPerson();
        this.ghuddyWebsiteRedPrice = availableComponentsInclusiveOptionsCombinationEntity.getGhuddyWebsiteRedPricePerPerson();
    }



    public ESTourComponentOptionCombinationDocument(AvailableComponentsAllOptionsCombinationEntity availableComponentsAllOptionsCombinationEntity) {
        this.optionCombinationId = availableComponentsAllOptionsCombinationEntity.getId();
        this.availableTourPackageId = availableComponentsAllOptionsCombinationEntity.getAvailableTourPackageEntity() != null
                ? availableComponentsAllOptionsCombinationEntity.getAvailableTourPackageEntity().getId()
                : 0;
        this.accommodationOptionId = availableComponentsAllOptionsCombinationEntity.getAvailableAccommodationOptionEntity() != null
                ? availableComponentsAllOptionsCombinationEntity.getAvailableAccommodationOptionEntity().getId()
                : 0;
        this.foodOptionId = availableComponentsAllOptionsCombinationEntity.getAvailableFoodOptionEntity() != null
                ? availableComponentsAllOptionsCombinationEntity.getAvailableFoodOptionEntity().getId()
                : 0;
        this.transferOptionId = availableComponentsAllOptionsCombinationEntity.getAvailableTransferOptionEntity() != null
                ? availableComponentsAllOptionsCombinationEntity.getAvailableTransferOptionEntity().getId()
                : 0;
        this.transportationOptionId = availableComponentsAllOptionsCombinationEntity.getAvailableTransportationPackageEntity() != null
                ? availableComponentsAllOptionsCombinationEntity.getAvailableTransportationPackageEntity().getId()
                : 0;
        this.guideOptionId = availableComponentsAllOptionsCombinationEntity.getAvailableGuideOptionEntity() != null
                ? availableComponentsAllOptionsCombinationEntity.getAvailableGuideOptionEntity().getId()
                : 0;
        this.spotEntryOptionId = availableComponentsAllOptionsCombinationEntity.getAvailableSpotEntryOptionEntity() != null
                ? availableComponentsAllOptionsCombinationEntity.getAvailableSpotEntryOptionEntity().getId()
                : 0;

        this.ghuddyWebsiteBlackPrice = availableComponentsAllOptionsCombinationEntity.getGhuddyWebsiteBlackPricePerPerson();
        this.ghuddyWebsiteRedPrice = availableComponentsAllOptionsCombinationEntity.getGhuddyWebsiteRedPricePerPerson();
    }
}
