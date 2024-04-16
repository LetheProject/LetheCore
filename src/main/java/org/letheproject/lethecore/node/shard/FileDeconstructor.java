package org.letheproject.lethecore.node.shard;

import org.letheproject.lethecore.cryptography.signing.Signer;
import org.letheproject.lethecore.dataprocessing.DataProcessor;
import org.letheproject.lethecore.util.ArrayOperations;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.PrivateKey;
import java.time.Instant;
import java.util.*;

/**
 * Class for converting files into lists of shards.
 */
public class FileDeconstructor {
    private final long size;
    private final DataProcessor processor;
    private final Signer signer;
    private final PrivateKey privateKey;
    private final String owner;
    private SeekableByteChannel channel;
    private String shardUUID;
    private String created;
    private byte[] name;
    private long position;
    private long start;

    /**
     * Instantiate a FileDeconstructor.
     * @param size the max size in bytes of a shard's contents.
     * @param processor the data processor to be used to process the private data of all created shards.
     * @param signer the signer to be used for signing shards.
     * @param privateKey the private key to be used by the signer.
     * @param owner the UUID of this node.
     * @param fileLocation the location of the file on disk.
     */
    public FileDeconstructor(long size, DataProcessor processor, Signer signer, PrivateKey privateKey, String owner, String fileLocation) {
        this.size = size;
        if (size >= Integer.MAX_VALUE) {
            throw new RuntimeException(String.format("Size must be less than integer max value, not %d", size));
        }
        this.processor = processor;
        this.signer = signer;
        this.privateKey = privateKey;
        this.owner = owner;
        this.setFile(fileLocation);
    }

    private void setFile(String location) {
        try {
            File file = new File(location);
            channel = Files.newByteChannel(file.toPath(), StandardOpenOption.READ);
            shardUUID = UUID.randomUUID().toString();
            created = Date.from(Instant.now()).toString();
            name = processor.forward(file.getName().getBytes(StandardCharsets.UTF_8));
            position = 0;
            start = 0;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert the next fragment of the file to shard.
     * @return the shard, or null if the end of the file was reached last invocation.
     */
    public Shard nextShard() {
        try {
            long fileSize = channel.size();
            if (start >= fileSize) {
                channel.close();
                return null;
            }
            long end = Math.min(start + size, fileSize);
            int size = (int) (end - start);
            ByteBuffer buffer = ByteBuffer.allocate(size);
            channel = channel.position(start);
            channel.read(buffer);
            byte[] data = buffer.array();
            start = end;

            byte[] contents = processor.forward(data);

            ShardPrivateData privateData = new ShardPrivateData(contents, name);
            ShardPublicData publicData = new ShardPublicData(owner, shardUUID, processor.getId(), created, position);

            position += data.length;
            byte[] signature = signer.sign(ArrayOperations.concatenate(privateData.toBytes(), publicData.toBytes()), privateKey);
            Shard shard = new Shard(privateData, publicData, signature);
            return shard;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
