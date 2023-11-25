package com.ghuddy.backendapp.tours.model.data.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationProviderEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransportationProviderData {
    @Schema(description = "The id of the transportation provider", example = "1")
    @JsonProperty("transportation_provider_id")
    private Long transportationProviderId;
    @Schema(description = "The name of the transportation provider", example = "Hanif")
    @JsonProperty("transportation_provider_name")
    private String transportationProviderName;
    @Schema(description = "The hot line number of the transportation provider")
    @JsonProperty("transportation_provider_hot_line_number")
    private String transportationProviderHotLineNumber;
    @Schema(description = "The average rating of the transportation provider", example = "4.7")
    @JsonProperty("transportation_provider_rating")
    private BigDecimal transportationProviderRating;
    @Schema(description = "The total reviews for this transportation provider", example = "120")
    @JsonProperty("transportation_provider_total_reviews")
    private Integer transportationProviderTotalReviews;

    public TransportationProviderData(TransportationProviderEntity transportationProviderEntity) {
        this.transportationProviderId = transportationProviderEntity.getId();
        this.transportationProviderName = transportationProviderEntity.getTransportationProviderName();
        this.transportationProviderHotLineNumber = transportationProviderEntity.getHotlineNumber();
        this.transportationProviderRating = transportationProviderEntity.getRating();
        this.transportationProviderTotalReviews = transportationProviderEntity.getTotalReviews();
    }
}
