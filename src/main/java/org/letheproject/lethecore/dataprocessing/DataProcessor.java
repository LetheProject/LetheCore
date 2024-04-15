package org.letheproject.lethecore.dataprocessing;

import org.bouncycastle.util.Arrays;
import org.letheproject.lethecore.compression.Compressor;
import org.letheproject.lethecore.cryptography.encryption.Encryptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a sequence of data processing events, namely encryption and compression.
 * Provides for the execution of the sequence and its inverse.
 */
public class DataProcessor {
    private final List<Encryptor> encryptors;
    private final List<byte[]> encryptorKeys;
    private final List<Compressor> compressors;

    /**
     * Instantiate a DataProcessor.
     */
    public DataProcessor() {
        this.encryptors = new ArrayList<>();
        this.encryptorKeys = new ArrayList<>();
        this.compressors = new ArrayList<>();
    }

    /**
     * Add an encryptor to the sequence.
     * @param encryptor the encryptor to add.
     * @param key the key to use with the encryptor.
     * @return this.
     */
    public DataProcessor addEncryptor(Encryptor encryptor, byte[] key) {
        encryptors.add(encryptor);
        encryptorKeys.add(key);
        return this;
    }

    /**
     * Add a compressor to the sequence.
     * @param compressor the compressor to add.
     * @return this.
     */
    public DataProcessor addCompressor(Compressor compressor) {
        compressors.add(compressor);
        return this;
    }

    /**
     * Execute the sequence forward.
     * @param in the data to process.
     * @return the processed data.
     */
    public byte[] forward(byte[] in) {
        byte[] out = Arrays.clone(in);
        for (int i = 0; i < encryptors.size(); i++) {
            Encryptor encryptor = encryptors.get(i);
            byte[] key = encryptorKeys.get(i);
            out = encryptor.encrypt(out, key);
        }
        for (int i = 0; i < compressors.size(); i++) {
            out = compressors.get(i).compress(out);
        }
        return out;
    }

    /**
     * Execute the sequence backward.
     * @param in the data to process.
     * @return the processed data.
     */
    public byte[] backward(byte[] in) {
        byte[] out = Arrays.clone(in);
        for (int i = compressors.size() - 1; i >= 0; i--) {
            Compressor compressor = compressors.get(i);
            out = compressor.decompress(out);
        }
        for (int i = encryptors.size() - 1; i >= 0; i--) {
            Encryptor encryptor = encryptors.get(i);
            byte[] key = encryptorKeys.get(i);
            out = encryptor.decrypt(out, key);
        }
        return out;
    }
}
