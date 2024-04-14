package org.letheproject.lethecore.cryptography.encryption;

public interface Encryptor {
    /**
     * Encrypt the data using the key.
     * @param data the data to encrypt.
     * @param key the key.
     * @return the encrypted data.
     */
    byte[] encrypt(byte[] data, byte[] key);

    /**
     * Decrypt the provided encrypted data using the key.
     * Presumes the initial vector concatenated with the encrypted data.
     * @param data the encrypted data concatenated with the initial vector.
     * @param key the key.
     * @return the decrypted data.
     */
    byte[] decrypt(byte[] data, byte[] key);
}
