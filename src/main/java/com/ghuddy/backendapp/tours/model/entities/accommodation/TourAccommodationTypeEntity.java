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
@Table(name = "tour_accommodation_types")
public class TourAccommodationTypeEntity extends BaseEntity {

    @Column(name = "accommodation_type_name", nullable = false)
    private String accommodationTypeName;

    @OneToMany(mappedBy = "tourAccommodationTypeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourAccommodationEntity> tourAccommodationEntities = new ArrayList<>();

}