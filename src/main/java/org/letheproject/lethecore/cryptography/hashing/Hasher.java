package org.letheproject.lethecore.cryptography.hashing;

import org.letheproject.lethecore.ArrayOperations;

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
        return ArrayOperations.concatenate(salt, data);
    }
}
