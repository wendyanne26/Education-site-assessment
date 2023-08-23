package com.chinwendu.courseservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class GenericResponse {
    private boolean status;
    private String message;
    private Object data;

    public GenericResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
