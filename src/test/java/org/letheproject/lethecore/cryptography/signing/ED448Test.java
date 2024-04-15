package org.letheproject.lethecore.cryptography.signing;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;

import static org.junit.jupiter.api.Assertions.*;

class ED448Test {

    @Test
    void signAndVerify() {
        SecureRandom random = new SecureRandom();
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("Ed448");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        keyPairGenerator.initialize(448, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        ED448 ed448 = new ED448();
        byte[] data = "Hello World!".getBytes(StandardCharsets.UTF_8);
        byte[] signature = ed448.sign(data, keyPair.getPrivate());
        boolean verification = ed448.verify(data, signature, keyPair.getPublic());
        assertTrue(verification);
    }
}