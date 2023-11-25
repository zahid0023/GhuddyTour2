package com.ghuddy.backendapp.tours.model.entities.accommodation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "accommodation_packages")
public class AccommodationPackageEntity extends BaseEntity {

    @Column(name = "is_shareable")
    private Boolean isShareable;

    @Column(name = "suitable_for_persons", nullable = false)
    private Integer suitableForPersons;

    @Column(name = "bed_count")
    private Integer bedCount;

    @Column(name = "bed_configuration", length = 100)
    private String bedConfiguration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_category_id", nullable = false)
    private TourRoomCategoryEntity tourRoomCategoryEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_type_id", nullable = false)
    private TourRoomTypeEntity tourRoomTypeEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_id", nullable = false)
    private TourAccommodationEntity tourAccommodationEntity;

    @Column(name = "active")
    private Boolean active = true;

    @NotNull
    @Column(name = "per_night_room_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal perNightRoomPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscribed_tour_id")
    private SubscribedTourEntity subscribedTourEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccommodationPackageEntity that = (AccommodationPackageEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}