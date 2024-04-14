package org.letheproject.lethecore.cryptography.encryption;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class Twofish256Test {

    @Test
    void encryptAndDecrypt() {
        SecureRandom random = new SecureRandom();
        Twofish256 twofish256 = new Twofish256();
        byte[] data = new byte[16];
        random.nextBytes(data);
        byte[] key = new byte[32];
        random.nextBytes(key);
        byte[] encrypted = twofish256.encrypt(data, key);
        byte[] decrypted = twofish256.decrypt(encrypted, key);
        assertArrayEquals(data, decrypted);
    }
}