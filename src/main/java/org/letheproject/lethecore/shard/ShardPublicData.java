package org.letheproject.lethecore.shard;

/**
 * This is the public information of a shard.
 * It contains the metadata, in addition to information needed by the network.
 * @param metadata
 * @param ownerIdentity
 * @param duplicationID
 * @param created
 * @param signature
 */
public record ShardPublicData(
        ShardMetadata metadata,
        String ownerIdentity,
        long duplicationID,
        long created,
        byte[] signature
) {

}
