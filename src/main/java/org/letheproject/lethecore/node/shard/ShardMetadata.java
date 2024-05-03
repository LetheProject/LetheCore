package org.letheproject.lethecore.node.shard;

/**
 * Represents the metadata of a shard.
 * This is the information to be kept by the owner node.
 * @param ownerIdentity
 * @param fileUUID
 * @param shardID
 * @param transformationUUID
 * @param checksum
 */
public record ShardMetadata(
        String ownerIdentity,
        String fileUUID,
        long shardID,
        String transformationUUID,
        byte[] checksum
) {

}
