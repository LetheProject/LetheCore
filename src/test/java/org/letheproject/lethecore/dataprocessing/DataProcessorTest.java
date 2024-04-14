package org.letheproject.lethecore.dataprocessing;

import org.junit.jupiter.api.Test;
import org.letheproject.lethecore.compression.Deflate;
import org.letheproject.lethecore.cryptography.encryption.AES256;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class DataProcessorTest {

    @Test
    void forwardAndBackward() {
        SecureRandom random = new SecureRandom();
        byte[] aesKey = new byte[32];
        random.nextBytes(aesKey);

        byte[] data = "Hello World!".getBytes(StandardCharsets.UTF_8);
        DataProcessor dataProcessor = new DataProcessor()
                .addEncryptor(new AES256(random), aesKey)
                .addCompressor(new Deflate(4));
        byte[] processed = dataProcessor.forward(data);
        byte[] unprocessed = dataProcessor.backward(dataProcessor.forward(data));
        assertArrayEquals(data, unprocessed);
    }
}