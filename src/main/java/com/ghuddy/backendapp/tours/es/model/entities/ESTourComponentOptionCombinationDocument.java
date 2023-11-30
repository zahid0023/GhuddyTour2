package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.combination.AvailableTourPackageOptionEntity;
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

    public ESTourComponentOptionCombinationDocument(AvailableTourPackageOptionEntity availableTourPackageOptionEntity) {
        this.optionId = availableTourPackageOptionEntity.getId();
        this.availableTourPackageId = availableTourPackageOptionEntity.getAvailableTourPackageEntity().getId();
        this.accommodationOptionId = availableTourPackageOptionEntity.getAvailableAccommodationOptionEntity() != null
                ? availableTourPackageOptionEntity.getAvailableAccommodationOptionEntity().getId()
                : 0;
        this.foodOptionId = availableTourPackageOptionEntity.getAvailableFoodOptionEntity() != null
                ? availableTourPackageOptionEntity.getAvailableFoodOptionEntity().getId()
                : 0;
        this.transferOptionId = availableTourPackageOptionEntity.getAvailableTransferOptionEntity() != null
                ? availableTourPackageOptionEntity.getAvailableTransferOptionEntity().getId()
                : 0;
        this.transportationOptionId = availableTourPackageOptionEntity.getAvailableTransportationPackageEntity() != null
                ? availableTourPackageOptionEntity.getAvailableTransportationPackageEntity().getId()
                : 0;
        this.guideOptionId = availableTourPackageOptionEntity.getAvailableGuideOptionEntity() != null
                ? availableTourPackageOptionEntity.getAvailableGuideOptionEntity().getId()
                : 0;
        this.spotEntryOptionId = availableTourPackageOptionEntity.getAvailableSpotEntryOptionEntity() != null
                ? availableTourPackageOptionEntity.getAvailableSpotEntryOptionEntity().getId()
                : 0;

        this.ghuddyWebsiteBlackPrice = availableTourPackageOptionEntity.getGhuddyWebsiteBlackPricePerPerson();
        this.ghuddyWebsiteRedPrice = availableTourPackageOptionEntity.getGhuddyWebsiteRedPricePerPerson();

        this.configId = UUID.randomUUID().toString().toUpperCase();
        this.title = availableTourPackageOptionEntity.getAvailableTourPackageEntity().getTourPackageTypeEntity().getPackageTypeName() + " (" + availableTourPackageOptionEntity.getAvailableTourPackageEntity().getTourStartDate() + ")";

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
