package com.ghuddy.backendapp.tours.model.entities.accommodation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour_accommodations")
public class TourAccommodationEntity extends BaseEntity {

    @Column(name = "accommodation_name", nullable = false)
    private String accommodationName;

    @Column(name = "accommodation_address", nullable = false)
    private String accommodationAddress;

    @Column(name = "average_rating", precision = 3, scale = 1)
    private BigDecimal averageRating = BigDecimal.ZERO;

    @Column(name = "total_reviews")
    private Integer totalReviews = 0;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "accommodation_type_id", nullable = false)
    private TourAccommodationTypeEntity tourAccommodationTypeEntity;

    @OneToMany(mappedBy = "tourAccommodationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccommodationPackageEntity> tourPackageAccommodationEntities = new ArrayList<>();

}