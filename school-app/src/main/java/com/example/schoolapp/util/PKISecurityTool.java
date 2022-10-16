package com.example.schoolapp.util;

import com.example.schoolapp.dto.RequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
public class PKISecurityTool extends Base64Utils {

    public static final String CHARSET = "UTF-8";

    public static RequestWrapper encryptResponse(String receiverEncryptionPublicKey,
                                               String data) throws Exception {

        String randomString = generateAlphaNumeric(32);

        SecretKey secretKey = SecretKeyUtil.getSecretKey(randomString.getBytes(CHARSET));

        String aesEncryptedData = AESUtil.encryptUsingAES(data, secretKey);

        String encryptedSecretKeyInString = RSAUtil.encrypt(randomString, receiverEncryptionPublicKey);

        // Generating final requestWrapper
        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.setData(aesEncryptedData);
        requestWrapper.setSecretKey(encryptedSecretKeyInString);

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



    public static String decryptVerifier(String receiverEncryptionPrivateKey, String
            senderSignaturePublicKey, RequestWrapper requestWrapper) throws Exception {

        // Base64 Decoding dv
        byte[] signatureInByte = Base64Util.decode(requestWrapper.getSignature());

        // Verifying dv
        boolean status = RSAUtil.verifySignature(requestWrapper.getData(), senderSignaturePublicKey, signatureInByte);

        if (!status) {
            log.info("Signature not verified.");
            throw new RuntimeException("Signature not verified");
        }

        log.debug("Signature verified");

        // Decrypting secretKey
        String secretKeyInString = RSAUtil.decrypt(requestWrapper.getSecretKey(), receiverEncryptionPrivateKey);

        log.debug("Key decrypted");

        // String secret key to SecretKey
        SecretKey secretKey = SecretKeyUtil.stringToSecretKey(secretKeyInString);

        // Decrypting data
        return AESUtil.decrypt(requestWrapper.getData(), secretKey);

    }



}
