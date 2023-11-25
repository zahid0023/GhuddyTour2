package com.ghuddy.backendapp.tours.dto.response.tour;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tour.AddedTourData;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class AddedTourListResponse extends BaseResponse {
    private List<AddedTourData> tours = new LinkedList<>();

    public AddedTourListResponse(List<AddedTourData> tours, String requestId) {
        this.tours = tours;
        this.setRequestId(requestId);
    }
}
