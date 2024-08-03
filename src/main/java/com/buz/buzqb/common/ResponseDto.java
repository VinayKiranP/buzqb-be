package com.buz.buzqb.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class ResponseDto {
    private Object data;
    private boolean success;
    private ArrayList<String> errors;

    public void setError(String errorMessage) {
        ErrorDto.builder().message(errorMessage).build();
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(errorMessage);
    }

    public void setError(ErrorDto errorDto) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(errorDto.getMessage());
    }
}

