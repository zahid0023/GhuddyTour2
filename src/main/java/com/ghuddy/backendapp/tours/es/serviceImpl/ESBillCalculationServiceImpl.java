package com.ghuddy.backendapp.tours.es.serviceImpl;

import com.ghuddy.backendapp.tours.dto.data.BillData;
import com.ghuddy.backendapp.tours.dto.data.ProductMeta;
import com.ghuddy.backendapp.tours.dto.request.sales.TourPackageOptionAddToCart;
import com.ghuddy.backendapp.tours.dto.request.sales.BillCalculationRequest;
import com.ghuddy.backendapp.tours.es.dto.response.ESBillCalculationResponse;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.es.service.ESBillCalculationService;
import com.ghuddy.backendapp.tours.es.service.ESOptionsCombinationService;
import com.ghuddy.backendapp.tours.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ESBillCalculationResponse calculateBill(BillCalculationRequest billCalculationRequest) throws IOException {
        HashMap<String, Integer> optionQuantityMap = new HashMap<>();
        billCalculationRequest.getTourPackageOptionAddToCartList()
                .forEach(tourPackageOptionAddToCart -> {
                    String key = StringUtil.generateKey(
                            tourPackageOptionAddToCart.getAvailableTourPackageId(),
                            tourPackageOptionAddToCart.getAccommodationOptionId(),
                            tourPackageOptionAddToCart.getFoodOptionId(),
                            tourPackageOptionAddToCart.getTransferOptionId(),
                            tourPackageOptionAddToCart.getTransportationPackageId(),
                            tourPackageOptionAddToCart.getGuideOptionId(),
                            tourPackageOptionAddToCart.getSpotEntryOptionId());
                    optionQuantityMap.put(key, tourPackageOptionAddToCart.getQuantity());
                });
        log.info(optionQuantityMap.toString());

        SearchResponse searchResponse = searchDocuments(billCalculationRequest);
        HashMap<String, ESTourComponentOptionCombinationDocument> documents = extractDocuments(searchResponse);
        List<ProductMeta> productMetaList = documents.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();
                    ESTourComponentOptionCombinationDocument esTourComponentOptionCombinationDocument = entry.getValue();
                    ProductMeta productMeta = new ProductMeta(esTourComponentOptionCombinationDocument, optionQuantityMap.get(key));
                    return productMeta;
                })
                .toList();

        BigDecimal totalBlackPrice = productMetaList.stream()
                .map(ProductMeta::getTotalBlackPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalRedPrice = productMetaList.stream()
                .map(ProductMeta::getTotalRedPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BillData billData = new BillData(productMetaList, totalBlackPrice, totalRedPrice);

        return new ESBillCalculationResponse(billData, billCalculationRequest.getRequestId());
    }

    // Method to generate a key based on fields (excluding quantity)

    private SearchResponse searchDocuments(BillCalculationRequest billCalculationRequest) throws IOException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        for (TourPackageOptionAddToCart tourPackageOption : billCalculationRequest.getTourPackageOptionAddToCartList()) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("available_tour_package_id", tourPackageOption.getAvailableTourPackageId()))
                    .must(QueryBuilders.matchQuery("accommodation_option_id", tourPackageOption.getAccommodationOptionId()))
                    .must(QueryBuilders.matchQuery("food_option_id", tourPackageOption.getFoodOptionId()))
                    .must(QueryBuilders.matchQuery("transfer_option_id", tourPackageOption.getTransferOptionId()))
                    .must(QueryBuilders.matchQuery("transportation_option_id", tourPackageOption.getTransportationPackageId()))
                    .must(QueryBuilders.matchQuery("guide_option_id", tourPackageOption.getGuideOptionId()))
                    .must(QueryBuilders.matchQuery("spot_entry_option_id", tourPackageOption.getSpotEntryOptionId()));

            // Add the current boolQuery to the main query using a "should" clause
            queryBuilder.should(boolQuery);
        }

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);
        sourceBuilder.size(10); // Set the number of documents to retrieve

        SearchRequest searchRequest = new SearchRequest("available_tour_package_component_options_combination");
        searchRequest.source(sourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    private HashMap<String, ESTourComponentOptionCombinationDocument> extractDocuments(SearchResponse searchResponse) {
        HashMap<String, ESTourComponentOptionCombinationDocument> keyDocumentMap = new HashMap<>();

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            ESTourComponentOptionCombinationDocument document = mapToDocument(sourceAsMap);
            keyDocumentMap.put(document.getKey(), document);
        }
        return keyDocumentMap;
    }

    private ESTourComponentOptionCombinationDocument mapToDocument(Map<String, Object> sourceAsMap) {
        ESTourComponentOptionCombinationDocument document = new ESTourComponentOptionCombinationDocument();

        document.setOptionId(getLongValue(sourceAsMap, "option_id"));
        document.setTitle(getStringValue(sourceAsMap, "title"));
        document.setConfigId(getStringValue(sourceAsMap, "config_id"));
        document.setAvailableTourPackageId(getLongValue(sourceAsMap, "available_tour_package_id"));
        document.setAccommodationOptionId(getLongValue(sourceAsMap, "accommodation_option_id"));
        document.setFoodOptionId(getLongValue(sourceAsMap, "food_option_id"));
        document.setTransferOptionId(getLongValue(sourceAsMap, "transfer_option_id"));
        document.setTransportationOptionId(getLongValue(sourceAsMap, "transportation_option_id"));
        document.setGuideOptionId(getLongValue(sourceAsMap, "guide_option_id"));
        document.setSpotEntryOptionId(getLongValue(sourceAsMap, "spot_entry_option_id"));
        document.setGhuddyWebsiteBlackPrice(getBigDecimalValue(sourceAsMap, "black_price"));
        document.setGhuddyWebsiteRedPrice(getBigDecimalValue(sourceAsMap, "red_price"));
        document.setKey(getStringValue(sourceAsMap, "key"));

        return document;
    }

    private Long getLongValue(Map<String, Object> sourceAsMap, String key) {
        return sourceAsMap.containsKey(key) ? ((Number) sourceAsMap.get(key)).longValue() : null;
    }

    private String getStringValue(Map<String, Object> sourceAsMap, String key) {
        return sourceAsMap.containsKey(key) ? sourceAsMap.get(key).toString() : null;
    }

    private BigDecimal getBigDecimalValue(Map<String, Object> sourceAsMap, String key) {
        return sourceAsMap.containsKey(key) ? new BigDecimal(sourceAsMap.get(key).toString()) : null;
    }

}
