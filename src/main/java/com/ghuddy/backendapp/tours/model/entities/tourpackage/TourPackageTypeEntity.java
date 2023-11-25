package com.ghuddy.backendapp.tours.model.entities.tourpackage;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tour_package_types")
public class TourPackageTypeEntity extends BaseEntity {

    @Column(name = "package_type_name", nullable = false)
    private String packageTypeName;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "suitable_for", nullable = false)
    private Integer suitableFor;

    @OneToMany(mappedBy = "tourPackageTypeEntity", orphanRemoval = true)
    private Set<AvailableTourPackageEntity> availableTourPackageEntities = new LinkedHashSet<>();

}