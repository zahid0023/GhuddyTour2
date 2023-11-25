package com.ghuddy.backendapp.tours.model.entities.accommodation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour_room_categories")
public class TourRoomCategoryEntity extends BaseEntity {

    @Column(name = "room_category_name", nullable = false)
    private String roomCategoryName;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "tourRoomCategoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccommodationPackageEntity> tourPackageAccommodationEntities = new ArrayList<>();

}