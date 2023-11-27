package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailableGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailableSpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferOptionEntity;

import java.math.BigDecimal;

public interface TourPackagePriceService {
    BigDecimal perPersonAccommodationOptionPrice(AvailableAccommodationOptionEntity availableAccommodationOptionEntity, int numberOfTravellers);

    BigDecimal perPersonFoodOptionPrice(AvailableFoodOptionEntity availableFoodOptionEntity);

    BigDecimal perPersonTransferOptionPrice(AvailableTransferOptionEntity availableTransferOptionEntity, int numberOfTravellers);

    BigDecimal perPersonGuideOptionPrice(AvailableGuideOptionEntity availableGuideOptionEntity, int numberOfTravellers);

    BigDecimal perPersonSpotEntryOptionPrice(AvailableSpotEntryOptionEntity availableSpotEntryOptionEntity);
}