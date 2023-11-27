package com.ghuddy.backendapp.tours.es.repository;

import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface ESTourComponentOptionCombinationRepository extends ElasticsearchRepository<ESTourComponentOptionCombinationDocument, Long> {
    List<ESTourComponentOptionCombinationDocument> findByAvailableTourPackageId(Long availableTourPackageId);

    Optional<ESTourComponentOptionCombinationDocument> findByAccommodationOptionIdAndFoodOptionIdAndTransferOptionIdAndTransportationOptionIdAndGuideOptionIdAndSpotEntryOptionId(Long accommodationOptionId, Long foodOptionId, Long transferOptionId, Long transportationOptionId, Long guideOptionId, Long spotEntryOptionId);

}
