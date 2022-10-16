package com.example.studentapp.utils;

import com.example.studentapp.dto.DecryptKeyPair;
import com.example.studentapp.dto.EncryptionKeyPairDTO;
import com.example.studentapp.dto.RequestWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PKIRequestResponseMapper {


    public static RequestWrapper encryptRequest(String request, EncryptionKeyPairDTO encryptionKeyPairDTO) {
        try {
            String serverSignaturePrivateKey = encryptionKeyPairDTO.getStudentAppSignaturePrivateKey();
            String clientEncryptionPublicKey = encryptionKeyPairDTO.getSchoolAppEncryptionPublicKey();

            RequestWrapper requestWrapper = PKISecurityTool.encryptSigner(clientEncryptionPublicKey,
                    serverSignaturePrivateKey, request);
            requestWrapper.setClientKey(encryptionKeyPairDTO.getClientKey());
            return requestWrapper;
        } catch (Exception e) {
            log.error("Exception: ", e);
            return null;
        }
    }

    public static RequestWrapper getEncryptedRequest(String request, EncryptionKeyPairDTO encryptionKeyPairDTO) {
        RequestWrapper requestWrapper = encryptRequest(request, encryptionKeyPairDTO);
        log.info("Encrypted Request : {}", requestWrapper.toString());

        return requestWrapper;
    }

    public static String decryptResponse(RequestWrapper requestWrapper, DecryptKeyPair decryptionKeyPairDTO) {
        try {
            String studentEncryptionPrivateKey = decryptionKeyPairDTO.getStudentEncryptionPrivateKey();

            String decryptedData = PKISecurityTool.decryptResponse(studentEncryptionPrivateKey,
                     requestWrapper);
            log.info("Raw response after decryption : {}", decryptedData);

            return decryptedData;
        } catch (Exception e) {
            log.error("Exception: ", e);
        }
        return null;
    }
}
