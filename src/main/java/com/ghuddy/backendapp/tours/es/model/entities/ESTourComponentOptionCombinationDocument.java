package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.combination.AvailableComponentsAllOptionsCombinationEntity;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(indexName = "available_tour_package_component_options_combination")
public class ESTourComponentOptionCombinationDocument {
    @Id
    @Field(name = "option_id", type = FieldType.Long)
    private Long optionId;
    @Field(name = "title", type = FieldType.Text)
    private String title;
    @Field(name = "config_id", type = FieldType.Text)
    private String configId;
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

    @Field(name = "key", type = FieldType.Keyword)
    private String key;

    public ESTourComponentOptionCombinationDocument(AvailableComponentsAllOptionsCombinationEntity availableComponentsAllOptionsCombinationEntity) {
        this.optionId = availableComponentsAllOptionsCombinationEntity.getId();
        this.availableTourPackageId = availableComponentsAllOptionsCombinationEntity.getAvailableTourPackageEntity().getId();
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

        this.configId = UUID.randomUUID().toString().toUpperCase();
        this.title = availableComponentsAllOptionsCombinationEntity.getAvailableTourPackageEntity().getTourPackageTypeEntity().getPackageTypeName() + " (" + availableComponentsAllOptionsCombinationEntity.getAvailableTourPackageEntity().getTourStartDate() + ")";

        this.key = StringUtil.generateKey(
                this.availableTourPackageId,
                this.accommodationOptionId,
                this.foodOptionId,
                this.transferOptionId,
                this.transportationOptionId,
                this.guideOptionId,
                this.spotEntryOptionId
        );
    }
}
