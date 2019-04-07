package de.garkolym.boilerplate.responses;

import java.io.Serializable;

public class ErrorMessageResponse implements Serializable {
    private String message;

    public ErrorMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
