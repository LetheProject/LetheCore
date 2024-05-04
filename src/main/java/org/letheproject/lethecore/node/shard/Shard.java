package org.letheproject.lethecore.node.shard;

/**
 * Represents a processed fragment of a file.
 * Contains both the public and private information of the shard.
 * The private information is presumed to be encrypted by the owner and is unreadable by the network.
 */
public class Shard implements Comparable<Shard> {
    private final ShardPublicData shardPublicData;
    private final byte[] contents;
    private final byte[] name;
    private final byte[] virtualFilePath;
    private final byte[] positionInOriginFile;

    private final long duplicationID;
    private final long created;

    /**
     * Instantiate a shard.
     * @param shardPublicData the public information of the shard.
     * @param contents the enciphered contents of the shard.
     * @param name the enciphered name of the shard.
     * @param virtualFilePath the enciphered virtual file path of the shard.
     * @param positionInOriginFile the enciphered position of the shard in the origin file.
     */

    public Shard(ShardPublicData shardPublicData, byte[] contents, byte[] name, byte[] virtualFilePath, byte[] positionInOriginFile) {
        this.shardPublicData = shardPublicData;
        this.contents = contents;
        this.name = name;
        this.virtualFilePath = virtualFilePath;
        this.positionInOriginFile = positionInOriginFile;
    }

    public ShardPublicData getShardPublicData() {
        return shardPublicData;
    }

    public byte[] getContents() {
        return contents;
    }

    public byte[] getName() {
        return name;
    }

    public byte[] getVirtualFilePath() {
        return virtualFilePath;
    }

    public byte[] getPositionInOriginFile() {
        return positionInOriginFile;
    }

    @Override
    public int compareTo(Shard o) {
        return (int) (shardPublicData.metadata().shardID() - o.shardPublicData.metadata().shardID());
    }
}
