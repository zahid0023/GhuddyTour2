package com.ghuddy.backendapp.tours.es.serviceImpl;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourPackageDocument;
import com.ghuddy.backendapp.tours.es.repository.ESTourComponentOptionCombinationRepository;
import com.ghuddy.backendapp.tours.es.repository.ESTourPackageRepository;
import com.ghuddy.backendapp.tours.es.service.ESTourPackageService;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ESTourPackageServiceImpl implements ESTourPackageService {
    private final ESTourPackageRepository esTourPackageRepository;
    private final ESTourComponentOptionCombinationRepository esTourComponentOptionCombinationRepository;

    public ESTourPackageServiceImpl(ESTourPackageRepository esTourPackageRepository,
                                    ESTourComponentOptionCombinationRepository esTourComponentOptionCombinationRepository) {
        this.esTourPackageRepository = esTourPackageRepository;
        this.esTourComponentOptionCombinationRepository = esTourComponentOptionCombinationRepository;
    }

    /**
     * @param tourEntity
     * @param requestId
     * @return
     */
    @Override
    public Boolean indexAvailableTourPackages(TourEntity tourEntity, String requestId) {
        List<ESTourPackageDocument> esTourPackageDocumentList = getAvailableTourPackagesByTourEntity(tourEntity);
        esTourPackageRepository.saveAll(esTourPackageDocumentList);
        return true;
    }


    private List<ESTourPackageDocument> getAvailableTourPackagesByTourEntity(TourEntity tourEntity) {
        List<ESTourPackageDocument> esTourPackageDocumentList = tourEntity.getSubscribedTourEntities().stream()
                .flatMap(subscribedTourEntity -> subscribedTourEntity.getAvailableTourPackageEntities().stream()
                        .map(availableTourPackageEntity -> new ESTourPackageDocument(availableTourPackageEntity)))
                .toList();
        return esTourPackageDocumentList;
    }
}
