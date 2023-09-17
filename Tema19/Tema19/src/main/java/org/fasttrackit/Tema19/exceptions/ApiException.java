package org.fasttrackit.Tema19.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ApiException {
    private String messageFromException;
    private int entityId;
    private HttpStatus code;

    public ApiException(String messageFromException, int entityId, HttpStatus code) {
        this.messageFromException = messageFromException;
        this.entityId = entityId;
        this.code = code;
    }

    public String getMessageFromException() {
        return messageFromException;
    }

    public void setMessageFromException(String messageFromException) {
        this.messageFromException = messageFromException;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }
}
