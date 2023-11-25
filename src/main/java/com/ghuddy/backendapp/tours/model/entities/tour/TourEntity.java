package com.ghuddy.backendapp.tours.model.entities.tour;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tours")
public class TourEntity extends BaseEntity {
    @Column(name = "thumb_image_url", columnDefinition = "text")
    private String thumbImageUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_tour_id")
    private AddedTourEntity addedTourEntity;

    @OneToMany(mappedBy = "tourEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<TourItineraryEntity> tourItineraryEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<TourSpecialityEntity> tourSpecialityEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubscribedTourEntity> subscribedTourEntities = new ArrayList<>();

}