package org.letheproject.lethecore.cryptography.hashing;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class SHA512Test {

    @Test
    void hash() {
        SHA512 sha512 = new SHA512();
        byte[] data = "Hello World!".getBytes(StandardCharsets.UTF_8);
        byte[] saltA = new byte[]{0, 0, 0, 1};
        byte[] saltB = new byte[]{0, 0, 0, 2};
        byte[] saltC = new byte[]{0, 0, 0, 1};
        byte[] hashA = sha512.hash(data, saltA);
        byte[] hashB = sha512.hash(data, saltB);
        byte[] hashC = sha512.hash(data, saltC);
        boolean condition = true;
        for (int i = 0; i < 4; i++) {
            if (hashA[i] != hashB[i]) {
                condition = false;
                break;
            }
        }
        assertFalse(condition);
        condition = true;
        for (int i = 0; i < 4; i++) {
            if (hashA[i] != hashC[i]) {
                condition = false;
                break;
            }
        }
        assertTrue(condition);
    }
}