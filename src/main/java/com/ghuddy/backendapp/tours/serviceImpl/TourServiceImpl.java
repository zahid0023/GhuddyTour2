package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.TourDAO;
import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tour.TourListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.data.tour.TourData;
import com.ghuddy.backendapp.tours.model.entities.tour.*;
import com.ghuddy.backendapp.tours.repository.TourRepository;
import com.ghuddy.backendapp.tours.service.AddedTourService;
import com.ghuddy.backendapp.tours.service.SpecialityService;
import com.ghuddy.backendapp.tours.service.TourItineraryService;
import com.ghuddy.backendapp.tours.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class TourServiceImpl implements TourService {
    private final SpecialityService specialityService;
    private final TourItineraryService tourItineraryService;
    private final TourRepository tourRepository;
    private final AddedTourService addedTourService;
    private final TourDAO tourDAO;

    public TourServiceImpl(SpecialityService specialityService,
                           TourItineraryService tourItineraryService,
                           TourRepository tourRepository,
                           AddedTourService addedTourService,
                           TourDAO tourDAO) {
        this.specialityService = specialityService;
        this.tourItineraryService = tourItineraryService;
        this.tourRepository = tourRepository;
        this.addedTourService = addedTourService;
        this.tourDAO = tourDAO;
    }

    // created tour
    @Transactional
    @Override
    public InsertAcknowledgeResponse<TourData> createTour(TourCreateRequest tourCreateRequest) throws TourNotFoundException {
        AddedTourEntity addedTourEntity = addedTourService.getAddedTourEntityById(tourCreateRequest.getAddedTourID());

        TourEntity tourEntity = new TourEntity();
        tourEntity.setTitle(tourCreateRequest.getTitle());
        tourEntity.setDescription(tourCreateRequest.getDescription());
        tourEntity.setThumbImageUrl(tourCreateRequest.getThumbImageURL());
        tourEntity.setAddedTourEntity(addedTourEntity);

        List<TourItineraryEntity> tourItineraryEntities = tourItineraryService.setTourActivities(tourEntity, tourCreateRequest.getTourActivityIds());
        tourEntity.setTourItineraryEntities(tourItineraryEntities);

        if (tourCreateRequest.getTourSpecialities() != null && !tourCreateRequest.getTourSpecialities().isEmpty()) {
            List<TourSpecialityEntity> tourSpecialityEntities = specialityService.setTourSpecialities(tourEntity, tourCreateRequest.getTourSpecialities());
            tourEntity.setTourSpecialityEntities(tourSpecialityEntities);
        } else {
            log.warn("No Speciality added!!");
        }

        tourEntity = tourRepository.save(tourEntity);
        return new InsertAcknowledgeResponse<>(new TourData(tourEntity), tourCreateRequest.getRequestId());
    }

    /**
     * @param createdTourId the of the created tour entity
     * @return TourEntity
     * @throws TourNotFoundException when the created tour entity by this id is not found
     */
    @Override
    public TourEntity getCreatedTourEntityById(Long createdTourId) throws TourNotFoundException {
        return tourRepository.findById(createdTourId).orElseThrow(() -> new TourNotFoundException(ErrorCode.TOUR_NOT_FOUND));
    }

    /**
     * @param createdTourEntityId the id of the created tour
     * @return CreatedTourData
     * @throws TourNotFoundException when the created tour by this id is not found
     */
    @Override
    public TourData getCreatedTourByCreatedTourId(Long createdTourEntityId) throws TourNotFoundException {
        return new TourData(getCreatedTourEntityById(createdTourEntityId));
    }

    /**
     * @param requestId the id of the request
     * @return TourDataResponseList
     * @throws EmptyListException when the list of tour is empty
     */
    @Override
    public TourListResponse getAllCreatedTours(String requestId) throws EmptyListException {
        List<TourEntity> tourEntityList = tourRepository.findAll();
        if (tourEntityList == null || tourEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<TourData> tourDataList = tourEntityList.stream()
                .map(tourEntity -> new TourData(tourEntity))
                .toList();
        return new TourListResponse(tourDataList, requestId);
    }

    /**
     * @param pageSize   the size of the page
     * @param pageNumber the page number
     * @return TourDataResponseList
     * @throws EmptyListException when the list of tours is empty
     */
    @Override
    public TourListResponse getAllCreatedToursPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<TourEntity> tourEntityList = tourRepository.findAll(pageable).getContent();

        if (tourEntityList == null || tourEntityList.isEmpty()) {
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        }

        List<TourData> tourDataList = tourEntityList.stream()
                .map(TourData::new)
                .toList();
        return new TourListResponse(tourDataList, requestId);
    }
}
