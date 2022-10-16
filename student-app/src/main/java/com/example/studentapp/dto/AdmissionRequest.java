package com.example.studentapp.dto;

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
