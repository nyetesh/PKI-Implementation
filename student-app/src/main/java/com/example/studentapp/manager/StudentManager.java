package com.example.studentapp.manager;

import com.example.studentapp.connector.SchoolConnector;
import com.example.studentapp.dto.*;
import com.example.studentapp.utils.JsonUtil;
import com.example.studentapp.utils.PKIRequestResponseMapper;
import com.example.studentapp.utils.StudentAppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
@Component
public class StudentManager {

    @Autowired
    private SchoolConnector schoolConnector;

    public String admitStudent(AdmissionRequest admissionRequest) {

        EncryptionKeyPairDTO encryptionKeyPairDTO = StudentAppUtil.convertToKeyPair();

        String payload = JsonUtil.getString(admissionRequest);
        log.info("Plain payload request : " + payload);

        RequestWrapper encryptedPayload = PKIRequestResponseMapper.getEncryptedRequest(payload, encryptionKeyPairDTO);

        StudentAppRequest studentAppRequest = new StudentAppRequest();
        studentAppRequest.setSchoolRequest(encryptedPayload);

        ServerResponse serverResponse = schoolConnector.request(
                studentAppRequest,
                new ParameterizedTypeReference<SchoolResponse>() {
                }
        );

        DecryptKeyPair decryptKeyPair = StudentAppUtil.getDecryptKeyPair();
        SchoolResponse schoolResponse = (SchoolResponse)serverResponse.getObject();
        String referenceNumber = PKIRequestResponseMapper.decryptResponse(schoolResponse.getSchoolResponse() , decryptKeyPair);

        return referenceNumber;
    }
}
