package org.letheproject.lethecore.node.shard;

import org.junit.jupiter.api.Test;
import org.letheproject.lethecore.compression.Deflate;
import org.letheproject.lethecore.cryptography.encryption.AES256;
import org.letheproject.lethecore.dataprocessing.DataProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    @Test
    void test() {
        SecureRandom random = new SecureRandom();
        byte[] aesKey = new byte[32];
        random.nextBytes(aesKey);
        DataProcessor dataProcessor = new DataProcessor()
                .addPreCompressor(new Deflate(4))
                .addEncryptor(new AES256(random), aesKey);
        FileProcessor fileProcessor = new FileProcessor(dataProcessor);
        byte[] data = new byte[10_000];
        random.nextBytes(data);
        File originalFile = new File("file-processed-test-1");
        originalFile.deleteOnExit();
        try (FileOutputStream fileOutputStream = new FileOutputStream(originalFile)){
            originalFile.createNewFile();
            fileOutputStream.write(data);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileProcessor.toProcessed("file-processed-test-1", "file-processed-test-2");
        fileProcessor.fromProcessed("file-processed-test-2", "file-processed-test-3");
        File processedFile = new File("file-processed-test-2");
        File deprocessedFile = new File("file-processed-test-3");
        processedFile.deleteOnExit();
        deprocessedFile.deleteOnExit();
        byte[] processedData;
        byte[] deprocessedData;
        try {
            processedData = Files.readAllBytes(Path.of("file-processed-test-2"));
            deprocessedData = Files.readAllBytes(Path.of("file-processed-test-3"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertArrayEquals(data, deprocessedData);
        assertFalse(new String(processedData).equals(new String(deprocessedData)));
    }
}