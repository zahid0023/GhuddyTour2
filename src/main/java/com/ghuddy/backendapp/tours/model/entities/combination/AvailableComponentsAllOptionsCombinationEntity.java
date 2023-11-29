package com.ghuddy.backendapp.tours.model.entities.combination;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailableTransportationPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "available_components_all_options_combinations")
public class AvailableComponentsAllOptionsCombinationEntity extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "available_tour_package_id", nullable = false)
    private AvailableTourPackageEntity availableTourPackageEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_accommodation_option_id")
    private AvailableAccommodationOptionEntity availableAccommodationOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_food_option_id")
    private AvailableFoodOptionEntity availableFoodOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_transfer_option_id")
    private AvailableTransferOptionEntity availableTransferOptionEntity;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_transportation_package_id")
    private AvailableTransportationPackageEntity availableTransportationPackageEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_guide_option_id")
    private AvailableGuideOptionEntity availableGuideOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_spot_entry_option_id")
    private AvailableSpotEntryOptionEntity availableSpotEntryOptionEntity;

    @NotNull
    @Column(name = "ghuddy_platform_calculated_option_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyPlatformCalculatedOptionPricePerPerson;

    @NotNull
    @Column(name = "merchant_subsidy_amount_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal merchantSubsidyAmountPerPerson;

    @NotNull
    @Column(name = "net_option_price_per_person_after_merchant_subsidy", nullable = false, precision = 10, scale = 2)
    private BigDecimal netOptionPricePerPersonAfterMerchantSubsidy;

    @NotNull
    @Column(name = "ghuddy_platform_commission_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyPlatformCommissionAmount;

    @NotNull
    @Column(name = "net_option_price_per_person_after_ghuddy_commission", nullable = false, precision = 10, scale = 2)
    private BigDecimal netOptionPricePerPersonAfterGhuddyCommission;

    @NotNull
    @Column(name = "ghuddy_website_black_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyWebsiteBlackPricePerPerson;

    @NotNull
    @Column(name = "ghuddy_subsidy_amount_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddySubsidyAmountPerPerson;

    @NotNull
    @Column(name = "net_option_price_per_person_after_ghuddy_subsidy", nullable = false, precision = 10, scale = 2)
    private BigDecimal netOptionPricePerPersonAfterGhuddySubsidy;

    @NotNull
    @Column(name = "ghuddy_website_red_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyWebsiteRedPricePerPerson;

    @NotNull
    @Column(name = "payment_gateway_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal paymentGatewayAmount;


    @NotNull
    @Column(name = "is_inclusive_options", nullable = false)
    private Boolean isInclusiveOptions = false;

}