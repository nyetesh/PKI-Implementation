package com.example.studentapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class ServerResponse {

    private boolean success;
    private Object object;
    private String code;
    private String message;
}
