package org.letheproject.lethecore.shard;

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
        String fileUUID,
        long shardID,
        String transformationUUID,
        byte[] checksum
) {

}
