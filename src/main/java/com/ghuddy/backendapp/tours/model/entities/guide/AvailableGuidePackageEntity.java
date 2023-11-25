package com.ghuddy.backendapp.tours.model.entities.guide;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "available_guide_packages")
public class AvailableGuidePackageEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "available_guide_option_id")
    private AvailableGuideOptionEntity availableGuideOptionEntity;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "guide_package_id", nullable = false)
    private GuidePackageEntity guidePackageEntity;
    @NotNull
    @Column(name = "total_guide_package_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalGuidePackagePrice;
    @NotNull
    @Column(name = "day_number", nullable = false)
    private Integer dayNumber;
    @NotNull
    @Column(name = "number_of_guides_for_day", nullable = false)
    private Integer numberOfGuidesForDay;

}