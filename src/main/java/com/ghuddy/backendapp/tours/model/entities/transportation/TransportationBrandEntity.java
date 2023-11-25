package com.ghuddy.backendapp.tours.model.entities.transportation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transportation_brands")
public class TransportationBrandEntity extends BaseEntity {

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @OneToMany(mappedBy = "transportationBrandEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationPackageEntity> tourPackageTransportationEntities = new ArrayList<>();

}