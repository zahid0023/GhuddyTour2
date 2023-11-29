package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.availability.accommodation.AccommodationOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.food.FoodOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.food.MealPackageRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.guide.GuideOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.spot.entry.SpotEntryOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.request.availability.transfer.TransferOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.transportation.TransportationPackageRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.response.commons.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.data.OptionPriceData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.AvailableTourPackageData;
import com.ghuddy.backendapp.tours.model.entities.combination.AvailableComponentsAllOptionsCombinationEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableMealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuidePackageEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.GuidePackageEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailableTransportationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
import com.ghuddy.backendapp.tours.repository.AvailabilityGeneratedTourPackageRepository;
import com.ghuddy.backendapp.tours.repository.AvailableTourPackageRepository;
import com.ghuddy.backendapp.tours.service.*;
import com.ghuddy.backendapp.tours.utils.CombinationGenerator;
import com.ghuddy.backendapp.tours.utils.OptionPriceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TourPackageAvailabilityServiceImpl implements TourPackageAvailabilityService {
    private final AvailableTourPackageRepository availableTourPackageRepository;
    private final AvailabilityGeneratedTourPackageRepository availabilityGeneratedTourPackageRepository;
    private final TourPackageService tourPackageService;
    private final AccommodationService accommodationService;
    private final FoodService foodService;
    private final TransferService transferService;
    private final TransportationService transportationService;
    private final SpotEntryService spotEntryService;
    private final GuideService guideService;
    private final TourPackagePriceService tourPackagePriceService;

    public TourPackageAvailabilityServiceImpl(AvailabilityGeneratedTourPackageRepository availabilityGeneratedTourPackageRepository,
                                              AccommodationService accommodationService,
                                              FoodService foodService,
                                              TransferService transferService,
                                              TransportationService transportationService,
                                              TourPackageService tourPackageService,
                                              SpotEntryService spotEntryService,
                                              AvailableTourPackageRepository availableTourPackageRepository,
                                              GuideService guideService,
                                              TourPackagePriceService tourPackagePriceService) {
        this.availabilityGeneratedTourPackageRepository = availabilityGeneratedTourPackageRepository;
        this.accommodationService = accommodationService;
        this.foodService = foodService;
        this.transferService = transferService;
        this.transportationService = transportationService;
        this.tourPackageService = tourPackageService;
        this.spotEntryService = spotEntryService;
        this.availableTourPackageRepository = availableTourPackageRepository;
        this.guideService = guideService;
        this.tourPackagePriceService = tourPackagePriceService;
    }

    @Override
    public InsertAcknowledgeResponse generateTourPackageAvailabilityOptions(SubscribedTourEntity subscribedTourEntity, TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) {
        AvailableTourPackageEntity availableTourPackageEntity = prepareAvailableTourPackageEntity(subscribedTourEntity, tourPackageAvailabilitySetRequest);
        availableTourPackageEntity.setStatus("DRAFT");
        availableTourPackageEntity.setAvailableComponentsAllOptionsCombinationEntities(getAllOptionEntities(availableTourPackageEntity));
        availableTourPackageEntity = availableTourPackageRepository.save(availableTourPackageEntity);
        AvailableTourPackageData availableTourPackageData = new AvailableTourPackageData(availableTourPackageEntity);
        return new InsertAcknowledgeResponse(availableTourPackageData, tourPackageAvailabilitySetRequest.getRequestId());
    }


    private AvailableTourPackageEntity prepareAvailableTourPackageEntity(SubscribedTourEntity subscribedTourEntity, TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) {
        AvailableTourPackageEntity availableTourPackageEntity = new AvailableTourPackageEntity();
        TourPackageTypeEntity tourPackageTypeEntity = tourPackageService.getTourPackageTypeEntityByPackageTypeID(tourPackageAvailabilitySetRequest.getTourPackageTypeId());

        availableTourPackageEntity.setSubscribedTourEntity(subscribedTourEntity);
        availableTourPackageEntity.setTourPackageTypeEntity(tourPackageTypeEntity);
        availableTourPackageEntity.setTourStartDate(tourPackageAvailabilitySetRequest.getTourStartDate());
        availableTourPackageEntity.setTotalSeats(tourPackageAvailabilitySetRequest.getTotalSeats());
        availableTourPackageEntity.setBookableSeats(tourPackageAvailabilitySetRequest.getBookableSeats());

        // Set Accommodations
        if (tourPackageAvailabilitySetRequest.getTourPackageAccommodationIsInclusive())
            availableTourPackageEntity.setIsAccommodationInclusive(true);
        List<AccommodationOptionRequestForAvailability> accommodationOptions = tourPackageAvailabilitySetRequest.getAccommodationOptionRequestForAvailabilityList();
        if (accommodationOptions != null && !accommodationOptions.isEmpty()) {
            availableTourPackageEntity.setAvailableAccommodationOptionEntities(setAccommodations(availableTourPackageEntity, accommodationOptions));
        }

        // Set Foods
        if (tourPackageAvailabilitySetRequest.getTourPackageFoodIsInclusive())
            availableTourPackageEntity.setIsFoodInclusive(true);
        List<FoodOptionRequestForAvailability> foodOptions = tourPackageAvailabilitySetRequest.getFoodOptionRequestForAvailabilityList();
        if (foodOptions != null && !foodOptions.isEmpty()) {
            availableTourPackageEntity.setAvailableFoodOptionEntities(setFoods(availableTourPackageEntity, foodOptions));
        }

        // Set Transfers
        if (tourPackageAvailabilitySetRequest.getTourPackageTransferIsInclusive())
            availableTourPackageEntity.setIsTransferInclusive(true);
        List<TransferOptionRequestForAvailability> transferOptions = tourPackageAvailabilitySetRequest.getTransferOptionRequestForAvailabilityList();
        if (transferOptions != null && !transferOptions.isEmpty()) {
            availableTourPackageEntity.setAvailableTransferOptionEntities(setTransfers(availableTourPackageEntity, transferOptions));
        }

        // Set Transportation
        List<TransportationPackageRequestForAvailability> transportationOptions = tourPackageAvailabilitySetRequest.getTransportationPackageRequestForAvailabilityList();
        if (transportationOptions != null && !transportationOptions.isEmpty()) {
            availableTourPackageEntity.setAvailableTransportationPackageEntities(setTransportations(availableTourPackageEntity, transportationOptions));
        }

        // Set Guides
        if (tourPackageAvailabilitySetRequest.getTourPackageGuideIsInclusive() != null && tourPackageAvailabilitySetRequest.getTourPackageGuideIsInclusive())
            availableTourPackageEntity.setIsGuideInclusive(true);
        List<GuideOptionRequestForAvailability> guideOptions = tourPackageAvailabilitySetRequest.getGuideOptionRequestForAvailabilityList();
        if (guideOptions != null && !guideOptions.isEmpty())
            availableTourPackageEntity.setAvailableGuideOptionEntities(setGuides(availableTourPackageEntity, guideOptions));

        // Set Spot Entry
        if (tourPackageAvailabilitySetRequest.getTourPackageSpotEntryIsInclusive() != null && tourPackageAvailabilitySetRequest.getTourPackageSpotEntryIsInclusive()) {
            availableTourPackageEntity.setIsSpotEntryInclusive(true);
        }
        List<SpotEntryOptionRequestForAvailability> spotEntryOptions = tourPackageAvailabilitySetRequest.getSpotEntryOptionRequestForAvailabilityList();
        log.info(spotEntryOptions.toString());
        if (spotEntryOptions != null && !spotEntryOptions.isEmpty()) {
            availableTourPackageEntity.setAvailableSpotEntryOptionEntities(setSpotEntries(availableTourPackageEntity, spotEntryOptions));
        }
        return availableTourPackageEntity;
    }

    private List<AvailableComponentsAllOptionsCombinationEntity> getAllOptionEntities(AvailableTourPackageEntity availableTourPackageEntity) {
        List<List<?>> inclusiveLists = new LinkedList<>();
        List<List<?>> nonInclusiveLists = new LinkedList<>();

        List<AvailableAccommodationOptionEntity> availableAccommodationOptionEntities = availableTourPackageEntity.getAvailableAccommodationOptionEntities();
        if (!isListNullOrEmpty(availableAccommodationOptionEntities)) {
            if (availableTourPackageEntity.getIsAccommodationInclusive())
                inclusiveLists.add(availableAccommodationOptionEntities);
            else nonInclusiveLists.add(availableAccommodationOptionEntities);
        }

        List<AvailableFoodOptionEntity> availableFoodOptionEntities = availableTourPackageEntity.getAvailableFoodOptionEntities();
        if (!isListNullOrEmpty(availableFoodOptionEntities)) {
            if (availableTourPackageEntity.getIsFoodInclusive())
                inclusiveLists.add(availableFoodOptionEntities);
            else nonInclusiveLists.add(availableFoodOptionEntities);
        }

        List<AvailableTransferOptionEntity> availableTransferOptionEntities = availableTourPackageEntity.getAvailableTransferOptionEntities();
        if (!isListNullOrEmpty(availableTransferOptionEntities)) {
            if (availableTourPackageEntity.getIsTransferInclusive())
                inclusiveLists.add(availableTransferOptionEntities);
            else nonInclusiveLists.add(availableTransferOptionEntities);
        }

        List<AvailableTransportationPackageEntity> availableTransportationPackageEntities = availableTourPackageEntity.getAvailableTransportationPackageEntities();
        if (!isListNullOrEmpty(availableTransportationPackageEntities))
            nonInclusiveLists.add(availableTransportationPackageEntities);

        List<AvailableGuideOptionEntity> availableGuideOptionEntities = availableTourPackageEntity.getAvailableGuideOptionEntities();
        if (!isListNullOrEmpty(availableGuideOptionEntities)) {
            if (availableTourPackageEntity.getIsGuideInclusive())
                inclusiveLists.add(availableGuideOptionEntities);
            else nonInclusiveLists.add(availableGuideOptionEntities);
        }

        List<AvailableSpotEntryOptionEntity> availableSpotEntryOptionEntities = availableTourPackageEntity.getAvailableSpotEntryOptionEntities();
        if (!isListNullOrEmpty(availableSpotEntryOptionEntities)) {
            if (availableTourPackageEntity.getIsSpotEntryInclusive())
                inclusiveLists.add(availableSpotEntryOptionEntities);
            else nonInclusiveLists.add(availableSpotEntryOptionEntities);
        }

        log.info("Inclusive:" + inclusiveLists);
        log.info("Non Inclusive" + nonInclusiveLists);

        List<AvailableComponentsAllOptionsCombinationEntity> inclusiveOptionEntities = inclusiveComponentsCombinations(availableTourPackageEntity, inclusiveLists);
        if (!nonInclusiveLists.isEmpty()) inclusiveLists.addAll(nonInclusiveLists);
        List<AvailableComponentsAllOptionsCombinationEntity> allOptionEntities = allComponentsCombinations(availableTourPackageEntity, inclusiveLists);
        allOptionEntities.addAll(inclusiveOptionEntities);
        return allOptionEntities;
    }

    private List<AvailableAccommodationOptionEntity> setAccommodations(AvailableTourPackageEntity availableTourPackageEntity, List<AccommodationOptionRequestForAvailability> accommodationOptionRequestForAvailabilityList) {
        Set<Long> accommodationPackageIds = accommodationOptionRequestForAvailabilityList.stream()
                .flatMap(accommodationOptionRequestForAvailability ->
                        accommodationOptionRequestForAvailability.getAccommodationPackageRequestForAvailabilityList().stream()
                                .map(accommodationPackageRequestForAvailability -> accommodationPackageRequestForAvailability.getAccommodationPackageId())
                )
                .collect(Collectors.toSet());
        Map<Long, AccommodationPackageEntity> accommodationPackageEntityMap = accommodationService.getAccommodationPackageEntitiesById(accommodationPackageIds);

        return accommodationOptionRequestForAvailabilityList.stream()
                .map(accommodationOptionRequestForAvailability -> {
                    AvailableAccommodationOptionEntity availableAccommodationOptionEntity = new AvailableAccommodationOptionEntity();
                    availableAccommodationOptionEntity.setAvailableTourPackageEntity(availableTourPackageEntity);
                    availableAccommodationOptionEntity.setTotalOptionPricePerPerson(BigDecimal.ZERO);
                    List<AvailableAccommodationPackageEntity> availabilityGeneratedAccommodationPackageEntityList = accommodationOptionRequestForAvailability.getAccommodationPackageRequestForAvailabilityList().stream()
                            .map(accommodationPackageRequestForAvailability -> {
                                AvailableAccommodationPackageEntity availableAccommodationPackageEntity = new AvailableAccommodationPackageEntity();
                                AccommodationPackageEntity accommodationPackageEntity = accommodationPackageEntityMap.get(accommodationPackageRequestForAvailability.getAccommodationPackageId());
                                availableAccommodationPackageEntity.setAccommodationPackageEntity(accommodationPackageEntity);
                                availableAccommodationPackageEntity.setAccommodationPackagePrice(accommodationPackageRequestForAvailability.getPerNightRoomPrice());
                                availableAccommodationPackageEntity.setNightNumber(accommodationPackageRequestForAvailability.getNightNumber());
                                availableAccommodationPackageEntity.setAvailableAccommodationOptionEntity(availableAccommodationOptionEntity);
                                return availableAccommodationPackageEntity;
                            })
                            .toList();
                    availableAccommodationOptionEntity.setAvailableAccommodationPackageEntityList(availabilityGeneratedAccommodationPackageEntityList);

                    BigDecimal optionPricePerPerson = tourPackagePriceService.perPersonAccommodationOptionPrice(availableAccommodationOptionEntity, availableTourPackageEntity.getTourPackageTypeEntity().getSuitableFor());
                    availableAccommodationOptionEntity.setTotalOptionPricePerPerson(optionPricePerPerson);

                    return availableAccommodationOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailableFoodOptionEntity> setFoods(AvailableTourPackageEntity availableTourPackageEntity, List<FoodOptionRequestForAvailability> foodOptions) {
        Set<Long> mealPackageIds = foodOptions.stream()
                .flatMap(foodOptionRequest ->
                        foodOptionRequest.getMealTypeWiseMealPackages().values().stream()
                                .flatMap(List::stream)
                                .map(MealPackageRequestForAvailability::getMealPackageId)
                )
                .collect(Collectors.toSet());

        Map<Long, MealPackageEntity> mealPackageEntityMap = foodService.getMealPackageEntitiesByIds(mealPackageIds);
        List<AvailableFoodOptionEntity> availableFoodOptionEntityList = new LinkedList<>();

        foodOptions.stream()
                .forEach(foodOptionRequestForAvailability -> {
                    List<List<?>> lists = new LinkedList<>();
                    foodOptionRequestForAvailability.getMealTypeWiseMealPackages().entrySet().stream()
                            .forEach(integerListEntry -> {
                                List<AvailableMealPackageEntity> availableMealPackageEntityList = integerListEntry.getValue().stream()
                                        .map(mealPackageRequest -> {
                                            AvailableMealPackageEntity availableMealPackageEntity = new AvailableMealPackageEntity();
                                            availableMealPackageEntity.setMealPackageEntity(mealPackageEntityMap.get(mealPackageRequest.getMealPackageId()));
                                            availableMealPackageEntity.setMealPackageAvailableInDay(foodOptionRequestForAvailability.getDayNumber());
                                            availableMealPackageEntity.setMealPackagePrice(mealPackageRequest.getMealPackagePrice()); // Set the mealPackagePrice
                                            log.info(availableMealPackageEntity.getMealPackagePrice().toString());
                                            return availableMealPackageEntity;
                                        })
                                        .toList();
                                lists.add(availableMealPackageEntityList);
                            });
                    List<List<?>> combinations = CombinationGenerator.generateCombinations(lists);
                    combinations.forEach(combination -> {
                        AvailableFoodOptionEntity availableFoodOptionEntity = new AvailableFoodOptionEntity();
                        List<AvailableMealPackageEntity> availableMealPackageEntityList = (List<AvailableMealPackageEntity>) combination;
                        availableMealPackageEntityList = availableMealPackageEntityList.stream()
                                .peek(availableMealPackageEntity -> availableMealPackageEntity.setAvailableFoodOptionEntity(availableFoodOptionEntity))
                                .toList();
                        availableFoodOptionEntity.setAvailabilityGeneratedMealPackageEntities(availableMealPackageEntityList);
                        availableFoodOptionEntity.setAvailableTourPackageEntity(availableTourPackageEntity);
                        BigDecimal optionPricePerPerson = tourPackagePriceService.perPersonFoodOptionPrice(availableFoodOptionEntity);
                        availableFoodOptionEntity.setTotalOptionPricePerPerson(optionPricePerPerson);
                        log.info(availableFoodOptionEntity.getTotalOptionPricePerPerson().toString());
                        availableFoodOptionEntityList.add(availableFoodOptionEntity);
                    });
                });

        return availableFoodOptionEntityList;
    }

    private List<AvailableTransferOptionEntity> setTransfers(AvailableTourPackageEntity availableTourPackageEntity, List<TransferOptionRequestForAvailability> transferOptionRequestForAvailabilityList) {
        Set<Long> transferPackageIds = transferOptionRequestForAvailabilityList.stream()
                .flatMap(transferOptionRequestForAvailability ->
                        transferOptionRequestForAvailability.getTransferPackageRequestForAvailabilityList().stream()
                                .map(transferPackageRequestForAvailability -> transferPackageRequestForAvailability.getTransferPackageId())
                )
                .collect(Collectors.toSet());
        Map<Long, TransferPackageEntity> transferPackageEntityMap = transferService.getTransferPackageEntitiesById(transferPackageIds);

        return transferOptionRequestForAvailabilityList.stream()
                .map(transferOptionRequestForAvailability -> {
                    AvailableTransferOptionEntity availableTransferOptionEntity = new AvailableTransferOptionEntity();
                    availableTransferOptionEntity.setAvailableTourPackageEntity(availableTourPackageEntity);
                    availableTransferOptionEntity.setTotalOptionPricePerPerson(BigDecimal.ZERO);

                    List<AvailableTransferPackageEntity> availableTransferPackageEntityList = transferOptionRequestForAvailability.getTransferPackageRequestForAvailabilityList().stream()
                            .map(transferPackageRequestForAvailability -> {
                                AvailableTransferPackageEntity availableTransferPackageEntity = new AvailableTransferPackageEntity();
                                availableTransferPackageEntity.setAvailableTransferOptionEntity(availableTransferOptionEntity);
                                TransferPackageEntity transferPackageEntity = transferPackageEntityMap.get(transferPackageRequestForAvailability.getTransferPackageId());
                                availableTransferPackageEntity.setTransferPackageEntity(transferPackageEntity);
                                BigDecimal perVehiclePerTripPrice = transferPackageRequestForAvailability.getPerVehiclePerTripPrice();
                                availableTransferPackageEntity.setPerVehiclePerTripPrice(perVehiclePerTripPrice);
                                return availableTransferPackageEntity;
                            })
                            .toList();
                    availableTransferOptionEntity.setAvailableTransferPackageEntityList(availableTransferPackageEntityList);
                    BigDecimal optionPricePerPerson = tourPackagePriceService.perPersonTransferOptionPrice(availableTransferOptionEntity, availableTourPackageEntity.getTourPackageTypeEntity().getSuitableFor());
                    availableTransferOptionEntity.setTotalOptionPricePerPerson(optionPricePerPerson);
                    return availableTransferOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailableTransportationPackageEntity> setTransportations(AvailableTourPackageEntity availableTourPackageEntity, List<TransportationPackageRequestForAvailability> transportationPackageRequestForAvailabilityList) {
        Set<Long> transportationPackageIds = transportationPackageRequestForAvailabilityList.stream()
                .map(transportationPackageRequestForAvailability -> transportationPackageRequestForAvailability.getTransportationPackageId())
                .collect(Collectors.toSet());
        Map<Long, TransportationPackageEntity> transportationPackageEntityMap = transportationService.getTransferPackageEntitiesById(transportationPackageIds);
        return transportationPackageRequestForAvailabilityList.stream()
                .map(transportationPackageRequestForAvailability -> {
                    AvailableTransportationPackageEntity availableTransportationPackageEntity = new AvailableTransportationPackageEntity();
                    availableTransportationPackageEntity.setAvailableTourPackageEntity(availableTourPackageEntity);
                    TransportationPackageEntity transportationPackageEntity = transportationPackageEntityMap.get(transportationPackageRequestForAvailability.getTransportationPackageId());
                    availableTransportationPackageEntity.setTransportationPackageEntity(transportationPackageEntity);
                    BigDecimal perPersonTransportationPackagePrice = transportationPackageRequestForAvailability.getTransportationPackagePrice();
                    availableTransportationPackageEntity.setTransportationPackagePrice(perPersonTransportationPackagePrice);
                    return availableTransportationPackageEntity;
                })
                .toList();
    }

    private List<AvailableSpotEntryOptionEntity> setSpotEntries(AvailableTourPackageEntity availableTourPackageEntity, List<SpotEntryOptionRequestForAvailability> spotEntryOptionRequestForAvailabilityList) {
        Set<Long> spotEntryIds = spotEntryOptionRequestForAvailabilityList.stream()
                .flatMap(spotEntryOptionRequestForAvailability ->
                        spotEntryOptionRequestForAvailability.getSpotEntryPackageRequestForAvailabilityList().stream()
                                .map(spotEntryPackageRequestForAvailability -> spotEntryPackageRequestForAvailability.getSpotEntryId())
                )
                .collect(Collectors.toSet());
        Map<Long, SpotEntryPackageEntity> spotEntryPackageEntityMap = spotEntryService.getSpotEntryPackageEntitiesById(spotEntryIds);

        return spotEntryOptionRequestForAvailabilityList.stream()
                .map(spotEntryOptionRequestForAvailability -> {
                    AvailableSpotEntryOptionEntity availableSpotEntryOptionEntity = new AvailableSpotEntryOptionEntity();
                    availableSpotEntryOptionEntity.setAvailableTourPackageEntity(availableTourPackageEntity);
                    availableSpotEntryOptionEntity.setTotalOptionPricePerPerson(BigDecimal.ZERO);
                    List<AvailableSpotEntryPackageEntity> availableSpotEntryPackageEntityList = spotEntryOptionRequestForAvailability.getSpotEntryPackageRequestForAvailabilityList().stream()
                            .map(spotEntryPackageRequestForAvailability -> {
                                AvailableSpotEntryPackageEntity availableSpotEntryPackageEntity = new AvailableSpotEntryPackageEntity();
                                availableSpotEntryPackageEntity.setAvailableSpotEntryOptionEntity(availableSpotEntryOptionEntity);
                                SpotEntryPackageEntity spotEntryPackageEntity = spotEntryPackageEntityMap.get(spotEntryPackageRequestForAvailability.getSpotEntryId());
                                availableSpotEntryPackageEntity.setSpotEntryPackageEntity(spotEntryPackageEntity);
                                BigDecimal perPersonSpotEntryPackagePrice = spotEntryPackageRequestForAvailability.getSpotEntryPrice();
                                availableSpotEntryPackageEntity.setSpotEntryPricePerPerson(perPersonSpotEntryPackagePrice);
                                return availableSpotEntryPackageEntity;
                            })
                            .toList();
                    availableSpotEntryOptionEntity.setAvailableSpotEntryPackageEntityList(availableSpotEntryPackageEntityList);
                    BigDecimal optionPricePerPerson = tourPackagePriceService.perPersonSpotEntryOptionPrice(availableSpotEntryOptionEntity);
                    availableSpotEntryOptionEntity.setTotalOptionPricePerPerson(optionPricePerPerson);
                    return availableSpotEntryOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailableGuideOptionEntity> setGuides(AvailableTourPackageEntity availableTourPackageEntity, List<GuideOptionRequestForAvailability> guideOptionRequestForAvailabilityList) {
        Set<Long> guidePackageIds = guideOptionRequestForAvailabilityList.stream()
                .flatMap(guideOptionRequestForAvailability -> guideOptionRequestForAvailability.getGuidePackageRequestForAvailabilityList().stream()
                        .map(guidePackageRequestForAvailability -> guidePackageRequestForAvailability.getGuidePackageId()))
                .collect(Collectors.toSet());
        Map<Long, GuidePackageEntity> guidePackageEntityMap = guideService.getGuidePackageEntitiesById(guidePackageIds);
        log.info(guidePackageEntityMap.toString());
        return guideOptionRequestForAvailabilityList.stream()
                .map(guideOptionRequestForAvailability -> {
                    AvailableGuideOptionEntity availableGuideOptionEntity = new AvailableGuideOptionEntity();
                    availableGuideOptionEntity.setTotalOptionPricePerPerson(BigDecimal.ZERO);
                    List<AvailableGuidePackageEntity> availableSpotEntryPackageEntityList = guideOptionRequestForAvailability.getGuidePackageRequestForAvailabilityList().stream()
                            .map(guidePackageRequestForAvailability -> {
                                AvailableGuidePackageEntity availableGuidePackageEntity = new AvailableGuidePackageEntity();
                                availableGuidePackageEntity.setAvailableGuideOptionEntity(availableGuideOptionEntity);
                                GuidePackageEntity guidePackageEntity = guidePackageEntityMap.get(guidePackageRequestForAvailability.getGuidePackageId());
                                availableGuidePackageEntity.setGuidePackageEntity(guidePackageEntity);
                                availableGuidePackageEntity.setDayNumber(guidePackageRequestForAvailability.getDayNumber());
                                BigDecimal perGuidePackagePrice = guidePackageRequestForAvailability.getGuidePackagePrice();
                                availableGuidePackageEntity.setTotalGuidePackagePrice(perGuidePackagePrice);
                                return availableGuidePackageEntity;
                            })
                            .toList();
                    availableGuideOptionEntity.setAvailableTourPackageEntity(availableTourPackageEntity);
                    availableGuideOptionEntity.setAvailableGuidePackageEntityList(availableSpotEntryPackageEntityList);
                    BigDecimal optionPricePerPerson = tourPackagePriceService.perPersonGuideOptionPrice(availableGuideOptionEntity, availableTourPackageEntity.getTourPackageTypeEntity().getSuitableFor());
                    availableGuideOptionEntity.setTotalOptionPricePerPerson(optionPricePerPerson);
                    return availableGuideOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailableComponentsAllOptionsCombinationEntity> inclusiveComponentsCombinations(AvailableTourPackageEntity availableTourPackageEntity, List<List<?>> tourPackageCombinationToCheck) {
        List<List<?>> combinations = CombinationGenerator.generateCombinations(tourPackageCombinationToCheck);

        List<AvailableComponentsAllOptionsCombinationEntity> inclusiveOptionsCombinationEntityList = combinations.stream()
                .map(option -> {
                    AvailableComponentsAllOptionsCombinationEntity inclusiveOptionsCombinationEntity = new AvailableComponentsAllOptionsCombinationEntity();
                    inclusiveOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPricePerPerson(BigDecimal.ZERO);

                    option.forEach(component -> {
                        if (component.getClass().isAssignableFrom(AvailableAccommodationOptionEntity.class)) {
                            AvailableAccommodationOptionEntity accommodationOptionEntity = (AvailableAccommodationOptionEntity) component;
                            inclusiveOptionsCombinationEntity.setAvailableAccommodationOptionEntity(accommodationOptionEntity);
                            updateCombinationPrices(inclusiveOptionsCombinationEntity, accommodationOptionEntity.getTotalOptionPricePerPerson());

                        } else if (component.getClass().isAssignableFrom(AvailableFoodOptionEntity.class)) {
                            AvailableFoodOptionEntity foodOptionEntity = (AvailableFoodOptionEntity) component;
                            inclusiveOptionsCombinationEntity.setAvailableFoodOptionEntity(foodOptionEntity);
                            updateCombinationPrices(inclusiveOptionsCombinationEntity, foodOptionEntity.getTotalOptionPricePerPerson());

                        } else if (component.getClass().isAssignableFrom(AvailableTransferOptionEntity.class)) {
                            AvailableTransferOptionEntity transferOptionEntity = (AvailableTransferOptionEntity) component;
                            inclusiveOptionsCombinationEntity.setAvailableTransferOptionEntity(transferOptionEntity);
                            updateCombinationPrices(inclusiveOptionsCombinationEntity, transferOptionEntity.getTotalOptionPricePerPerson());

                        } else if (component.getClass().isAssignableFrom(AvailableGuideOptionEntity.class)) {
                            AvailableGuideOptionEntity guideOptionEntity = (AvailableGuideOptionEntity) component;
                            inclusiveOptionsCombinationEntity.setAvailableGuideOptionEntity(guideOptionEntity);
                            updateCombinationPrices(inclusiveOptionsCombinationEntity, BigDecimal.ZERO); // Adjust as needed

                        } else if (component.getClass().isAssignableFrom(AvailableSpotEntryOptionEntity.class)) {
                            AvailableSpotEntryOptionEntity spotEntryOptionEntity = (AvailableSpotEntryOptionEntity) component;
                            inclusiveOptionsCombinationEntity.setAvailableSpotEntryOptionEntity(spotEntryOptionEntity);
                            updateCombinationPrices(inclusiveOptionsCombinationEntity, spotEntryOptionEntity.getTotalOptionPricePerPerson());
                        }
                    });
                    setOptionPrice(inclusiveOptionsCombinationEntity);
                    inclusiveOptionsCombinationEntity.setAvailableTourPackageEntity(availableTourPackageEntity);
                    inclusiveOptionsCombinationEntity.setIsInclusiveOptions(true);
                    return inclusiveOptionsCombinationEntity;
                })
                .collect(Collectors.toList());
        return inclusiveOptionsCombinationEntityList;
    }

    private List<AvailableComponentsAllOptionsCombinationEntity> allComponentsCombinations(AvailableTourPackageEntity availableTourPackageEntity, List<List<?>> tourPackageCombinationToCheck) {
        List<List<?>> combinations = CombinationGenerator.generateCombinations(tourPackageCombinationToCheck);

        List<AvailableComponentsAllOptionsCombinationEntity> availableComponentsAllOptionsCombinationEntityList = combinations.stream()
                .map(option -> {
                    AvailableComponentsAllOptionsCombinationEntity allOptionsCombinationEntity = new AvailableComponentsAllOptionsCombinationEntity();
                    allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPricePerPerson(BigDecimal.ZERO);
                    option.forEach(component -> {
                        if (component.getClass().isAssignableFrom(AvailableAccommodationOptionEntity.class)) {
                            AvailableAccommodationOptionEntity accommodationOptionEntity = (AvailableAccommodationOptionEntity) component;
                            allOptionsCombinationEntity.setAvailableAccommodationOptionEntity(accommodationOptionEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPricePerPerson(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPricePerPerson().add(accommodationOptionEntity.getTotalOptionPricePerPerson()));
                        } else if (component.getClass().isAssignableFrom(AvailableFoodOptionEntity.class)) {
                            AvailableFoodOptionEntity foodOptionEntity = (AvailableFoodOptionEntity) component;
                            allOptionsCombinationEntity.setAvailableFoodOptionEntity(foodOptionEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPricePerPerson(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPricePerPerson().add(BigDecimal.ZERO));
                        } else if (component.getClass().isAssignableFrom(AvailableTransferOptionEntity.class)) {
                            AvailableTransferOptionEntity transferOptionEntity = (AvailableTransferOptionEntity) component;
                            allOptionsCombinationEntity.setAvailableTransferOptionEntity(transferOptionEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPricePerPerson(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPricePerPerson().add(transferOptionEntity.getTotalOptionPricePerPerson()));
                        } else if (component.getClass().isAssignableFrom(AvailableTransportationPackageEntity.class)) {
                            AvailableTransportationPackageEntity transportationPackageEntity = (AvailableTransportationPackageEntity) component;
                            allOptionsCombinationEntity.setAvailableTransportationPackageEntity(transportationPackageEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPricePerPerson(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPricePerPerson().add(transportationPackageEntity.getTransportationPackagePrice()));
                        } else if (component.getClass().isAssignableFrom(AvailableGuideOptionEntity.class)) {
                            AvailableGuideOptionEntity availableGuideOptionEntity = (AvailableGuideOptionEntity) component;
                            allOptionsCombinationEntity.setAvailableGuideOptionEntity(availableGuideOptionEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPricePerPerson(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPricePerPerson().add(availableGuideOptionEntity.getTotalOptionPricePerPerson()));
                        } else if (component.getClass().isAssignableFrom(AvailableSpotEntryOptionEntity.class)) {
                            AvailableSpotEntryOptionEntity spotEntryOptionEntity = (AvailableSpotEntryOptionEntity) component;
                            allOptionsCombinationEntity.setAvailableSpotEntryOptionEntity(spotEntryOptionEntity);
                            allOptionsCombinationEntity.setGhuddyPlatformCalculatedOptionPricePerPerson(allOptionsCombinationEntity.getGhuddyPlatformCalculatedOptionPricePerPerson().add(spotEntryOptionEntity.getTotalOptionPricePerPerson()));
                        }
                    });

                    setOptionPrice(allOptionsCombinationEntity);
                    allOptionsCombinationEntity.setAvailableTourPackageEntity(availableTourPackageEntity);
                    allOptionsCombinationEntity.setIsInclusiveOptions(false);
                    return allOptionsCombinationEntity;
                })
                .collect(Collectors.toList());
        return availableComponentsAllOptionsCombinationEntityList;
    }


    private void updateCombinationPrices(AvailableComponentsAllOptionsCombinationEntity optionsCombinationEntity, BigDecimal optionPrice) {
        // the sum of component option combination price
        optionsCombinationEntity.setGhuddyPlatformCalculatedOptionPricePerPerson(optionsCombinationEntity.getGhuddyPlatformCalculatedOptionPricePerPerson().add(optionPrice));
    }

    private void setOptionPrice(AvailableComponentsAllOptionsCombinationEntity optionsCombinationEntity) {
        OptionPriceData optionPriceData = OptionPriceCalculator.getBlackPrice(optionsCombinationEntity.getGhuddyPlatformCalculatedOptionPricePerPerson(), BigDecimal.ZERO); // before ghuddy subsidy
        optionPriceData = OptionPriceCalculator.getRedPrice(optionPriceData.getNetOptionPriceAfterGhuddyCommission(), BigDecimal.ZERO, optionPriceData); // after ghuddy subsidy

        optionsCombinationEntity.setMerchantSubsidyAmountPerPerson(optionPriceData.getMerchantSubsidyAmount());
        optionsCombinationEntity.setNetOptionPricePerPersonAfterMerchantSubsidy(optionPriceData.getNetOptionPriceAfterMerchantSubsidy());

        optionsCombinationEntity.setGhuddyPlatformCommissionAmount(optionPriceData.getGhuddyPlatformCommissionAmount());
        optionsCombinationEntity.setNetOptionPricePerPersonAfterGhuddyCommission(optionPriceData.getNetOptionPriceAfterGhuddyCommission());

        optionsCombinationEntity.setGhuddyWebsiteBlackPricePerPerson(optionPriceData.getGhuddyWebsiteBlackPrice());

        optionsCombinationEntity.setGhuddySubsidyAmountPerPerson(optionPriceData.getGhuddySubsidyAmount());
        optionsCombinationEntity.setNetOptionPricePerPersonAfterGhuddySubsidy(optionPriceData.getNetOptionPriceAfterGhuddySubsidy());

        optionsCombinationEntity.setGhuddyWebsiteRedPricePerPerson(optionPriceData.getGhuddyWebsiteRedPrice());

        optionsCombinationEntity.setPaymentGatewayAmount(optionPriceData.getPaymentGateWayAmount());
    }

    private boolean isListNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty() ? true : false;
    }
}
