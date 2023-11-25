package com.ghuddy.backendapp.tours.model.entities.tour;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.activity.ActivityEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "subscribed_tour_itineraries")
public class SubscribedTourItineraryEntity extends BaseEntity {

    @Column(name = "active")
    private Boolean isActive;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscribed_tour_id", nullable = false)
    private SubscribedTourEntity subscribedTourEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_id", nullable = false)
    private ActivityEntity activityEntity;

    @NotNull
    @Column(name = "activity_day_number", nullable = false)
    private Integer activityDayNumber;

    @NotNull
    @Column(name = "activity_start_time", nullable = false)
    private LocalTime activityStartTime;

    @NotNull
    @Column(name = "activity_end_time", nullable = false)
    private LocalTime activityEndTime;

}