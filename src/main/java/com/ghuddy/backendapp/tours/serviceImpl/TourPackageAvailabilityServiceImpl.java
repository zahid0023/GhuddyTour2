package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.availability.accommodation.AccommodationOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.food.FoodOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.guide.GuideOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.spot.entry.SpotEntryOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.request.availability.transfer.TransferOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.availability.transportation.TransportationPackageRequestForAvailability;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableMealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public TourPackageAvailabilityServiceImpl(AvailabilityGeneratedTourPackageRepository availabilityGeneratedTourPackageRepository,
                                              AccommodationService accommodationService,
                                              FoodService foodService,
                                              TransferService transferService,
                                              TransportationService transportationService,
                                              TourPackageService tourPackageService,
                                              SpotEntryService spotEntryService,
                                              AvailableTourPackageRepository availableTourPackageRepository) {
        this.availabilityGeneratedTourPackageRepository = availabilityGeneratedTourPackageRepository;
        this.accommodationService = accommodationService;
        this.foodService = foodService;
        this.transferService = transferService;
        this.transportationService = transportationService;
        this.tourPackageService = tourPackageService;
        this.spotEntryService = spotEntryService;
        this.availableTourPackageRepository = availableTourPackageRepository;
    }

    @Override
    public void generateTourPackageAvailabilityOptions(SubscribedTourEntity subscribedTourEntity,TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) {
        AvailableTourPackageEntity availableTourPackageEntity = prepareAvailableTourPackageEntity(subscribedTourEntity,tourPackageAvailabilitySetRequest);
        availableTourPackageEntity.setStatus("DRAFT");
        availableTourPackageEntity = availableTourPackageRepository.save(availableTourPackageEntity);
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

        // Set Guide Package
        if (tourPackageAvailabilitySetRequest.getTourPackageGuideIsInclusive() != null && tourPackageAvailabilitySetRequest.getTourPackageGuideIsInclusive())
            availableTourPackageEntity.setIsGuideInclusive(true);
        List<GuideOptionRequestForAvailability> guideOptions = tourPackageAvailabilitySetRequest.getGuideOptionRequestForAvailabilityList();

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

    private List<AvailableAccommodationOptionEntity> setAccommodations(AvailableTourPackageEntity availableTourPackageEntity, List<AccommodationOptionRequestForAvailability> accommodationOptionRequestForAvailabilityList) {
        Set<Long> accommodationPackageIds = accommodationOptionRequestForAvailabilityList.stream()
                .flatMap(accommodationOptionRequestForAvailability ->
                        accommodationOptionRequestForAvailability.getAccommodationPackageRequestForAvailabilityList().stream()
                                .map(accommodationPackageRequestForAvailability -> accommodationPackageRequestForAvailability.getAccommodationPackageId())
                )
                .collect(Collectors.toSet());
        log.info(accommodationOptionRequestForAvailabilityList.toString());
        log.info(accommodationPackageIds.toString());
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

                                BigDecimal perPersonAccommodationPackagePrice = accommodationPackageRequestForAvailability.getPerNightRoomPrice().divideToIntegralValue(BigDecimal.valueOf(accommodationPackageEntity.getSuitableForPersons()));
                                availableAccommodationPackageEntity.setAvailableAccommodationOptionEntity(availableAccommodationOptionEntity);
                                availableAccommodationOptionEntity.setTotalOptionPricePerPerson(availableAccommodationOptionEntity.getTotalOptionPricePerPerson().add(perPersonAccommodationPackagePrice));

                                return availableAccommodationPackageEntity;
                            })
                            .toList();
                    availableAccommodationOptionEntity.setAvailableAccommodationPackageEntityList(availabilityGeneratedAccommodationPackageEntityList);
                    return availableAccommodationOptionEntity;
                })
                .collect(Collectors.toList());
    }

    private List<AvailableFoodOptionEntity> setFoods(AvailableTourPackageEntity availableTourPackageEntity, List<FoodOptionRequestForAvailability> foodOptions) {
        Set<Long> mealPackageIds = foodOptions.stream()
                .flatMap(foodOptionRequest ->
                        foodOptionRequest.getMealTypeWiseMealPackages().values().stream()
                                .flatMap(List::stream))
                .collect(Collectors.toSet());

        Map<Long, MealPackageEntity> mealPackageEntityMap = foodService.getMealPackageEntitiesByIds(mealPackageIds);
        List<AvailableFoodOptionEntity> availableFoodOptionEntityList = new LinkedList<>();

        foodOptions.stream()
                .forEach(foodOptionRequestForAvailability -> {
                    List<List<?>> lists = new LinkedList<>();
                    foodOptionRequestForAvailability.getMealTypeWiseMealPackages().entrySet().stream()
                            .forEach(integerListEntry -> {
                                List<AvailableMealPackageEntity> availableMealPackageEntityList = integerListEntry.getValue().stream()
                                        .map(mealPackageId -> {
                                            AvailableMealPackageEntity availableMealPackageEntity = new AvailableMealPackageEntity();
                                            availableMealPackageEntity.setMealPackageEntity(mealPackageEntityMap.get(mealPackageId));
                                            availableMealPackageEntity.setMealPackageAvailableInDay(foodOptionRequestForAvailability.getDayNumber());
                                            return availableMealPackageEntity;
                                        })
                                        .toList();
                                lists.add(availableMealPackageEntityList);
                            });
                    List<List<?>> combinations = CombinationGenerator.generateCombinations(lists);
                    combinations.forEach(combination -> {
                        AvailableFoodOptionEntity availableFoodOptionEntity = new AvailableFoodOptionEntity();
                        List<AvailableMealPackageEntity> availableMealPackageEntityList = (List<AvailableMealPackageEntity>) combination;
                        availableMealPackageEntityList = availableMealPackageEntityList.stream().peek(availableMealPackageEntity -> availableMealPackageEntity.setAvailableFoodOptionEntity(availableFoodOptionEntity)).toList();
                        availableFoodOptionEntity.setAvailabilityGeneratedMealPackageEntities(availableMealPackageEntityList);
                        availableFoodOptionEntity.setAvailableTourPackageEntity(availableTourPackageEntity);
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
                                int numberOfTravellers = availableTourPackageEntity.getTourPackageTypeEntity().getSuitableFor();
                                int suitableForPersons = transferPackageEntity.getSuitableForPersons();
                                BigDecimal perVehiclePerTripPrice = transferPackageRequestForAvailability.getPerVehiclePerTripPrice();
                                BigDecimal perPersonTransferPackagePrice = perVehiclePerTripPrice.divideToIntegralValue(BigDecimal.valueOf(suitableForPersons > numberOfTravellers ? numberOfTravellers : suitableForPersons));
                                availableTransferPackageEntity.setPerVehiclePerTripPrice(perVehiclePerTripPrice);
                                availableTransferOptionEntity.setTotalOptionPricePerPerson(availableTransferOptionEntity.getTotalOptionPricePerPerson().add(perPersonTransferPackagePrice));
                                return availableTransferPackageEntity;
                            })
                            .toList();
                    availableTransferOptionEntity.setAvailableTransferPackageEntityList(availableTransferPackageEntityList);
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
                                availableSpotEntryOptionEntity.setTotalOptionPricePerPerson(availableSpotEntryOptionEntity.getTotalOptionPricePerPerson().add(perPersonSpotEntryPackagePrice));
                                return availableSpotEntryPackageEntity;
                            })
                            .toList();
                    availableSpotEntryOptionEntity.setAvailableSpotEntryPackageEntityList(availableSpotEntryPackageEntityList);
                    return availableSpotEntryOptionEntity;
                })
                .collect(Collectors.toList());
    }
}
