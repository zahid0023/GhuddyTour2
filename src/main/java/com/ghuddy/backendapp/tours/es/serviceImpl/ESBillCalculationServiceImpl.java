package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.sales.TourPackageOptionAddToCart;
import com.ghuddy.backendapp.tours.dto.response.sales.BillCalculationRequest;
import com.ghuddy.backendapp.tours.es.service.ESBillCalculationService;
import com.ghuddy.backendapp.tours.es.service.ESOptionsCombinationService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;

@Slf4j
@Service
public class ESBillCalculationServiceImpl implements ESBillCalculationService {
    private final ESOptionsCombinationService esOptionsCombinationService;
    private final RestHighLevelClient restHighLevelClient;

    public ESBillCalculationServiceImpl(ESOptionsCombinationService esOptionsCombinationService, RestHighLevelClient restHighLevelClient) {
        this.esOptionsCombinationService = esOptionsCombinationService;
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public void calculateBill(BillCalculationRequest billCalculationRequest) throws IOException {
        searchDocuments(billCalculationRequest);
    }

    private SearchResponse searchDocuments(BillCalculationRequest billCalculationRequest) throws IOException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        for (TourPackageOptionAddToCart tourPackageOption : billCalculationRequest.getTourPackageOptionAddToCartList()) {
            queryBuilder.should(QueryBuilders.matchQuery("available_tour_package_id", tourPackageOption.getAvailableTourPackageId()));
            queryBuilder.should(QueryBuilders.matchQuery("tour_package_accommodation_option_id", tourPackageOption.getAccommodationOptionId()));
            queryBuilder.should(QueryBuilders.matchQuery("tour_package_food_option_id", tourPackageOption.getFoodOptionId()));
            queryBuilder.should(QueryBuilders.matchQuery("tour_package_transfer_option_id", tourPackageOption.getTransferOptionId()));
            queryBuilder.should(QueryBuilders.matchQuery("tour_package_transportation_package_id", tourPackageOption.getTransportationPackageId()));
            queryBuilder.should(QueryBuilders.matchQuery("tour_guide_accommodation_option_id", tourPackageOption.getGuideOptionId()));
            queryBuilder.should(QueryBuilders.matchQuery("tour_package_spot_entry_option_id", tourPackageOption.getSpotEntryOptionId()));
        }

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);
        sourceBuilder.size(10); // Set the number of documents to retrieve

        SearchRequest searchRequest = new SearchRequest("available_tour_package_component_options_combination");
        searchRequest.source(sourceBuilder);
        log.info(sourceBuilder.query().toString());

        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }


}
