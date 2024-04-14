package org.letheproject.lethecore.cryptography.encryption;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import static org.junit.jupiter.api.Assertions.*;

class AES256Test {
    @Test
    void encryptAndDecrypt() {
        SecureRandom random = new SecureRandom();
        AES256 aes256 = new AES256(random);
        byte[] data = "Hello World!".getBytes(StandardCharsets.UTF_8);
        byte[] key = new byte[32];
        random.nextBytes(key);
        byte[] encrypted = aes256.encrypt(data, key);
        byte[] decrypted = aes256.decrypt(encrypted, key);
        assertArrayEquals(data, decrypted);
    }
}