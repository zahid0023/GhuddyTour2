package com.ghuddy.backendapp.tours.dto.request.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class GuidePackageListAddRequest extends BaseRequest {
    @Schema(description = "The subscribed tour id", required = true, example = "1")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;
    @Schema(description = "The list of guide packages for this tour", required = true)
    @JsonProperty("guide_packages")
    private List<GuidePackageRequest> guidePackageRequestList;

    @Override
    public void validate() throws AbstractException {
        
    }
}
