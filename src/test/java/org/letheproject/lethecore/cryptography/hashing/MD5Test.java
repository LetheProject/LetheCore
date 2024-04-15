package org.letheproject.lethecore.cryptography.hashing;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MD5Test {

    @Test
    void hash() {
        MD5 md5 = new MD5();
        byte[] data = "Hello World!".getBytes(StandardCharsets.UTF_8);
        byte[] saltA = new byte[]{0, 0, 0, 1};
        byte[] saltB = new byte[]{0, 0, 0, 2};
        byte[] saltC = new byte[]{0, 0, 0, 1};
        byte[] hashA = md5.hash(data, saltA);
        byte[] hashB = md5.hash(data, saltB);
        byte[] hashC = md5.hash(data, saltC);
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