package org.letheproject.lethecore.cryptography.encryption;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;

import static org.junit.jupiter.api.Assertions.*;

class RSA4096Test {

    @Test
    void encryptAndDecrypt() {
        SecureRandom random = new SecureRandom();
        RSA4096 rsa4096 = new RSA4096(random);
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
        byte[] encrypted = rsa4096.encrypt(data, keyPair.getPublic().getEncoded());
        byte[] decrypted = rsa4096.decrypt(encrypted, keyPair.getPrivate().getEncoded());
        assertArrayEquals(data, decrypted);
    }
}