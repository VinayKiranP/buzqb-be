package com.buz.buzqb.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
    String message;
    String description;
    int code;
    String status;

    public static ErrorDto getErrorFromException(Exception e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .status("500")
                .code(500)
                .description("")
                .build();
    }


}
