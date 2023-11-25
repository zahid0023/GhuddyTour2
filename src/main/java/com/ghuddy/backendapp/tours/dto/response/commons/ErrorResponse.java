package com.ghuddy.backendapp.tours.dto.response.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorResponse extends BaseResponse {
    @Schema(description = "The status message of the error", required = true)
    @JsonProperty("status")
    private String status;
    @Schema(description = "The code associated with this error", required = true)
    @JsonProperty("status_code")
    private String statusCode;

    public ErrorResponse(ErrorCode statusCode, String requestId) {
        this.status = statusCode.toString();
        this.statusCode = statusCode.getCode();
        this.setRequestId(requestId);
    }

    public ErrorResponse(String status, String statusCode, String requestId) {
        this.status = status;
        this.statusCode = statusCode;
        this.setRequestId(requestId);
    }
}

