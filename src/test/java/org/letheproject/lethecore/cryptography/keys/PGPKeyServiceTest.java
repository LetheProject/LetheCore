package org.letheproject.lethecore.cryptography.keys;

import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class PGPKeyServiceTest {
    @Test
    void test() {
        SecureRandom random = new SecureRandom();
        PGPKeyService service = new PGPKeyService(random);
        PGPKeyPair alice = service.generate("alice");
        PGPKeyPair bob = service.generate("bob");
        assertFalse(service.verify(bob.getPublicKey(), alice.getPublicKey()));
        PGPPublicKey bobSignedPublicKey = service.sign(bob.getPublicKey(), alice);
        assertTrue(service.verify(bobSignedPublicKey, alice.getPublicKey()));

        byte[] data = new byte[64];
        random.nextBytes(data);
        PGPSignature signature = service.sign(data, alice);
        assertTrue(service.verify(data, signature, alice.getPublicKey()));
        assertFalse(service.verify(data, signature, bob.getPublicKey()));
    }
}