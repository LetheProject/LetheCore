package org.letheproject.lethecore.cryptography.encryption;

import org.bouncycastle.crypto.engines.SerpentEngine;
import org.bouncycastle.crypto.params.KeyParameter;

public class Serpent256 implements Encryptor {
    private final SerpentEngine engine;

    public Serpent256() {
        this.engine = new SerpentEngine();
    }

    private void validateKeyLength(byte[] key) {
        if (key.length != 32) {
            throw new RuntimeException(String.format("Key length for Serpent256 must be 32 bytes, not %d", key.length));
        }
    }

    private void validateDataLength(byte[] data) {
        if (data.length != 16) {
            throw new RuntimeException(String.format("Data length for Serpent256 must be 32 bytes, not %d", data.length));
        }
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        validateKeyLength(key);
        validateDataLength(data);
        KeyParameter keyParameter = new KeyParameter(key);
        engine.init(true, keyParameter);
        byte[] encrypted = new byte[data.length];
        engine.processBlock(data, 0, encrypted, 0);
        return encrypted;
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        validateKeyLength(key);
        validateDataLength(data);
        KeyParameter keyParameter = new KeyParameter(key);
        engine.init(false, keyParameter);
        byte[] decrypted = new byte[data.length];
        engine.processBlock(data, 0, decrypted, 0);
        return decrypted;
    }
}
