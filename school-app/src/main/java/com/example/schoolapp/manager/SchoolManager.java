package com.example.schoolapp.manager;

import com.example.schoolapp.dto.*;
import com.example.schoolapp.util.PKIRequestResponseMapper;
import com.example.schoolapp.util.SchoolAppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class SchoolManager {

    public SchoolResponse decryptRequest(SchoolRequest schoolRequest) {
        DecryptionKeyPairDTO decryptionKeyPairDTO = SchoolAppUtil.getDecryptionKeyPair();

        AdmissionRequest admissionRequest = PKIRequestResponseMapper.decryptRequest(schoolRequest.getSchoolRequest(), decryptionKeyPairDTO);

        log.info("Request wrapper object, {}", admissionRequest.getStudentName());

        String admissionReferenceNumber = processAdmission(admissionRequest);

        EncryptionKeyPairDTO encryptionKeyPairDTO = SchoolAppUtil.getEncryptionKeyPair();
        RequestWrapper requestWrapper = PKIRequestResponseMapper.getEncryptedResponse(admissionReferenceNumber, encryptionKeyPairDTO);

        SchoolResponse schoolResponse = new SchoolResponse();
        schoolResponse.setSchoolResponse(requestWrapper);
        return schoolResponse;
    }

    public String processAdmission(AdmissionRequest admissionRequest) {

        log.info("Student with following details admitted : \n Name: {} , PhoneNumber: {}", admissionRequest.getStudentName(), admissionRequest.getPhoneNumber());
        String admissionReferenceNo = "ADMISSION-" + (int)(Math.random() * 1000);
        return admissionReferenceNo;
    }
}
