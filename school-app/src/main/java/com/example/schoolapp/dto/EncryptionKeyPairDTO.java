package com.example.schoolapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Nitesh Poudel
 */
@Getter
@Setter
public class EncryptionKeyPairDTO {

    private String schoolSignaturePrivateKey;
    private String studentEncryptionPublicKey;

}
