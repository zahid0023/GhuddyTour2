package com.ghuddy.backendapp.tours.es.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.dto.data.ESOptionCombinationData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ESOptionCombinationPriceCheckRequest {
    @Schema(description = "The necessary id to fetch option price", required = true)
    @JsonProperty("component_options_combination")
    private ESOptionCombinationData esOptionCombinationData;
}
