package com.example.schoolapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
@ToString
public class RequestWrapper {

    private String clientKey;
    private String data;
    private String secretKey;
    private String signature;

}
