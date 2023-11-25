package com.ghuddy.backendapp.tours.dto.response.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class InsertAcknowledgeListResponse<T> extends BaseResponse {
    @Schema(description = "The list of the entities that were saved in the database",
            required = true)
    private List<T> entities;
    @Schema(
            description = "The action was successful",
            required = true,
            example = "Successful")
    @JsonProperty("status")
    private final String status;
    @Schema(description = "the status code of the action performed",
            required = true,
            example = "00000")
    @JsonProperty("status_code")
    private final String statusCode;

    public InsertAcknowledgeListResponse(List<T> entities, String requestId) {
        this.entities = entities;
        this.status = "Successful";
        this.statusCode = "00000";
        this.setRequestId(requestId);
    }
}
