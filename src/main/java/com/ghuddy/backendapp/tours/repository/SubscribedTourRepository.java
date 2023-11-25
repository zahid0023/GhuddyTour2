package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.model.UserEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscribedTourRepository extends JpaRepository<SubscribedTourEntity, Long> {
    List<SubscribedTourEntity> getSubscribedTourEntitiesByMerchantEntityAndDeleted(UserEntity userEntity, Boolean isDeleted);

    Page<SubscribedTourEntity> getSubscribedTourEntitiesByMerchantEntityAndDeleted(UserEntity userEntity, Boolean isDeleted, PageRequest pageRequest);

}