package org.letheproject.lethecore.cryptography.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 extends Hasher {
    @Override
    public byte[] hash(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return digest.digest(data);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
