package com.ghuddy.backendapp.tours.dto.request.guide;

import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import lombok.Data;

import java.util.List;

@Data
public class GuidePackageListAddRequest extends BaseRequest {
    private Long subscribedTourId;
    private List<GuidePackageRequest> guidePackageRequestList;

    @Override
    public void validate() throws AbstractException {
        
    }
}
