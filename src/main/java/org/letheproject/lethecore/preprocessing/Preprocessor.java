package org.letheproject.lethecore.preprocessing;

import org.bouncycastle.util.Arrays;
import org.letheproject.lethecore.compression.Compressor;
import org.letheproject.lethecore.cryptography.encryption.Encryptor;

import java.util.List;

public class Preprocessor {
    private List<Encryptor> encryptors;
    private List<byte[]> encryptorKeys;
    private List<Compressor> compressors;

    public Preprocessor addEncryptor(Encryptor encryptor, byte[] key) {
        encryptors.add(encryptor);
        encryptorKeys.add(key);
        return this;
    }

    public Preprocessor addCompressor(Compressor compressor) {
        compressors.add(compressor);
    }

    public byte[] apply(byte[] in) {
        byte[] out = Arrays.clone(in);
        for (int i = 0; i < encryptors.size(); i++) {
            out = encryptors.get(i).encrypt(out, encryptorKeys.get(i));
        }
        for (int i = 0; i < compressors.size(); i++) {
            out = compressors.get(i).compress(out);
        }
        return out;
    }
}