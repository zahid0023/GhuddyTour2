package com.ghuddy.backendapp.tours.model.entities.transportation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transportation_modes")
public class TransportationModeEntity extends BaseEntity {

    @Column(name = "mode_name", nullable = false)
    private String modeName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "icon_url", nullable = false, columnDefinition = "text")
    private String iconUrl;

    @OneToMany(mappedBy = "transportationModeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationPackageEntity> tourPackageTransportationEntities = new ArrayList<>();

    @OneToMany(mappedBy = "transportationModeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferPackageEntity> transferPackageEntities = new LinkedList<>();

}