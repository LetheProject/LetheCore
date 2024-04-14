package org.letheproject.lethecore.cryptography.encryption;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class Serpent256Test {

    @Test
    void encryptAndDecrypt() {
        SecureRandom random = new SecureRandom();
        Serpent256 serpent256 = new Serpent256();
        byte[] data = new byte[16];
        random.nextBytes(data);
        byte[] key = new byte[32];
        random.nextBytes(key);
        byte[] encrypted = serpent256.encrypt(data, key);
        byte[] decrypted = serpent256.decrypt(encrypted, key);
        assertArrayEquals(data, decrypted);
    }
}