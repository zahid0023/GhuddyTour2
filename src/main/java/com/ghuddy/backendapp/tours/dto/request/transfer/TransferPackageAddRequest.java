package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransferPackageAddRequest extends BaseRequest {
    @Schema(description = "The subscribed tour id",required = true,example = "1")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourId;
    @Schema(description = "The transfer package", required = true)
    @JsonProperty("tour_transfer_package")
    private TransferPackageRequest transferPackageRequest;

    @Override
    public void validate() throws AbstractException {

    }
}
