package org.letheproject.lethecore.cryptography.hashing;

public abstract class Hasher {
    /**
     * Hash the provided data.
     * @param data the data to hash.
     * @return the hash of the data.
     */
    public abstract byte[] hash(byte[] data);

    /**
     * Hash the concatenation of the provided salt and data.
     * @param data the data.
     * @param salt the salt.
     * @return the hash of the concatenated salt and data.
     */
    public byte[] hash(byte[] data, byte[] salt) {
        byte[] combined = new byte[data.length + salt.length];
        System.arraycopy(salt, 0, combined, 0, salt.length);
        System.arraycopy(data, 0, combined, salt.length, data.length);
        return hash(combined);
    }
}
