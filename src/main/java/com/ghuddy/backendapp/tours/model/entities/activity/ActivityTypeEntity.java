package com.ghuddy.backendapp.tours.model.entities.activity;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "activity_types")
public class ActivityTypeEntity extends BaseEntity {

    @Column(name = "activity_type_name", nullable = false)
    private String activityTypeName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "activityTypeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityEntity> activities = new ArrayList<>();

}