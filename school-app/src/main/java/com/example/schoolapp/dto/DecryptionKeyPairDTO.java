package com.example.schoolapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class DecryptionKeyPairDTO {

    private String schoolEncryptionPrivateKey;
    private String studentSignaturePublicKey;

}
