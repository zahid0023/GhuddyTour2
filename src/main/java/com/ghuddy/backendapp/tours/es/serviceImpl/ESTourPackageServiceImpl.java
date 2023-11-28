package com.ghuddy.backendapp.tours.es.serviceImpl;
import com.ghuddy.backendapp.tours.es.dto.response.ESTourPackageResponse;
import com.ghuddy.backendapp.tours.es.model.data.ESTourPackageData;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourPackageDocument;
import com.ghuddy.backendapp.tours.es.repository.ESTourComponentOptionCombinationRepository;
import com.ghuddy.backendapp.tours.es.repository.ESTourPackageRepository;
import com.ghuddy.backendapp.tours.es.service.ESTourPackageService;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    /**
     * @param tourEntity
     * @param requestId
     * @return
     */
    @Override
    public Boolean indexAvailableTourPackagesOptionsCombinations(TourEntity tourEntity, String requestId) {
        List<ESTourComponentOptionCombinationDocument> esTourComponentOptionCombinationDocumentList = tourEntity.getSubscribedTourEntities().stream()
                .flatMap(subscribedTourEntity -> subscribedTourEntity.getAvailableTourPackageEntities().stream()
                        .flatMap(availableTourPackageEntity -> getAllComponentOptionsCombinations(availableTourPackageEntity).stream()))
                .collect(Collectors.toList());
        esTourComponentOptionCombinationRepository.saveAll(esTourComponentOptionCombinationDocumentList);
        return true;
    }

    private List<ESTourComponentOptionCombinationDocument> getAllComponentOptionsCombinations(AvailableTourPackageEntity availableTourPackageEntity) {
        List<ESTourComponentOptionCombinationDocument> allOptions = availableTourPackageEntity.getAvailableComponentsAllOptionsCombinationEntities().stream()
                .map(availableComponentsAllOptionsCombinationEntity -> new ESTourComponentOptionCombinationDocument(availableComponentsAllOptionsCombinationEntity))
                .collect(Collectors.toList());
        log.info(allOptions.toString());
        List<ESTourComponentOptionCombinationDocument> inclusiveOptions = availableTourPackageEntity.getAvailableComponentsInclusiveOptionEntities().stream()
                .map(inclusiveOptionsCombinationEntity -> new ESTourComponentOptionCombinationDocument(inclusiveOptionsCombinationEntity))
                .collect(Collectors.toList());
        log.info(inclusiveOptions.toString());

        allOptions.addAll(inclusiveOptions);
        return allOptions.stream().distinct().toList();
    }

    /**
     * @param tourId
     * @return
     */
    @Override
    public ESTourPackageResponse getAvailableTourPackagesByTourId(Long tourId, String requestId) {
        return new ESTourPackageResponse(organizeAvailableTourPackagesByTourPackageType(getAllAvailableTourPackagesByTourId(tourId)), requestId);
    }

    private Map<Long, List<ESTourPackageData>> organizeAvailableTourPackagesByTourPackageType(List<ESTourPackageDocument> esTourPackageDocumentList) {
        // Organize the packages by their tourPackageTypeId using computeIfAbsent
        Map<Long, List<ESTourPackageData>> idTourPackagesMap = new HashMap<>();

        esTourPackageDocumentList.forEach(esTourPackageDocument -> {
            ESTourPackageData esTourPackageData = new ESTourPackageData(esTourPackageDocument);
            idTourPackagesMap
                    .computeIfAbsent(esTourPackageData.getTourPackageTypeId(), key -> new LinkedList<>())
                    .add(esTourPackageData);
        });
        return idTourPackagesMap;
    }

    private List<ESTourPackageDocument> getAllAvailableTourPackagesByTourId(Long tourId) {
        return esTourPackageRepository.findAllByTourId(tourId);
    }
}
