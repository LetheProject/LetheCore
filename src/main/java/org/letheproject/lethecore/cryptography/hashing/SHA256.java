package org.letheproject.lethecore.cryptography.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 extends Hasher {
    private MessageDigest digest;

    public SHA256() {
        try {
            this.digest = MessageDigest.getInstance("SHA-256");
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
