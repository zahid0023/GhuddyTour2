package com.ghuddy.backendapp.tours.model.entities.food;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "available_meal_packages")
public class AvailableMealPackageEntity extends BaseEntity {
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "available_food_option_id", nullable = false)
    private AvailableFoodOptionEntity availableFoodOptionEntity;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "meal_package_id", nullable = false)
    private MealPackageEntity mealPackageEntity;
    @NotNull
    @Column(name = "day_number", nullable = false)
    private Integer mealPackageAvailableInDay;
    @NotNull
    @Column(name = "meal_package_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal mealPackagePrice = BigDecimal.ZERO;
}