package com.example.studentapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class RequestWrapper {

    private String signature;
    private String data;
    private String secretKey;
    private String clientKey;

}
