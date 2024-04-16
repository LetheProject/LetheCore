package org.letheproject.lethecore.node.shard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.letheproject.lethecore.compression.Deflate;
import org.letheproject.lethecore.cryptography.encryption.AES256;
import org.letheproject.lethecore.cryptography.signing.ED448;
import org.letheproject.lethecore.dataprocessing.DataProcessor;

import java.io.*;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileDeconstructorAndFileReconstructorTest {
    private SecureRandom random;
    private DataProcessor processor;
    private FileDeconstructor deconstructor;
    private FileReconstructor reconstructor;

    @BeforeEach
    void setUp() {
        try {
            random = new SecureRandom();
            File file = new File("unit-test.txt");
            file.createNewFile();
            file.deleteOnExit();
            FileOutputStream stream = new FileOutputStream(file);
            byte[] data = new byte[4096];
            random.nextBytes(data);
            stream.write(data);
            stream.close();
            // create clone that FileReconstructor won't touch
            File other = new File("unit-test-old.txt");
            other.createNewFile();
            other.deleteOnExit();
            stream = new FileOutputStream(other);
            stream.write(data);
            stream.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void run() {
        byte[] aesKey = new byte[32];
        random.nextBytes(aesKey);
        AES256 aes256 = new AES256(random);
        Deflate deflate = new Deflate(4);
        processor = new DataProcessor()
                .addPreCompressor(deflate)
                .addEncryptor(aes256, aesKey)
                .addPostCompressor(deflate);

        ED448 ed448 = new ED448();
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("Ed448");
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyPairGenerator.initialize(448, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        deconstructor = new FileDeconstructor(1024, processor, ed448, keyPair.getPrivate(), "owner", "unit-test.txt");
        reconstructor = new FileReconstructor(processor);
        List<Shard> shards = new ArrayList<>();
        Shard shard;
        while ((shard = deconstructor.nextShard()) != null) {
            shards.add(shard);
        }
        shards.forEach((s) -> reconstructor.nextShard(s, ""));
        try {
            byte[] a = Files.readAllBytes(new File("unit-test.txt").toPath());
            byte[] b = Files.readAllBytes(new File("unit-test-old.txt").toPath());
            assertArrayEquals(a, b);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}