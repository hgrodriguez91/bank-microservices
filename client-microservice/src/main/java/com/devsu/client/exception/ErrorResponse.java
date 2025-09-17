package com.devsu.client.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
