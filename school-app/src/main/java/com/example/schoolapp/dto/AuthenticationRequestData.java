package com.example.schoolapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class AuthenticationRequestData {

    private String clientKey;
    private String data;
    private String secretKey;
    private String signature;

}
