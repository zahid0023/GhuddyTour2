package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dao.FoodDao;
import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.food.*;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.data.food.FlatMealPackageData;
import com.ghuddy.backendapp.tours.model.data.food.FoodItemData;
import com.ghuddy.backendapp.tours.model.data.food.MealPackageData;
import com.ghuddy.backendapp.tours.model.data.food.MealTypeData;
import com.ghuddy.backendapp.tours.model.entities.food.FoodItemEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.repository.FoodItemRepository;
import com.ghuddy.backendapp.tours.repository.MealPackageRepository;
import com.ghuddy.backendapp.tours.repository.MealTypeRepository;
import com.ghuddy.backendapp.tours.service.FoodService;
import com.ghuddy.backendapp.tours.utils.EntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FoodServiceImpl implements FoodService {
    private final FoodItemRepository foodItemRepository;
    private final MealTypeRepository mealTypeRepository;
    private final MealPackageRepository mealPackageRepository;
    private final FoodDao foodDao;
    private final JdbcTemplate jdbcTemplate;

    public FoodServiceImpl(FoodItemRepository foodItemRepository,
                           MealTypeRepository mealTypeRepository,
                           MealPackageRepository mealPackageRepository,
                           FoodDao foodDao,
                           JdbcTemplate jdbcTemplate) {
        this.foodItemRepository = foodItemRepository;
        this.mealTypeRepository = mealTypeRepository;
        this.mealPackageRepository = mealPackageRepository;
        this.foodDao = foodDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    // food item
    @Override
    public InsertAcknowledgeResponse addFoodItem(FoodItemAddRequest foodItemAddRequest) {
        FoodItemData foodItemData = addFoodItems(List.of(foodItemAddRequest.getFoodItem())).get(0);
        return new InsertAcknowledgeResponse(foodItemData, foodItemAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addFoodItems(FoodItemListAddRequest foodItemListAddRequest) {
        List<FoodItemData> foodItemDataList = addFoodItems(foodItemListAddRequest.getFoodItems());
        return new InsertAcknowledgeListResponse(foodItemDataList, foodItemListAddRequest.getRequestId());
    }

    private List<FoodItemData> addFoodItems(List<FoodItemRequest> foodItems) {
        List<FoodItemEntity> foodItemEntities = foodItems.stream()
                .map(foodItemRequest -> {
                    FoodItemEntity foodItemEntity = new FoodItemEntity();
                    foodItemEntity.setFoodItemName(foodItemRequest.getFoodItemName());
                    return foodItemEntity;
                })
                .collect(Collectors.toList());
        return foodItemRepository.saveAll(foodItemEntities).stream()
                .map(foodItemEntity -> new FoodItemData(foodItemEntity))
                .collect(Collectors.toList());
    }

    @Override
    public FoodItemListResponse getAllFoodItems() throws EmptyListException {
        List<FoodItemEntity> foodItemEntityList = foodItemRepository.findAll();
        if (foodItemEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<FoodItemData> foodItemDataList = foodItemEntityList.stream()
                .map(foodItemEntity -> new FoodItemData(foodItemEntity))
                .toList();
        return new FoodItemListResponse(foodItemDataList);
    }

    @Override
    public FoodItemListResponse getAllFoodItemsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<FoodItemEntity> foodItemEntityList = foodItemRepository.findAll(pageable).getContent();
        if (foodItemEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<FoodItemData> foodItemDataList = foodItemEntityList.stream()
                .map(foodItemEntity -> new FoodItemData(foodItemEntity))
                .toList();
        return new FoodItemListResponse(foodItemDataList);
    }


    // meal type
    @Override
    public InsertAcknowledgeResponse addMealType(MealTypeAddRequest mealTypeAddRequest) {
        MealTypeData mealTypeData = addMealTypes(List.of(mealTypeAddRequest.getMealType())).get(0);
        return new InsertAcknowledgeResponse(mealTypeData, mealTypeAddRequest.getRequestId());
    }

    @Override
    public InsertAcknowledgeListResponse addMealTypes(MealTypeListAddRequest mealTypeListAddRequest) {
        List<MealTypeData> mealTypeDataList = addMealTypes(mealTypeListAddRequest.getMealTypes());
        return new InsertAcknowledgeListResponse<>(mealTypeDataList, mealTypeListAddRequest.getRequestId());
    }

    private List<MealTypeData> addMealTypes(List<MealTypeRequest> mealTypes) {
        List<MealTypeEntity> mealTypeEntities = mealTypes.stream()
                .map(mealTypeRequest -> {
                    MealTypeEntity mealTypeEntity = new MealTypeEntity();
                    mealTypeEntity.setMealTypeName(mealTypeRequest.getMealTypeName());
                    return mealTypeEntity;
                })
                .collect(Collectors.toList());
        return mealTypeRepository.saveAll(mealTypeEntities).stream()
                .map(mealTypeEntity -> new MealTypeData(mealTypeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public MealTypeListResponse getAllMealTypes() throws EmptyListException {
        List<MealTypeEntity> mealTypeEntityList = mealTypeRepository.findAll();
        if (mealTypeEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<MealTypeData> mealTypeDataList = mealTypeEntityList.stream()
                .map(mealTypeEntity -> new MealTypeData(mealTypeEntity))
                .toList();
        return new MealTypeListResponse(mealTypeDataList);
    }

    @Override
    public MealTypeListResponse getAllMealTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<MealTypeEntity> mealTypeEntityList = mealTypeRepository.findAll(pageable).getContent();
        if (mealTypeEntityList.isEmpty()) throw new EmptyListException(ErrorCode.LIST_IS_EMPTY);
        List<MealTypeData> mealTypeDataList = mealTypeEntityList.stream()
                .map(mealTypeEntity -> new MealTypeData(mealTypeEntity))
                .toList();
        return new MealTypeListResponse(mealTypeDataList);
    }

    // tour package meal package


    /**
     * @param subscribedTourEntity
     * @param mealPackageRequest
     * @return
     */
    @Override
    public InsertAcknowledgeResponse addTourMealPackage(SubscribedTourEntity subscribedTourEntity, MealPackageRequest mealPackageRequest, String requestId) {
        MealPackageEntity mealPackageEntity = setTourMealPackages(subscribedTourEntity, List.of(mealPackageRequest)).get(0);
        mealPackageEntity = mealPackageRepository.save(mealPackageEntity);
        MealPackageData mealPackageData = new MealPackageData(mealPackageEntity);
        return new InsertAcknowledgeResponse(mealPackageData, requestId);
    }

    /**
     * @param subscribedTourEntity
     * @param mealPackageRequestList
     * @return
     */
    @Override
    public MealPackageAddListResponse addTourMealPackages(SubscribedTourEntity subscribedTourEntity, List<MealPackageRequest> mealPackageRequestList, String requestId) {
        List<MealPackageEntity> mealPackageEntityList = setTourMealPackages(subscribedTourEntity, mealPackageRequestList);
        mealPackageEntityList = mealPackageRepository.saveAll(mealPackageEntityList);
        List<MealPackageData> mealPackageDataList = mealPackageEntityList.stream()
                .map(mealPackageEntity -> new MealPackageData(mealPackageEntity))
                .toList();

        // Using stream to organize MealPackageData objects into a HashMap based on meal type IDs
        Map<Long, List<MealPackageData>> mealPackages = mealPackageDataList.stream()
                .collect(Collectors.groupingBy(MealPackageData::getMealTypeId));
        return new MealPackageAddListResponse(mealPackages, requestId);
    }

    /**
     * @param subscribedTourEntity
     * @param mealPackageRequestList
     * @return
     */
    @Override
    public List<MealPackageEntity> setTourMealPackages(SubscribedTourEntity subscribedTourEntity, List<MealPackageRequest> mealPackageRequestList) {
        Map<String, Set<Long>> idMaps = new HashMap<>();
        mealPackageRequestList.forEach(mealPackageRequest -> {
            idMaps.computeIfAbsent("mealType", key -> new HashSet<>())
                    .add(mealPackageRequest.getMealTypeID());
            idMaps.computeIfAbsent("foodItem", key -> new HashSet<>())
                    .addAll(mealPackageRequest.getFoodItemIDs());
        });

        Map<Long, MealTypeEntity> mealTypeEntityMap = getMealTypeEntitiesByIDs(idMaps.get("mealType"));
        Map<Long, FoodItemEntity> foodItemEntityMap = getFoodItemEntitiesByIDs(idMaps.get("foodItem"));

        return mealPackageRequestList.stream()
                .map(mealPackageRequest -> {
                    MealPackageEntity mealPackageEntity = new MealPackageEntity();
                    mealPackageEntity.setSubscribedTourEntity(subscribedTourEntity);
                    mealPackageEntity.setMealTypeEntity(mealTypeEntityMap.get(mealPackageRequest.getMealTypeID()));
                    mealPackageEntity.setPerMealPrice(mealPackageRequest.getPerMealPackagePrice());
                    mealPackageEntity.setFoodItemEntities(mealPackageRequest.getFoodItemIDs().stream()
                            .map(foodItemId -> foodItemEntityMap.get(foodItemId))
                            .toList());
                    return mealPackageEntity;
                })
                .toList();
    }

    @Override
    public MealTypeEntity getMealTypeEntityByID(Long mealTypeID) {
        return mealTypeRepository.findById(mealTypeID).orElseThrow(() -> new EntityNotFoundException("MealTypeEntity Not Found"));
    }

    @Override
    public FoodItemEntity getFoodItemEntityByID(Long foodItemID) {
        return foodItemRepository.findById(foodItemID).orElseThrow(() -> new EntityNotFoundException("FoodItemEntity Not Found"));
    }


    /**
     * @param mealTypeIDs
     * @return
     */
    @Override
    public Map<Long, MealTypeEntity> getMealTypeEntitiesByIDs(Set<Long> mealTypeIDs) {
        return EntityUtil.findEntitiesByIds(mealTypeIDs, mealTypeRepository, MealTypeEntity::getId, "MealTypeEntity");
    }

    /**
     * @param foodItemIDs
     * @return
     */
    @Override
    public Map<Long, FoodItemEntity> getFoodItemEntitiesByIDs(Set<Long> foodItemIDs) {
        return EntityUtil.findEntitiesByIds(foodItemIDs, foodItemRepository, FoodItemEntity::getId, "FoodItemEntity");
    }

    /**
     * @param mealPackageIds
     * @return
     */
    @Override
    public Map<Long, MealPackageEntity> getMealPackageEntitiesByIds(Set<Long> mealPackageIds) {
        return EntityUtil.findEntitiesByIds(mealPackageIds, mealPackageRepository, MealPackageEntity::getId, "MealPackageEntity");
    }

    @Override
    public Map<Long, List<MealPackageData>> getAllMealPackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId) {
        return subscribedTourEntity.getMealPackageEntities().stream()
                .map(mealPackageEntity -> new MealPackageData(mealPackageEntity))
                .collect(Collectors.groupingBy(mealPackageData -> mealPackageData.getMealTypeId()));
    }

    @Override
    public List<FlatMealPackageData> getAllFlatMealPackagesForSubscribedTour(SubscribedTourEntity subscribedTourEntity, String requestId) {
        return subscribedTourEntity.getMealPackageEntities().stream()
                .map(mealPackageEntity -> new FlatMealPackageData(mealPackageEntity))
                .toList();
    }
}
