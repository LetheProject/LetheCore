package org.letheproject.lethecore.cryptography.encryption;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;

import static org.junit.jupiter.api.Assertions.*;

class RSATest {

    @Test
    void encryptAndDecrypt() {
        SecureRandom random = new SecureRandom();
        RSA rsa = new RSA(random);
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096, random);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] data = "Hello World!".getBytes(StandardCharsets.UTF_8);
        byte[] encrypted = rsa.encrypt(data, keyPair.getPublic().getEncoded());
        byte[] decrypted = rsa.decrypt(encrypted, keyPair.getPrivate().getEncoded());
        assertArrayEquals(data, decrypted);
    }
}