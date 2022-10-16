package com.example.schoolapp.util;

import com.example.schoolapp.dto.AdmissionRequest;
import com.example.schoolapp.dto.DecryptionKeyPairDTO;
import com.example.schoolapp.dto.EncryptionKeyPairDTO;
import com.example.schoolapp.dto.RequestWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PKIRequestResponseMapper {


    public static RequestWrapper encryptResponse(String request, EncryptionKeyPairDTO encryptionKeyPairDTO) {
        try {
            String clientEncryptionPublicKey = encryptionKeyPairDTO.getStudentEncryptionPublicKey();

            RequestWrapper requestWrapper = PKISecurityTool.encryptResponse(clientEncryptionPublicKey,
                    request);

            return requestWrapper;
        } catch (Exception e) {
            log.error("Exception: ", e);
        }
        return null;
    }

    public static RequestWrapper getEncryptedResponse(String request, EncryptionKeyPairDTO encryptionKeyPairDTO) {
        RequestWrapper requestWrapper = encryptResponse(request, encryptionKeyPairDTO);
        log.info("Encrypted Request : {}", requestWrapper.toString());

        return requestWrapper;
    }

    public static AdmissionRequest decryptRequest(RequestWrapper requestWrapper, DecryptionKeyPairDTO decryptionKeyPairDTO) {
        try {
            String schoolEncryptionPrivateKey = decryptionKeyPairDTO.getSchoolEncryptionPrivateKey();
            String studentSignaturePublicKey = decryptionKeyPairDTO.getStudentSignaturePublicKey();

            String decryptedData = PKISecurityTool.decryptVerifier(schoolEncryptionPrivateKey,
                    studentSignaturePublicKey, requestWrapper);
            log.info("Raw response after decryption : {}", decryptedData);

            AdmissionRequest admissionRequest = JsonUtil.get(decryptedData, AdmissionRequest.class);

            return admissionRequest;
        } catch (Exception e) {
            log.error("Exception: ", e);
        }
        return null;
    }
}
