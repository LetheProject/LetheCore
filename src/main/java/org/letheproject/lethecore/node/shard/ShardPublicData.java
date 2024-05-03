package org.letheproject.lethecore.node.shard;

/**
 * This is the public information of a shard.
 * It contains the metadata, in addition to information needed by the network.
 * @param metadata
 * @param duplicationID
 * @param created
 * @param signature
 */
public record ShardPublicData(
        ShardMetadata metadata,
        long duplicationID,
        long created,
        byte[] signature
) {

}
