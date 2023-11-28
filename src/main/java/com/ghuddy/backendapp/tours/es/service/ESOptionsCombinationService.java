package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.es.dto.data.ESOptionCombinationData;
import com.ghuddy.backendapp.tours.es.dto.response.ESComponentCombinationCheckResponse;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;

import java.util.List;


public interface ESOptionsCombinationService {

    /*
       The following code efficiently indexes the options for a specific tour.
       Instead of individually indexing each available tour package's options, this approach
       focuses on indexing options related to a particular tour.

       This method enhances clarity and maintainability, making it easier to manage
       and update options for the specified tour without the need to index each
       package's options separately.

       Note: This approach assumes a centralized data structure or database where
       tour options are organized by tour package which is part of a tour,
       allowing for streamlined indexing and retrieval of information.
    */
    Boolean indexOptionsCombinations(TourEntity tourEntity, String requestId);


    ESComponentCombinationCheckResponse getComponentCombinationPrice(ESOptionCombinationData esOptionCombinationData, String requestId);
}
