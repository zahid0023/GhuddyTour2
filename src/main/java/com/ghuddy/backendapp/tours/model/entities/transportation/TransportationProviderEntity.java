package com.ghuddy.backendapp.tours.model.entities.transportation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transportation_providers")
public class TransportationProviderEntity extends BaseEntity {

    @Column(name = "transportation_provider_name")
    private String transportationProviderName;

    @Column(name = "hotline_number", length = 20)
    private String hotlineNumber;

    @Column(name = "rating", precision = 2, scale = 1)
    private BigDecimal rating = BigDecimal.ZERO;

    @Column(name = "total_reviews")
    private Integer totalReviews = 0;

    @OneToMany(mappedBy = "transportationProviderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationPackageEntity> transportationPackageEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "transportationProviderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferPackageEntity> transferPackageEntities = new LinkedList<>();

}