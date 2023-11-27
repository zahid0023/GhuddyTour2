package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferOptionEntity;
import com.ghuddy.backendapp.tours.service.TourPackagePriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TourPackagePriceServiceImpl implements TourPackagePriceService {
    @Override
    public BigDecimal perPersonAccommodationOptionPrice(AvailableAccommodationOptionEntity availableAccommodationOptionEntity, int numberOfTravellers) {
        return availableAccommodationOptionEntity.getAvailableAccommodationPackageEntityList().stream()
                .map(availableAccommodationPackageEntity -> {
                    int suitableForPersons = availableAccommodationPackageEntity.getAccommodationPackageEntity().getSuitableForPersons();
                    int dividedByPersons = suitableForPersons > numberOfTravellers ? numberOfTravellers : suitableForPersons;
                    BigDecimal perNightRoomPrice = availableAccommodationPackageEntity.getAccommodationPackagePrice();
                    return perNightRoomPrice.divideToIntegralValue(BigDecimal.valueOf(dividedByPersons));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal perPersonFoodOptionPrice(AvailableFoodOptionEntity availableFoodOptionEntity) {
        return availableFoodOptionEntity.getAvailabilityGeneratedMealPackageEntities().stream()
                .map(availableMealPackageEntity -> availableMealPackageEntity.getMealPackagePrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal perPersonTransferOptionPrice(AvailableTransferOptionEntity availableTransferOptionEntity, int numberOfTravellers) {
        return availableTransferOptionEntity.getAvailableTransferPackageEntityList().stream()
                .map(transferPackageEntity -> {
                    int suitableForPersons = transferPackageEntity.getTransferPackageEntity().getSuitableForPersons();
                    int divideBy = suitableForPersons > numberOfTravellers ? numberOfTravellers : suitableForPersons;
                    BigDecimal perVehiclePerTripPrice = transferPackageEntity.getPerVehiclePerTripPrice();
                    return perVehiclePerTripPrice.divideToIntegralValue(BigDecimal.valueOf(divideBy));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal perPersonGuideOptionPrice(AvailableGuideOptionEntity availableGuideOptionEntity, int numberOfTravellers) {
        return availableGuideOptionEntity.getAvailableGuidePackageEntityList().stream()
                .map(availableGuidePackageEntity -> {
                    BigDecimal totalGuidePrice = availableGuidePackageEntity.getTotalGuidePackagePrice();
                    return totalGuidePrice.divideToIntegralValue(BigDecimal.valueOf(numberOfTravellers));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal perPersonSpotEntryOptionPrice(AvailableSpotEntryOptionEntity availableSpotEntryOptionEntity) {
        return availableSpotEntryOptionEntity.getAvailableSpotEntryPackageEntityList().stream()
                .map(availableSpotEntryPackageEntity -> availableSpotEntryPackageEntity.getSpotEntryPricePerPerson())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
