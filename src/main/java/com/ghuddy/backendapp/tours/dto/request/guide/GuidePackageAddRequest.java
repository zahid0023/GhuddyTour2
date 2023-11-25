package com.ghuddy.backendapp.tours.dto.request.guide;

import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import lombok.Data;

@Data
public class GuidePackageAddRequest extends BaseRequest {
    private Long subscribedTourId;
    private GuidePackageRequest guidePackageRequest;

    @Override
    public void validate() throws AbstractException {

    }
}
