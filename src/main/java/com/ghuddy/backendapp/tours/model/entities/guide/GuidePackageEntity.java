package com.ghuddy.backendapp.tours.model.entities.guide;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "guide_packages")
public class GuidePackageEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscribed_tour_id")
    private SubscribedTourEntity subscribedTourEntity;
    @Column(name = "number_of_guide_for_day")
    private Integer numberOfGuideForDay;
    @Column(name = "total_guide_price_for_day", precision = 10, scale = 2)
    private BigDecimal totalGuidePriceForDay;
    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

}