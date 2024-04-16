package org.letheproject.lethecore.node.shard;

import java.nio.charset.StandardCharsets;

/**
 * Encapsulates the non-processed data, what cannot be encrypted and must be public to all nodes, of a shard.
 */
public class ShardPublicData {
    private final String ownerUUID;
    private final String uuid;
    private final String dataProcessingSequenceID;
    private final String created;
    private final long position;

    /**
     * Instantiate a ShardPublicData object.
     * @param ownerUUID the owning node's UUID.
     * @param uuid the UUID of the shard.
     * @param dataProcessingSequenceID the UUID of the data processing sequence used on the corresponding private data.
     * @param shardIndex the index of this shard, which is used for reconstructing the original file from its shards.
     * @param created when the shard was created.
     * @param position the byte position of the start of the shard in the file
     */
    public ShardPublicData(String ownerUUID, String uuid, String dataProcessingSequenceID, String created, long position) {
        this.ownerUUID = ownerUUID;
        this.uuid = uuid;
        this.dataProcessingSequenceID = dataProcessingSequenceID;
        this.created = created;
        this.position = position;
    }

    /**
     * Convert the data to a byte array.
     * @return the byte array.
     */
    public byte[] toBytes() {
        return String.format("%s-%s-%s-%d-%s", ownerUUID, uuid, dataProcessingSequenceID, position, created).getBytes(StandardCharsets.UTF_8);
    }

    public String getOwnerUUID() {
        return ownerUUID;
    }

    public String getUuid() {
        return uuid;
    }

    public String getDataProcessingSequenceID() {
        return dataProcessingSequenceID;
    }
    public String getCreated() {
        return created;
    }

    public long getPosition() {
        return position;
    }
}
