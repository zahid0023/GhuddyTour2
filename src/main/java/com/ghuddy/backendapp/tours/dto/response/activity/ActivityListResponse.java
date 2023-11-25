package com.ghuddy.backendapp.tours.dto.response.activity;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityData;
import lombok.Data;

import java.util.List;

@Data
public class ActivityListResponse extends BaseResponse {
    private List<ActivityData> activities;

    public ActivityListResponse(List<ActivityData> activities, String requestId) {
        this.activities = activities;
        this.setRequestId(requestId);
    }
}
