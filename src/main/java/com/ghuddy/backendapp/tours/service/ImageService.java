package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.image.ImageRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;

import java.util.List;

public interface ImageService {
    AcknowledgeResponse addImage(ImageRequest imageRequest);

    AcknowledgeResponse addActivityImages(ActivityEntity activityEntity, List<ImageRequest> activityImages);
}
