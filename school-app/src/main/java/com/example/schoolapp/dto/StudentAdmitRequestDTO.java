package com.example.schoolapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class StudentAdmitRequestDTO {

    private String name;
    private String phoneNumber;
    private String guardiansName;
    private String guardiansNumber;

}
