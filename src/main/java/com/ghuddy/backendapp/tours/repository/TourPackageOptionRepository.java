package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.combination.AvailableTourPackageOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourPackageOptionRepository extends JpaRepository<AvailableTourPackageOptionEntity, Long> {
    List<AvailableTourPackageOptionEntity> getAllByIdInAndDeletedFalse(List<Long> ids);

}

