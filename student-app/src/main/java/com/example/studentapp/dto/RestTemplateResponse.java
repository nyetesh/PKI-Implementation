package com.example.studentapp.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestTemplateResponse<T> {

    private boolean obtained;
    private T obj;
    private HttpStatus httpStatus;
    private String errorMessage;

}
