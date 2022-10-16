package com.example.studentapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class EncryptionKeyPairDTO {

    private String studentAppEncryptionPrivateKey;
    private String studentAppSignaturePrivateKey;

    private String schoolAppSignaturePublicKey;
    private String schoolAppEncryptionPublicKey;

    private String clientKey;

}
