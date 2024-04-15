package org.letheproject.lethecore.cryptography.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512 extends Hasher {
    private final MessageDigest digest;

    public SHA512() {
        try {
            this.digest = MessageDigest.getInstance("SHA-512");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] hash(byte[] data) {
        return digest.digest(data);
    }
}
