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
@Table(name = "food_items")
public class FoodItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "food_item_name", nullable = false)
    private String foodItemName;

    @ManyToMany(mappedBy = "foodItemEntities")
    private List<MealPackageEntity> mealPackageEntities = new ArrayList<>();

}