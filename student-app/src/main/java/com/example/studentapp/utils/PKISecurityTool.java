package com.example.studentapp.utils;

import com.example.studentapp.dto.RequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
public class PKISecurityTool extends Base64Utils {

    public static final String CHARSET = "UTF-8";

    public static RequestWrapper encryptSigner(String receiverEncryptionPublicKey,
                                               String senderSignaturePrivateKey,
                                               String data) throws Exception {

        String randomString = generateAlphaNumeric(32);

        SecretKey secretKey = getSecretKey(randomString.getBytes(CHARSET));

        String aesEncryptedData = AESUtil.encryptUsingAES(data, secretKey);

        String encryptedSecretKeyInString = RSAUtil.encrypt(randomString, receiverEncryptionPublicKey);

        // Generating dv (Base64Encoded dv)
        String encodedSignature = RSAUtil.generateSignature(aesEncryptedData.getBytes(CHARSET), senderSignaturePrivateKey);

        // Generating final requestWrapper
        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.setData(aesEncryptedData);
        requestWrapper.setSecretKey(encryptedSecretKeyInString);
        requestWrapper.setSignature(encodedSignature);

        return requestWrapper;

    }

    public static String generateAlphaNumeric(int n) {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();

    }

    public static SecretKey getSecretKey(byte[] secretKey) throws Exception {
        if (secretKey.length % 16 == 0 && secretKey.length <= 32) {

            log.debug("Secret Key In String: {}", new String(secretKey, "UTF-8"));

            return new SecretKeySpec(secretKey, "AES");
        } else {
            throw new RuntimeException("AES Keys must 16, 24, 32 in length");
        }
    }

    public static String decryptResponse(String receiverEncryptionPrivateKey, RequestWrapper requestWrapper) throws Exception {

        // Decrypting secretKey
        String secretKeyInString = RSAUtil.decrypt(requestWrapper.getSecretKey(), receiverEncryptionPrivateKey);

        log.debug("Key decrypted");

        // String secret key to SecretKey
        SecretKey secretKey = SecretKeyUtil.stringToSecretKey(secretKeyInString);

        // Decrypting data
        return AESUtil.decrypt(requestWrapper.getData(), secretKey);

    }



}
