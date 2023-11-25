package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.AccommodationDao;
import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourAccommodationTypeListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomCategoryListResponse;
import com.ghuddy.backendapp.tours.dto.response.accommodation.TourRoomTypeListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.accommodation.*;
import com.ghuddy.backendapp.tours.model.entities.accommodation.*;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.repository.*;
import com.ghuddy.backendapp.tours.service.AccommodationService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final TourRoomTypeRepository tourRoomTypeRepository;
    private final TourRoomCategoryRepository tourRoomCategoryRepository;
    private final TourAccommodationTypeRepository tourAccommodationTypeRepository;
    private final TourAccommodationRepository tourAccommodationRepository;
    private final AccommodationPackageRepository accommodationPackageRepository;
    private final AccommodationDao accommodationDao;

    public AccommodationServiceImpl(TourRoomTypeRepository tourRoomTypeRepository,
                                    TourRoomCategoryRepository tourRoomCategoryRepository,
                                    TourAccommodationTypeRepository tourAccommodationTypeRepository,
                                    TourAccommodationRepository tourAccommodationRepository,
                                    AccommodationPackageRepository accommodationPackageRepository,
                                    AccommodationDao accommodationDao) {
        this.tourRoomTypeRepository = tourRoomTypeRepository;
        this.tourRoomCategoryRepository = tourRoomCategoryRepository;
        this.tourAccommodationTypeRepository = tourAccommodationTypeRepository;
        this.tourAccommodationRepository = tourAccommodationRepository;
        this.accommodationPackageRepository = accommodationPackageRepository;
        this.accommodationDao = accommodationDao;
    }

    // room type
    @Override
    public InsertAcknowledgeResponse<TourRoomTypeData> addRoomType(RoomTypeAddRequest roomTypeAddRequest) {
        TourRoomTypeData tourRoomTypeData = addRoomTypes(List.of(roomTypeAddRequest.getRoomType())).get(0);
        return new InsertAcknowledgeResponse<>(tourRoomTypeData, roomTypeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addRoomTypes(RoomTypeListAddRequest roomTypeListAddRequest) {
        List<TourRoomTypeData> tourRoomTypeDataList = addRoomTypes(roomTypeListAddRequest.getRoomTypes());
        return new InsertAcknowledgeListResponse<>(tourRoomTypeDataList, roomTypeListAddRequest.getRequestId());
    }

    @Transactional
    public List<TourRoomTypeData> addRoomTypes(List<RoomTypeRequest> roomTypes) {
        List<TourRoomTypeEntity> tourRoomTypeEntities = roomTypes.stream()
                .map(roomTypeRequest -> {
                    TourRoomTypeEntity tourRoomTypeEntity = new TourRoomTypeEntity();
                    tourRoomTypeEntity.setRoomTypeName(roomTypeRequest.getRoomTypeName());
                    tourRoomTypeEntity.setDescription(roomTypeRequest.getDescription());
                    return tourRoomTypeEntity;
                })
                .collect(Collectors.toList());

        List<TourRoomTypeEntity> tourRoomTypeEntityList = tourRoomTypeRepository.saveAll(tourRoomTypeEntities);

        List<TourRoomTypeData> tourRoomTypeDataList = tourRoomTypeEntityList.stream()
                .map(tourRoomTypeEntity -> {
                    TourRoomTypeData tourRoomTypeData = new TourRoomTypeData(tourRoomTypeEntity);
                    return tourRoomTypeData;
                })
                .collect(Collectors.toList());
        return tourRoomTypeDataList;
    }

    @Override
    public TourRoomTypeListResponse getAllTourRoomTypes(String requestId) throws EmptyListException {
        List<TourRoomTypeEntity> tourRoomTypeEntityList = tourRoomTypeRepository.findAll();
        if (tourRoomTypeEntityList == null || tourRoomTypeEntityList.isEmpty())
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<TourRoomTypeData> tourRoomTypeDataList = tourRoomTypeEntityList.stream()
                .map(tourRoomTypeEntity -> new TourRoomTypeData(tourRoomTypeEntity))
                .toList();
        return new TourRoomTypeListResponse(tourRoomTypeDataList, requestId);
    }

    @Override
    public TourRoomTypeListResponse getAllTourRoomTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<TourRoomTypeEntity> tourRoomTypeEntityList = tourRoomTypeRepository.findAll(pageable).getContent();
        if (tourRoomTypeEntityList == null || tourRoomTypeEntityList.isEmpty())
            throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<TourRoomTypeData> tourRoomTypeDataList = tourRoomTypeEntityList.stream()
                .map(tourRoomTypeEntity -> new TourRoomTypeData(tourRoomTypeEntity))
                .toList();
        return new TourRoomTypeListResponse(tourRoomTypeDataList, requestId);
    }

    // room category
    @Override
    public InsertAcknowledgeResponse addRoomCategory(RoomCategoryAddRequest roomCategoryAddRequest) {
        TourRoomCategoryData tourRoomCategoryData = addRoomCategories(List.of(roomCategoryAddRequest.getRoomCategoryRequest())).get(0);
        return new InsertAcknowledgeResponse(tourRoomCategoryData, roomCategoryAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addRoomCategories(RoomCategoryListAddRequest roomCategoryListAddRequest) {
        List<TourRoomCategoryData> tourRoomCategoryDataList = addRoomCategories(roomCategoryListAddRequest.getRoomCategoryRequestList());
        return new InsertAcknowledgeListResponse(tourRoomCategoryDataList, roomCategoryListAddRequest.getRequestId());
    }

    @Transactional
    public List<TourRoomCategoryData> addRoomCategories(List<RoomCategoryRequest> roomCategories) {
        List<TourRoomCategoryEntity> tourRoomCategoryEntities = roomCategories.stream()
                .map(roomCategoryRequest -> {
                    TourRoomCategoryEntity tourRoomCategoryEntity = new TourRoomCategoryEntity();
                    tourRoomCategoryEntity.setRoomCategoryName(roomCategoryRequest.getRoomCategoryName());
                    tourRoomCategoryEntity.setDescription(roomCategoryRequest.getDescription());
                    return tourRoomCategoryEntity;
                })
                .collect(Collectors.toList());
        List<TourRoomCategoryEntity> tourRoomCategoryEntityList = tourRoomCategoryRepository.saveAll(tourRoomCategoryEntities);
        List<TourRoomCategoryData> tourRoomCategoryDataList = tourRoomCategoryEntityList.stream()
                .map(tourRoomCategoryEntity -> new TourRoomCategoryData(tourRoomCategoryEntity))
                .collect(Collectors.toList());
        return tourRoomCategoryDataList;
    }

    @Override
    public TourRoomCategoryListResponse getAllTourRoomCategories(String requestId) throws EmptyListException {
        List<TourRoomCategoryEntity> tourRoomCategoryEntityList = tourRoomCategoryRepository.findAll();
        if (tourRoomCategoryEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<TourRoomCategoryData> tourRoomCategoryDataList = tourRoomCategoryEntityList.stream()
                .map(tourRoomCategoryEntity -> new TourRoomCategoryData(tourRoomCategoryEntity))
                .toList();
        return new TourRoomCategoryListResponse(tourRoomCategoryDataList, requestId);
    }

    @Override
    public TourRoomCategoryListResponse getAllTourRoomCategoriesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<TourRoomCategoryEntity> tourRoomCategoryEntityList = tourRoomCategoryRepository.findAll(pageable).getContent();
        if (tourRoomCategoryEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<TourRoomCategoryData> tourRoomCategoryDataList = tourRoomCategoryEntityList.stream()
                .map(tourRoomCategoryEntity -> new TourRoomCategoryData(tourRoomCategoryEntity))
                .toList();
        return new TourRoomCategoryListResponse(tourRoomCategoryDataList, requestId);
    }

    // accommodation type
    @Override
    public InsertAcknowledgeResponse addAccommodationType(AccommodationTypeAddRequest accommodationTypeAddRequest) {
        AccommodationTypeData accommodationTypeData = addAccommodationTypes(List.of(accommodationTypeAddRequest.getAccommodationTypeRequest())).get(0);
        return new InsertAcknowledgeResponse(accommodationTypeData, accommodationTypeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addAccommodationTypes(AccommodationTypeListAddRequest accommodationTypeListAddRequest) {
        List<AccommodationTypeData> accommodationTypeDataList = addAccommodationTypes(accommodationTypeListAddRequest.getAccommodationTypeRequestList());
        return new InsertAcknowledgeListResponse(accommodationTypeDataList, accommodationTypeListAddRequest.getRequestId());
    }

    @Transactional
    public List<AccommodationTypeData> addAccommodationTypes(List<AccommodationTypeRequest> accommodations) {
        List<TourAccommodationTypeEntity> tourAccommodationEntities = accommodations.stream()
                .map(accommodationTypeRequest -> {
                    TourAccommodationTypeEntity tourAccommodationTypeEntity = new TourAccommodationTypeEntity();
                    tourAccommodationTypeEntity.setAccommodationTypeName(accommodationTypeRequest.getAccommodationTypeName());
                    return tourAccommodationTypeEntity;
                })
                .collect(Collectors.toList());

        return tourAccommodationTypeRepository.saveAll(tourAccommodationEntities)
                .stream()
                .map(tourAccommodationTypeEntity -> new AccommodationTypeData(tourAccommodationTypeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TourAccommodationTypeListResponse getAllTourAccommodationTypes(String requestId) throws EmptyListException {
        List<TourAccommodationTypeEntity> tourAccommodationTypeEntityList = tourAccommodationTypeRepository.findAll();
        if (tourAccommodationTypeEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<AccommodationTypeData> accommodationTypeDataList = tourAccommodationTypeEntityList.stream()
                .map(tourAccommodationTypeEntity -> new AccommodationTypeData(tourAccommodationTypeEntity))
                .toList();
        return new TourAccommodationTypeListResponse(accommodationTypeDataList, requestId);
    }

    @Override
    public TourAccommodationTypeListResponse getAllTourAccommodationTypesPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<TourAccommodationTypeEntity> tourAccommodationTypeEntityList = tourAccommodationTypeRepository.findAll(pageable).getContent();
        if (tourAccommodationTypeEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<AccommodationTypeData> accommodationTypeDataList = tourAccommodationTypeEntityList.stream()
                .map(tourAccommodationTypeEntity -> new AccommodationTypeData(tourAccommodationTypeEntity))
                .toList();
        return new TourAccommodationTypeListResponse(accommodationTypeDataList, requestId);
    }

    // accommodation
    @Override
    public InsertAcknowledgeResponse addAccommodation(AccommodationAddRequest accommodationAddRequest) {
        AccommodationData accommodationData = addAccommodations(List.of(accommodationAddRequest.getAccommodationRequest())).get(0);
        return new InsertAcknowledgeResponse(accommodationData, accommodationAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addAccommodations(AccommodationListAddRequest accommodationListAddRequest) {
        List<AccommodationData> accommodationDataList = addAccommodations(accommodationListAddRequest.getAccommodationList());
        return new InsertAcknowledgeListResponse(accommodationDataList, accommodationListAddRequest.getRequestId());
    }

    @Transactional
    public List<AccommodationData> addAccommodations(List<AccommodationRequest> accommodations) {
        Set<Long> accommodationTypeIDs = accommodations.stream()
                .map(AccommodationRequest::getAccommodationTypeID)
                .collect(Collectors.toSet());
        Map<Long, TourAccommodationTypeEntity> tourAccommodationTypeEntityMap = getAccommodationTypeEntitiesByIDs(accommodationTypeIDs);

        List<TourAccommodationEntity> tourAccommodationEntities = accommodations.stream()
                .map(accommodationRequest -> {
                    TourAccommodationEntity tourAccommodationEntity = new TourAccommodationEntity();
                    tourAccommodationEntity.setTourAccommodationTypeEntity(tourAccommodationTypeEntityMap.get(accommodationRequest.getAccommodationTypeID()));
                    tourAccommodationEntity.setAccommodationName(accommodationRequest.getAccommodationName());
                    tourAccommodationEntity.setAccommodationAddress(accommodationRequest.getAccommodationAddress());
                    return tourAccommodationEntity;
                })
                .collect(Collectors.toList());
        return tourAccommodationRepository.saveAll(tourAccommodationEntities).stream()
                .map(tourAccommodationEntity -> new AccommodationData(tourAccommodationEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TourAccommodationListResponse getAllTourAccommodations(String requestId) throws EmptyListException {
        List<TourAccommodationEntity> tourAccommodationEntityList = tourAccommodationRepository.findAll();
        if (tourAccommodationEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<AccommodationData> accommodationDataList = tourAccommodationEntityList.stream()
                .map(tourAccommodationEntity -> new AccommodationData(tourAccommodationEntity))
                .toList();
        return new TourAccommodationListResponse(accommodationDataList, requestId);
    }

    @Override
    public TourAccommodationListResponse getAllTourAccommodationsPaginated(Integer pageSize, Integer pageNumber, String requestId) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<TourAccommodationEntity> tourAccommodationEntityList = tourAccommodationRepository.findAll(pageable).getContent();
        if (tourAccommodationEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<AccommodationData> accommodationDataList = tourAccommodationEntityList.stream()
                .map(tourAccommodationEntity -> new AccommodationData(tourAccommodationEntity))
                .toList();
        return new TourAccommodationListResponse(accommodationDataList, requestId);
    }

    // tour accommodation packages
    @Override
    public InsertAcknowledgeResponse addAccommodationPackage(SubscribedTourEntity subscribedTourEntity, AccommodationPackageRequest accommodationPackageRequest, String requestId) {
        AccommodationPackageEntity accommodationPackageEntity = setTourAccommodationPackages(subscribedTourEntity, List.of(accommodationPackageRequest)).get(0);
        accommodationPackageEntity = accommodationPackageRepository.save(accommodationPackageEntity);
        AccommodationPackageData accommodationPackageData = new AccommodationPackageData(accommodationPackageEntity);
        return new InsertAcknowledgeResponse(accommodationPackageData, requestId);
    }

    @Override
    public InsertAcknowledgeListResponse addAccommodationPackages(SubscribedTourEntity subscribedTourEntity, List<AccommodationPackageRequest> accommodationPackageRequestList, String requestId) {
        List<AccommodationPackageEntity> accommodationPackageEntities = setTourAccommodationPackages(subscribedTourEntity, accommodationPackageRequestList);
        List<AccommodationPackageData> accommodationOptionDataList = accommodationPackageRepository.saveAll(accommodationPackageEntities).stream()
                .map(accommodationPackageEntity -> new AccommodationPackageData(accommodationPackageEntity))
                .toList();
        return new InsertAcknowledgeListResponse(accommodationOptionDataList, requestId);
    }

    @Override
    public List<AccommodationPackageEntity> setTourAccommodationPackages(SubscribedTourEntity subscribedTourEntity, List<AccommodationPackageRequest> accommodationPackageRequestList) {

        Map<String, Set<Long>> idMaps = new HashMap<>();
        accommodationPackageRequestList.forEach(accommodationPackageRequest -> {
            idMaps.computeIfAbsent("Accommodation", key -> new HashSet<>())
                    .add(accommodationPackageRequest.getAccommodationID());
            idMaps.computeIfAbsent("RoomCategory", key -> new HashSet<>())
                    .add(accommodationPackageRequest.getRoomCategoryID());
            idMaps.computeIfAbsent("RoomType", key -> new HashSet<>())
                    .add(accommodationPackageRequest.getRoomTypeID());
        });

        Map<Long, TourAccommodationEntity> accommodationEntityMap = getAccommodationEntitiesByIDs(idMaps.get("Accommodation"));
        Map<Long, TourRoomCategoryEntity> roomCategoryEntityMap = getTourRoomCategoryEntitiesByIDs(idMaps.get("RoomCategory"));
        Map<Long, TourRoomTypeEntity> roomTypeEntityMap = getTourRoomTypeEntitiesByIDs(idMaps.get("RoomType"));


        List<AccommodationPackageEntity> accommodationPackageEntities = accommodationPackageRequestList.stream()
                .map(accommodationPackageRequest -> {
                    AccommodationPackageEntity accommodationPackageEntity = new AccommodationPackageEntity();
                    accommodationPackageEntity.setSubscribedTourEntity(subscribedTourEntity);
                    accommodationPackageEntity.setTourAccommodationEntity(accommodationEntityMap.get(accommodationPackageRequest.getAccommodationID()));
                    accommodationPackageEntity.setTourRoomCategoryEntity(roomCategoryEntityMap.get(accommodationPackageRequest.getRoomCategoryID()));
                    accommodationPackageEntity.setTourRoomTypeEntity(roomTypeEntityMap.get(accommodationPackageRequest.getRoomTypeID()));
                    accommodationPackageEntity.setBedCount(accommodationPackageRequest.getBedCount());
                    accommodationPackageEntity.setBedConfiguration(accommodationPackageRequest.getBedConfiguration());
                    accommodationPackageEntity.setIsShareable(accommodationPackageRequest.getIsShareable());
                    accommodationPackageEntity.setSuitableForPersons(accommodationPackageRequest.getForPersons());
                    accommodationPackageEntity.setPerNightRoomPrice(accommodationPackageRequest.getPerNightRoomPrice());
                    return accommodationPackageEntity;
                }).toList();
        return accommodationPackageEntities;
    }

    @Override
    public TourAccommodationTypeEntity getAccommodationTypeByID(Long accommodationTypeID) {
        return tourAccommodationTypeRepository.findById(accommodationTypeID).orElseThrow(() -> new EntityNotFoundException("AccommodationTypeEntity Not Found"));
    }

    @Override
    public Map<Long, TourRoomCategoryEntity> getTourRoomCategoryEntitiesByIDs(Set<Long> roomCategoryIDs) {
        return EntityUtil.findEntitiesByIds(roomCategoryIDs, tourRoomCategoryRepository, TourRoomCategoryEntity::getId, "TourRoomCategoryEntity");
    }

    @Override
    public Map<Long, TourAccommodationTypeEntity> getAccommodationTypeEntitiesByIDs(Set<Long> accommodationTypeIDs) {
        return EntityUtil.findEntitiesByIds(accommodationTypeIDs, tourAccommodationTypeRepository, TourAccommodationTypeEntity::getId, "TourAccommodationTypeEntity");

    }

    @Override
    public Map<Long, TourRoomTypeEntity> getTourRoomTypeEntitiesByIDs(Set<Long> roomTypeIDs) {
        return EntityUtil.findEntitiesByIds(roomTypeIDs, tourRoomTypeRepository, TourRoomTypeEntity::getId, "TourRoomTypeEntity");
    }

    @Override
    public Map<Long, TourAccommodationEntity> getAccommodationEntitiesByIDs(Set<Long> accommodationIDs) {
        return EntityUtil.findEntitiesByIds(accommodationIDs, tourAccommodationRepository, TourAccommodationEntity::getId, "TourAccommodationEntity");
    }

    /**
     * @param accommodationPackageIds
     * @return
     * @throws EmptyListException
     */
    @Override
    public Map<Long, AccommodationPackageEntity> getAccommodationPackageEntitiesById(Set<Long> accommodationPackageIds) {
        return EntityUtil.findEntitiesByIds(accommodationPackageIds, accommodationPackageRepository, AccommodationPackageEntity::getId, "AccommodationPackageEntity");
    }

    @Override
    public List<AccommodationPackageData> getAllAccommodationPackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId) {
        return subscribedTourEntity.getAccommodationPackageEntities().stream()
                .map(accommodationPackageEntity -> new AccommodationPackageData(accommodationPackageEntity))
                .toList();
    }
}
