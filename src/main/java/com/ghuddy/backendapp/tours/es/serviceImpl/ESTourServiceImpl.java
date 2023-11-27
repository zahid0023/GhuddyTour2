package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.es.dto.response.ESTourResponse;
import com.ghuddy.backendapp.tours.es.model.data.ESTourData;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourDocument;
import com.ghuddy.backendapp.tours.es.repository.ESTourDetailsRepository;
import com.ghuddy.backendapp.tours.es.service.ESTourPackageService;
import com.ghuddy.backendapp.tours.es.service.ESTourService;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.service.TourService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class ESTourServiceImpl implements ESTourService {
    private final TourSubscriptionService tourSubscriptionService;
    private final TourService tourService;
    private final ESTourDetailsRepository esTourDetailsRepository;
    private final ESTourPackageService esTourPackageService;

    public ESTourServiceImpl(TourSubscriptionService tourSubscriptionService,
                             TourService tourService,
                             ESTourDetailsRepository esTourDetailsRepository,
                             ESTourPackageService esTourPackageService) {
        this.tourSubscriptionService = tourSubscriptionService;
        this.tourService = tourService;
        this.esTourDetailsRepository = esTourDetailsRepository;
        this.esTourPackageService = esTourPackageService;
    }


    /**
     * @param createdTourId
     * @param requestId
     * @return
     */
    @Override
    public Boolean indexTourByTourId(Long createdTourId, String requestId) throws TourNotFoundException {
        TourEntity tourEntity = tourService.getCreatedTourEntityById(createdTourId);
        ESTourDocument esTourDocument = new ESTourDocument(tourEntity);
        esTourDetailsRepository.save(esTourDocument);
        esTourPackageService.indexAvailableTourPackages(tourEntity, requestId);
        return true;
    }


    /**
     * @param tourId
     * @param requestId
     * @return
     */
    @Override
    public ESTourResponse getTour(Long tourId, String requestId) throws TourNotFoundException {
        ESTourDocument esTourDocument = getTourById(tourId,requestId);
        ESTourData esTourData = new ESTourData(esTourDocument);
        return new ESTourResponse(esTourData);
    }

    /**
     * @param tourId
     * @param requestId
     * @return
     * @throws TourNotFoundException
     */
    @Override
    public ESTourDocument getTourById(Long tourId, String requestId) throws TourNotFoundException {
        return esTourDetailsRepository.findById(tourId).orElseThrow(() -> new TourNotFoundException(ErrorCode.TOUR_NOT_FOUND));
    }
}
