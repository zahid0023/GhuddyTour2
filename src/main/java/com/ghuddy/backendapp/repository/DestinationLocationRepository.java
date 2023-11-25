package com.ghuddy.backendapp.repository;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationLocationRepository extends JpaRepository<DestinationLocationEntity, Long> {

    DestinationLocationEntity findByIdAndDeleted(Long id, boolean deleted);

}
