package org.letheproject.lethecore.dataprocessing;

import org.bouncycastle.util.Arrays;
import org.letheproject.lethecore.compression.Compressor;
import org.letheproject.lethecore.cryptography.encryption.Encryptor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Defines a sequence of data processing events, namely encryption and compression.
 * Provides for the execution of the sequence and its inverse.
 */
public class DataProcessor {
    private final List<Encryptor> encryptors;
    private final List<byte[]> encryptionKeys;
    private final List<byte[]> decryptionKeys;
    private final List<Compressor> preCompressors;
    private final List<Compressor> postCompressors;
    private final String id;

    /**
     * Instantiate a DataProcessor.
     */
    public DataProcessor(String id) {
        this.encryptors = new ArrayList<>();
        this.encryptionKeys = new ArrayList<>();
        this.decryptionKeys = new ArrayList<>();
        this.preCompressors  = new ArrayList<>();
        this.postCompressors = new ArrayList<>();
        this.id = id;
    }

    public DataProcessor() {
        this.encryptors = new ArrayList<>();
        this.encryptionKeys = new ArrayList<>();
        this.decryptionKeys = new ArrayList<>();
        this.preCompressors  = new ArrayList<>();
        this.postCompressors = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Add an encryptor to the sequence.
     * @param encryptor the encryptor to add.
     * @param key the key to use with the encryptor.
     * @return this.
     */
    public DataProcessor addEncryptor(Encryptor encryptor, byte[] key) {
        encryptors.add(encryptor);
        encryptionKeys.add(key);
        decryptionKeys.add(key);
        return this;
    }

    public DataProcessor addEncryptor(Encryptor encryptor, byte[] encryptionKey, byte[] decryptionKey) {
        encryptors.add(encryptor);
        encryptionKeys.add(encryptionKey);
        decryptionKeys.add(decryptionKey);
        return this;
    }

    /**
     * Add a compressor to the sequence, after encryption.
     * @param compressor the compressor to add.
     * @return this.
     */
    public DataProcessor addPostCompressor(Compressor compressor) {
        postCompressors.add(compressor);
        return this;
    }

    /**
     * Add a compressor to the sequence, before encryption.
     * @param compressor the compressor to add.
     * @return this.
     */
    public DataProcessor addPreCompressor(Compressor compressor) {
        preCompressors.add(compressor);
        return this;
    }

    /**
     * Execute the sequence forward.
     * @param in the data to process.
     * @return the processed data.
     */
    public byte[] forward(byte[] in) {
        byte[] out = Arrays.clone(in);
        for (int i = 0; i < preCompressors.size(); i++) {
            out = preCompressors.get(i).compress(out);
        }
        for (int i = 0; i < encryptors.size(); i++) {
            Encryptor encryptor = encryptors.get(i);
            byte[] key = encryptionKeys.get(i);
            out = encryptor.encrypt(out, key);
        }
        for (int i = 0; i < postCompressors.size(); i++) {
            out = postCompressors.get(i).compress(out);
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
        for (int i = postCompressors.size() - 1; i >= 0; i--) {
            Compressor compressor = postCompressors.get(i);
            out = compressor.decompress(out);
        }
        for (int i = encryptors.size() - 1; i >= 0; i--) {
            Encryptor encryptor = encryptors.get(i);
            byte[] key = decryptionKeys.get(i);
            out = encryptor.decrypt(out, key);
        }
        for (int i = preCompressors.size() - 1; i >= 0; i--) {
            Compressor compressor = preCompressors.get(i);
            out = compressor.decompress(out);
        }
        return out;
    }

    public String getId() {
        return id;
    }
}
