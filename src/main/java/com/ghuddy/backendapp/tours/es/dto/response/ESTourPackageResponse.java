package com.ghuddy.backendapp.tours.es.dto.response;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.es.model.data.ESTourPackageData;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ESTourPackageResponse extends BaseResponse {
    private Map<Long, List<ESTourPackageData>> esTourPackageData;

    public ESTourPackageResponse(Map<Long, List<ESTourPackageData>> esTourPackageData, String requestId) {
        this.esTourPackageData = esTourPackageData;
        this.setRequestId(requestId);
    }
}
