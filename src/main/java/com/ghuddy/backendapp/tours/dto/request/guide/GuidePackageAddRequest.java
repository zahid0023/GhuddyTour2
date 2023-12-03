package com.ghuddy.backendapp.tours.dto.request.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GuidePackageAddRequest extends BaseRequest {
    @Schema(description = "The subscribed tour id",required = true, example = "1")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;
    @Schema(description = "The guide package",required = true)
    @JsonProperty("tour_guide_package")
    private GuidePackageRequest guidePackageRequest;

    @Override
    public void validate() throws AbstractException {

    }
}
