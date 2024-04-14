package org.letheproject.lethecore.cryptography.hashing;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class Argon2IDTest {

    @Test
    void hash() {
        Argon2ID argon2ID = new Argon2ID();
        byte[] data = "Hello World!".getBytes(StandardCharsets.UTF_8);
        byte[] saltA = new byte[]{0, 0, 0, 1};
        byte[] saltB = new byte[]{0, 0, 0, 2};
        byte[] saltC = new byte[]{0, 0, 0, 1};
        byte[] hashA = argon2ID.hash(data, saltA);
        byte[] hashB = argon2ID.hash(data, saltB);
        byte[] hashC = argon2ID.hash(data, saltC);
        boolean condition = true;
        for (int i = 0; i < hashA.length; i++) {
            if (hashA[i] != hashB[i]) {
                condition = false;
                break;
            }
        }
        assertFalse(condition);
        condition = true;
        for (int i = 0; i < hashA.length; i++) {
            if (hashA[i] != hashC[i]) {
                condition = false;
                break;
            }
        }
        assertTrue(condition);
    }
}