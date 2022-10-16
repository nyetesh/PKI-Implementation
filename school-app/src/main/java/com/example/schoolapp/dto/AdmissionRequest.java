package com.example.schoolapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class AdmissionRequest {

    private String studentName;
    private String phoneNumber;
    private String guardiansName;
    private String guardiansPhoneNumber;

}
