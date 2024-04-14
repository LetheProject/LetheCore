package org.letheproject.lethecore.compression;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class DeflateTest {

    @Test
    void compressAndDecompress() {
        SecureRandom random = new SecureRandom();
        byte[] data = new byte[128];
        random.nextBytes(data);
        Deflate deflate = new Deflate(4);
        byte[] compressed = deflate.compress(data);
        byte[] decompressed = deflate.decompress(compressed);
        assertArrayEquals(data, decompressed);
    }
}