package org.letheproject.lethecore.node.shard;

import org.letheproject.lethecore.dataprocessing.DataProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * Class for reconstructing files from shards.
 */
public class FileReconstructor {
    private final DataProcessor processor;

    /**
     * Instantiate a FileConstructor.
     * @param processor the DataProcessor to use.
     */
    public FileReconstructor(DataProcessor processor) {
        this.processor = processor;
    }

    /**
     * Reconstruct the corresponding file fragment from the provided shard.
     * @param shard the shard.
     * @param location the directory containing the file being reconstructed.
     */
    public void nextShard(Shard shard, String location) {
        String name = new String(processor.backward(shard.getPrivateData().getName()));
        File file = new File(location + name);
        try {
            file.createNewFile();
            long start = shard.getPublicData().getPosition();
            byte[] data = processor.backward(shard.getPrivateData().getContents());
            SeekableByteChannel channel = Files.newByteChannel(file.toPath(), StandardOpenOption.WRITE).position(start);
            ByteBuffer buffer = ByteBuffer.allocate(data.length);
            buffer.put(data);
            channel.write(buffer);
            channel.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
