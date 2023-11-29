package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.es.dto.data.ESOptionCombinationData;
import com.ghuddy.backendapp.tours.es.dto.response.ESComponentCombinationCheckResponse;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.es.repository.ESTourComponentOptionCombinationRepository;
import com.ghuddy.backendapp.tours.es.service.ESOptionsCombinationService;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ESOptionsCombinationServiceImpl implements ESOptionsCombinationService {
    private final ESTourComponentOptionCombinationRepository esTourComponentOptionCombinationRepository;

    public ESOptionsCombinationServiceImpl(ESTourComponentOptionCombinationRepository esTourComponentOptionCombinationRepository) {
        this.esTourComponentOptionCombinationRepository = esTourComponentOptionCombinationRepository;
    }

    /**
     * @param tourEntity
     * @param requestId
     * @return
     */
    @Override
    public Boolean indexOptionsCombinations(TourEntity tourEntity, String requestId) {
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
        return allOptions.stream().distinct().toList();
    }

    @Override
    public ESComponentCombinationCheckResponse getComponentCombinationPrice(ESOptionCombinationData esOptionCombinationData, String requestId) {
        log.info(esOptionCombinationData.toString());
        Optional<ESTourComponentOptionCombinationDocument> existingEsTourComponentOptionCombinationDocument = esTourComponentOptionCombinationRepository.findByAvailableTourPackageIdAndAccommodationOptionIdAndFoodOptionIdAndTransferOptionIdAndTransportationOptionIdAndGuideOptionIdAndSpotEntryOptionId(
                esOptionCombinationData.getAvailableTourPackageId(),
                esOptionCombinationData.getAccommodationOptionId(),
                esOptionCombinationData.getFoodOptionId(),
                esOptionCombinationData.getTransferOptionId(),
                esOptionCombinationData.getTransportationPackageId(),
                esOptionCombinationData.getGuideOptionId(),
                esOptionCombinationData.getSpotEntryId()
        );
        if (existingEsTourComponentOptionCombinationDocument.isPresent()) {
            log.info(existingEsTourComponentOptionCombinationDocument.get().toString());
            return new ESComponentCombinationCheckResponse(existingEsTourComponentOptionCombinationDocument.get().getGhuddyWebsiteBlackPrice(), existingEsTourComponentOptionCombinationDocument.get().getGhuddyWebsiteRedPrice());
        } else throw new EntityNotFoundException("Option Not Found");
    }
}
