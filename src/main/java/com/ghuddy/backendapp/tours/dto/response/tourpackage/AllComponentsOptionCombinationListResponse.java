package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tourpackage.AllComponentCombinationOptionData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.InclusiveComponentOptionCombinationData;
import lombok.Data;

import java.util.List;

@Data
public class AllComponentsOptionCombinationListResponse extends BaseResponse {
    @JsonProperty("tour_package_inclusive_options")
    private List<InclusiveComponentOptionCombinationData> inclusiveComponentOptionCombinationDataList;
    @JsonProperty("tour_package_all_options")
    private List<AllComponentCombinationOptionData> allComponentCombinationOptionDataList;

    public AllComponentsOptionCombinationListResponse(List<InclusiveComponentOptionCombinationData> inclusiveComponentOptionCombinationDataList,List<AllComponentCombinationOptionData> allComponentCombinationOptionDataList, String requestId){
        this.inclusiveComponentOptionCombinationDataList = inclusiveComponentOptionCombinationDataList;
        this.allComponentCombinationOptionDataList = allComponentCombinationOptionDataList;
        this.setRequestId(requestId);
    }

}
