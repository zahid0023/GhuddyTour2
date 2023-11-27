package com.ghuddy.backendapp.tours.es.dto.response;

import com.ghuddy.backendapp.tours.es.model.data.ESTourData;
import lombok.Data;

@Data
public class ESTourResponse {
    private ESTourData esTourData;

    public ESTourResponse(ESTourData esTourData) {
        this.esTourData = esTourData;
    }
}
