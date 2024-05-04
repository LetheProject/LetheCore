package org.letheproject.lethecore.node.shard;

import org.letheproject.lethecore.dataprocessing.DataProcessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Provides file processing functionality.
 */
public class FileProcessor {
    private final DataProcessor processor;

    /**
     * Instantiate a file processor.
     * @param processor the data processor to use.
     */
    public FileProcessor(DataProcessor processor) {
        this.processor = processor;
    }

    private byte[] readFromFile(String location) {
        try {
            return Files.readAllBytes(Path.of(location));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(String location, byte[] data) {
        File file = new File(location);
        try {
            file.createNewFile();
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data);
            stream.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Process the contents of a file and put it in a different file.
     * @param from the path of the original file.
     * @param to the path of the different processed file.
     */
    public void toProcessed(String from, String to) {
        byte[] data = readFromFile(from);
        byte[] encoded = processor.forward(data);
        writeToFile(to, encoded);
    }

    /**
     * Convert the contents of the processed file back to their original state and put it in a different file.
     * @param from the path of the processed file.
     * @param to the path of the different file.
     */
    public void fromProcessed(String from, String to) {
        byte[] data = readFromFile(from);
        byte[] decoded = processor.backward(data);
        writeToFile(to, decoded);
    }
}
