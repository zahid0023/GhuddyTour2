package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.image.ImageRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import com.ghuddy.backendapp.tours.repository.ActivityImageRepository;
import com.ghuddy.backendapp.tours.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final ActivityImageRepository activityImageRepository;

    public ImageServiceImpl(ActivityImageRepository activityImageRepository) {
        this.activityImageRepository = activityImageRepository;
    }

    @Override
    public AcknowledgeResponse addImage(ImageRequest imageRequest) {
        return null;
    }

    @Override
    public AcknowledgeResponse addActivityImages(ActivityEntity activityEntity, List<ImageRequest> activityImages) {

        return new AcknowledgeResponse();
    }
}
