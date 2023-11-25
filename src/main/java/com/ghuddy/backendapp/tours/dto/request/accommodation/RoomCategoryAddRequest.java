package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoomCategoryAddRequest extends BaseRequest {
    @Schema(description = "The room category that will be stored in the database", required = true)
    @JsonProperty("room_category")
    private RoomCategoryRequest roomCategoryRequest;

    @Override
    public void validate() throws AbstractException {

    }
}
