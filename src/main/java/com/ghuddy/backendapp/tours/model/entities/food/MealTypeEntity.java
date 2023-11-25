package com.ghuddy.backendapp.tours.model.entities.food;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "meal_types")
public class MealTypeEntity extends BaseEntity {

    @Column(name = "meal_type_name", nullable = false)
    private String mealTypeName;

    @OneToMany(mappedBy = "mealTypeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealPackageEntity> mealPackageEntities = new ArrayList<>();

}