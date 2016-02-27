package com.tripoin.xwp.data.dto.response;

import java.io.Serializable;

/**
 * Created by Ladies Man on 1/8/2016.
 */
public class DTOBaseResponse implements Serializable{

    private String errorCode;

    private String message;
    private String description;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BaseDTO{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
