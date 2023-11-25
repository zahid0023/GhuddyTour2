package com.ghuddy.backendapp.exception;

import lombok.Data;

/**
 * @author : anisuzzaman
 * @created : 12/1/22
 **/

@Data
public class AbstractException extends Exception {

    private static final long serialVersionUID = 9004674664241996502L;

    String messageCode;
    String requestId;

    public AbstractException(String messageCode) {
        super(messageCode);
        this.messageCode = messageCode;
    }

    public AbstractException(String messageCode, String requestId) {
        super(messageCode);
        this.messageCode = messageCode;
        this.requestId = requestId;
    }

}
