package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import lombok.Data;

@Data
public class TransferPackageAddRequest extends BaseRequest {
    private Long subscribedTourId;
    private TransferPackageRequest transferPackageRequest;

    @Override
    public void validate() throws AbstractException {

    }
}
