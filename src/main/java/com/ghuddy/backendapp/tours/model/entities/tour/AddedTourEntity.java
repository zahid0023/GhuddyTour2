package com.ghuddy.backendapp.tours.model.entities.tour;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "added_tours")
public class AddedTourEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tour_name")
    private String tourName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_location_id", nullable = false)
    private DestinationLocationEntity destinationLocationEntity;

    @Column(name = "number_of_days", nullable = false)
    private Integer numberOfDays;

    @Column(name = "number_of_nights", nullable = false)
    private Integer numberOfNights;

    @Column(name = "short_address")
    private String shortAddress;

    @Column(name = "tour_tag")
    private String tourTag;

}