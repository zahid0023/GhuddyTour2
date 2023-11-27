package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailableAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailableFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourItineraryEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailableTourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailableTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailableTransportationPackageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "available_tour_packages")
public class ESTourPackageDocument {
    @Id
    @Field(name = "available_tour_package_id", type = FieldType.Long)
    private Long availableTourPackageId;
    @Field(name = "subscribed_tour_id", type = FieldType.Long)
    private Long subscribedTourId; // for merchant reference
    @Field(name = "tour_id", type = FieldType.Long) // for tour reference
    private Long tourId;
    @Field(name = "tour_package_available_type_id", type = FieldType.Long)
    private Long tourPackageTypeId;
    @Field(name = "tour_package_name", type = FieldType.Keyword)
    private String tourPackageName;
    @Field(name = "tour_package_available_start_date", type = FieldType.Date)
    private LocalDate tourStartDate;
    @Field(name = "tour_package_available_reporting_time", type = FieldType.Date, format = DateFormat.custom, pattern = "HH:mm:ss")
    private String tourReportingTime;
    @Field(name = "tour_package_available_reporting_place", type = FieldType.Text)
    private String tourReportingPlace;
    @Field(name = "tour_package_available_total_seats", type = FieldType.Integer)
    private Integer totalSeats;
    @Field(name = "tour_package_available_bookable_seats", type = FieldType.Integer)
    private Integer bookableSeats;
    @Field(name = "tour_package_available_is_accommodation_included", type = FieldType.Boolean)
    private Boolean isAccommodationInclusive;
    @Field(name = "tour_package_available_is_food_included", type = FieldType.Boolean)
    private Boolean isFoodInclusive;
    @Field(name = "tour_package_available_is_transfer_included", type = FieldType.Boolean)
    private Boolean isTransferInclusive;
    @Field(name = "tour_package_available_is_guide_included", type = FieldType.Boolean)
    private Boolean isGuideInclusive;
    @Field(name = "tour_package_available_is_spot_entry_included", type = FieldType.Boolean)
    private Boolean isSpotEntryInclusive;
    @Field(name = "tour_package_available_accommodation_options", type = FieldType.Nested, includeInParent = true)
    private List<ESAccommodationOptionDocument> esAccommodationOptionDocumentList;
    @Field(name = "tour_package_available_food_options", type = FieldType.Nested, includeInParent = true)
    private List<ESFoodOptionDocument> esFoodOptionDataList;
    @Field(name = "tour_package_available_transfer_options", type = FieldType.Nested, includeInParent = true)
    private List<ESTransferOptionDocument> esTransferOptionDocumentList;
    @Field(name = "tour_package_available_transportation_options", type = FieldType.Nested, includeInParent = true)
    private List<ESTransportationPackageDocument> esTransportationPackageDocumentList;
    @Field(name = "tour_package_available_itinerary", type = FieldType.Nested, includeInParent = true)
    private List<ESSubscribedTourItineraryDocument> esSubscribedTourItineraryDocumentList;

    public ESTourPackageDocument(AvailableTourPackageEntity availableTourPackageEntity) {
        this.availableTourPackageId = availableTourPackageEntity.getId();
        SubscribedTourEntity subscribedTourEntity = availableTourPackageEntity.getSubscribedTourEntity();
        this.tourId = subscribedTourEntity.getTourEntity().getId();
        this.subscribedTourId = subscribedTourEntity.getId();
        this.tourPackageTypeId = availableTourPackageEntity.getTourPackageTypeEntity().getId();
        this.tourPackageName = availableTourPackageEntity.getTourPackageName();
        this.tourStartDate = availableTourPackageEntity.getTourStartDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.tourReportingTime = subscribedTourEntity.getTourReportingTime().format(formatter);
        this.tourReportingPlace = subscribedTourEntity.getTourReportingPlace();
        this.totalSeats = availableTourPackageEntity.getTotalSeats();
        this.bookableSeats = availableTourPackageEntity.getBookableSeats();
        this.isAccommodationInclusive = availableTourPackageEntity.getIsAccommodationInclusive();
        this.isFoodInclusive = availableTourPackageEntity.getIsFoodInclusive();
        this.isTransferInclusive = availableTourPackageEntity.getIsTransferInclusive();
        this.isGuideInclusive = availableTourPackageEntity.getIsGuideInclusive();
        this.isSpotEntryInclusive = availableTourPackageEntity.getIsSpotEntryInclusive();

        this.esAccommodationOptionDocumentList = getAvailableAccommodationOptions(availableTourPackageEntity.getAvailableAccommodationOptionEntities());
        this.esFoodOptionDataList = getAvailableFoodOptions(availableTourPackageEntity.getAvailableFoodOptionEntities());
        this.esTransferOptionDocumentList = getAvailableTransferOptions(availableTourPackageEntity.getAvailableTransferOptionEntities());
        this.esTransportationPackageDocumentList = getAvailableTransportationPackages(availableTourPackageEntity.getAvailableTransportationPackageEntities());
        this.esSubscribedTourItineraryDocumentList = getTourItinerary(subscribedTourEntity.getSubscribedTourItineraryEntities());

    }

    private List<ESAccommodationOptionDocument> getAvailableAccommodationOptions(List<AvailableAccommodationOptionEntity> availableAccommodationOptionEntityList) {
        return availableAccommodationOptionEntityList.stream()
                .map(availableAccommodationOptionEntity -> new ESAccommodationOptionDocument(availableAccommodationOptionEntity))
                .toList();
    }

    private List<ESFoodOptionDocument> getAvailableFoodOptions(List<AvailableFoodOptionEntity> availableFoodOptionEntityList){
      return availableFoodOptionEntityList.stream()
    .map(availableFoodOptionEntity -> new ESFoodOptionDocument(availableFoodOptionEntity))
            .toList();
    }

    private List<ESTransferOptionDocument> getAvailableTransferOptions(List<AvailableTransferOptionEntity> availableTransferOptionEntityList) {
        return availableTransferOptionEntityList.stream()
                .map(availableTransferOptionEntity -> new ESTransferOptionDocument(availableTransferOptionEntity))
                .toList();
    }

    private List<ESTransportationPackageDocument> getAvailableTransportationPackages(List<AvailableTransportationPackageEntity> availableTransportationPackageEntityList) {
        return availableTransportationPackageEntityList.stream()
                .map(availableTransportationPackageEntity -> new ESTransportationPackageDocument(availableTransportationPackageEntity))
                .toList();
    }

    private List<ESSubscribedTourItineraryDocument> getTourItinerary(List<SubscribedTourItineraryEntity> subscribedTourItineraryEntityList) {
        return subscribedTourItineraryEntityList.stream()
                .map(subscribedTourItineraryEntity -> new ESSubscribedTourItineraryDocument(subscribedTourItineraryEntity))
                .toList();
    }

}
