package org.letheproject.lethecore.node.shard;

import org.letheproject.lethecore.util.ArrayOperations;

/**
 * Encapsulates the processed and presumably encrypted data of a shard.
 */
public class ShardPrivateData {
    private final byte[] contents;
    private final byte[] name;

    /**
     * Instantiate a ShardPrivateData object.
     * @param contents the processed contents.
     * @param name the processed name.
     */
    public ShardPrivateData(byte[] contents, byte[] name) {
        this.contents = contents;
        this.name = name;
    }

    /**
     * Convert the data to a byte array.
     * @return the byte array.
     */
    public byte[] toBytes() {
        return ArrayOperations.concatenate(contents, name);
    }

    public byte[] getContents() {
        return contents;
    }

    public byte[] getName() {
        return name;
    }
}
