package com.example.studentapp.utils;

import com.example.studentapp.constants.KeyConstants;
import com.example.studentapp.dto.DecryptKeyPair;
import com.example.studentapp.dto.EncryptionKeyPairDTO;

/**
 * @Author Nitesh Poudel
 */
public class StudentAppUtil {

    public static EncryptionKeyPairDTO convertToKeyPair(){

        EncryptionKeyPairDTO encryptionKeyPairDTO = new EncryptionKeyPairDTO();

        encryptionKeyPairDTO.setSchoolAppEncryptionPublicKey(KeyConstants.SchoolApp.encryptionPublicKey);
        encryptionKeyPairDTO.setStudentAppSignaturePrivateKey(KeyConstants.StudentApp.signaturePrivateKey);
        encryptionKeyPairDTO.setStudentAppEncryptionPrivateKey(KeyConstants.StudentApp.encryptionPrivateKey);
        encryptionKeyPairDTO.setClientKey("SCHOOL_APP");

        return encryptionKeyPairDTO;
    }

    public static DecryptKeyPair getDecryptKeyPair() {
        DecryptKeyPair decryptKeyPair = new DecryptKeyPair();

        decryptKeyPair.setStudentEncryptionPrivateKey(KeyConstants.StudentApp.encryptionPrivateKey);
        return decryptKeyPair;
    }
}
