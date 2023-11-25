package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeRequest;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageTypeListResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageTypeData;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageTypeEntity;
import com.ghuddy.backendapp.tours.repository.TourPackageTypeRepository;
import com.ghuddy.backendapp.tours.service.*;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TourPackageServiceImpl implements TourPackageService {
    private final TourPackageTypeRepository tourPackageTypeRepository;

    public TourPackageServiceImpl(TourPackageTypeRepository tourPackageTypeRepository) {
        this.tourPackageTypeRepository = tourPackageTypeRepository;
    }

    // tour package type
    @Override
    public InsertAcknowledgeResponse addTourPackageType(TourPackageTypeRequest tourPackageTypeRequest, String requestId) {
        TourPackageTypeData tourPackageTypeData = addTourPackageTypes(List.of(tourPackageTypeRequest)).get(0);
        return new InsertAcknowledgeResponse(tourPackageTypeData, requestId);
    }

    @Override
    public InsertAcknowledgeListResponse addTourPackageTypes(List<TourPackageTypeRequest> tourPackageTypeRequestList, String requestId) {
        List<TourPackageTypeData> tourPackageTypeDataList = addTourPackageTypes(tourPackageTypeRequestList);
        return new InsertAcknowledgeListResponse(tourPackageTypeDataList, requestId);
    }

    private List<TourPackageTypeData> addTourPackageTypes(List<TourPackageTypeRequest> tourPackages) {
        List<TourPackageTypeEntity> tourPackageTypeEntities = tourPackages.stream()
                .map(tourPackageTypeAddRequest -> {
                    TourPackageTypeEntity tourPackageTypeEntity = new TourPackageTypeEntity();
                    tourPackageTypeEntity.setPackageTypeName(tourPackageTypeAddRequest.getTourPackageTypeName());
                    tourPackageTypeEntity.setDescription(tourPackageTypeAddRequest.getTourPackageTypeDescription());
                    tourPackageTypeEntity.setSuitableFor(tourPackageTypeAddRequest.getSuitableForPersons());
                    return tourPackageTypeEntity;
                })
                .collect(Collectors.toList());
        return tourPackageTypeRepository.saveAll(tourPackageTypeEntities).stream()
                .map(tourPackageTypeEntity -> new TourPackageTypeData(tourPackageTypeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public TourPackageTypeListResponse getAllTourPackageTypes() throws EmptyListException {
        List<TourPackageTypeEntity> tourPackageTypeEntityList = tourPackageTypeRepository.findAll();
        if (tourPackageTypeEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<TourPackageTypeData> tourPackageTypeDataList = tourPackageTypeEntityList.stream()
                .map(tourPackageTypeEntity -> new TourPackageTypeData(tourPackageTypeEntity))
                .toList();
        return new TourPackageTypeListResponse(tourPackageTypeDataList);
    }

    @Override
    public TourPackageTypeListResponse getAllTourPackageTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<TourPackageTypeEntity> tourPackageTypeEntityList = tourPackageTypeRepository.findAll(pageable).getContent();
        if (tourPackageTypeEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<TourPackageTypeData> tourPackageTypeDataList = tourPackageTypeEntityList.stream()
                .map(tourPackageTypeEntity -> new TourPackageTypeData(tourPackageTypeEntity))
                .toList();
        return new TourPackageTypeListResponse(tourPackageTypeDataList);
    }

    @Override
    public TourPackageTypeEntity getTourPackageTypeEntityByPackageTypeID(Long tourPackageTypeID) {
        return tourPackageTypeRepository.findById(tourPackageTypeID).orElseThrow(() -> new EntityNotFoundException("TourPackageTypeEntity Not Found"));
    }

    @Override
    public Map<Long, TourPackageTypeEntity> getTourPackageTypeEntitiesByPackageTypeIDs(Set<Long> tourPackageTypeIDs) {
        return EntityUtil.findEntitiesByIds(tourPackageTypeIDs, tourPackageTypeRepository, TourPackageTypeEntity::getId, "TourPackageTypeEntity");
    }
}
