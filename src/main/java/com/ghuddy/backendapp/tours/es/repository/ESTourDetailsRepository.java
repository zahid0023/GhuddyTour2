package com.ghuddy.backendapp.tours.es.repository;

import com.ghuddy.backendapp.tours.es.model.entities.ESTourDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESTourDetailsRepository extends ElasticsearchRepository<ESTourDocument, Long> {
}
