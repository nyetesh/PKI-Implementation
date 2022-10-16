package com.example.schoolapp.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
public class SecretKeyUtil {

    public static SecretKey getSecretKey(byte[] secretKey) throws Exception {
        if (secretKey.length % 16 == 0 && secretKey.length <= 32) {

            log.debug("Secret Key In String: {}", new String(secretKey, "UTF-8"));

            return new SecretKeySpec(secretKey, "AES");
        } else {
            throw new RuntimeException("AES Keys must 16, 24, 32 in length");
        }
    }

    public static SecretKey stringToSecretKey(String secretKeyInString) throws Exception {
        return getSecretKey(secretKeyInString.getBytes("UTF-8"));
    }
}
