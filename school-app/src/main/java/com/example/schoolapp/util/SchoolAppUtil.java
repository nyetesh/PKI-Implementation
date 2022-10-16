package com.example.schoolapp.util;

import com.example.schoolapp.constants.KeyConstants;
import com.example.schoolapp.dto.DecryptionKeyPairDTO;
import com.example.schoolapp.dto.EncryptionKeyPairDTO;

/**
 * @Author Nitesh Poudel
 */
public class SchoolAppUtil {

    public static DecryptionKeyPairDTO getDecryptionKeyPair() {
        DecryptionKeyPairDTO decryptionKeyPairDTO = new DecryptionKeyPairDTO();
        decryptionKeyPairDTO.setSchoolEncryptionPrivateKey(KeyConstants.SchoolApp.encryptionPrivateKey);
        decryptionKeyPairDTO.setStudentSignaturePublicKey(KeyConstants.StudentApp.signaturePublicKey);

        return decryptionKeyPairDTO;
    }

    public static EncryptionKeyPairDTO getEncryptionKeyPair() {
        EncryptionKeyPairDTO encryptionKeyPairDTO = new EncryptionKeyPairDTO();
        encryptionKeyPairDTO.setStudentEncryptionPublicKey(KeyConstants.StudentApp.encryptionPublicKey);
        return encryptionKeyPairDTO;
    }
}
