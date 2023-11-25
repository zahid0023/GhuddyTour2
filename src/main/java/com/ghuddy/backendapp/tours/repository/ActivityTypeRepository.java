package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.activity.ActivityTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityTypeEntity, Long> {

}