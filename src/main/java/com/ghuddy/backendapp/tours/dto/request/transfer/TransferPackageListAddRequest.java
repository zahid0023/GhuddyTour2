package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import lombok.Data;

import java.util.List;

@Data
public class TransferPackageListAddRequest extends BaseRequest {
    private Long subscribedTourId;
    private List<TransferPackageRequest> transferPackageRequestList;

    @Override
    public void validate() throws AbstractException {
        
    }
}
