package com.ghuddy.backendapp.dto.request;

import com.ghuddy.backendapp.exception.AbstractException;
import lombok.Data;

/**
 * @author : anisuzzaman
 * @created : 17/1/22
 **/

@Data
public abstract class BaseRequest {

    private String requestId;

    abstract public void validate() throws AbstractException;

    public String getRequestId() {
        return requestId;
    }

}
