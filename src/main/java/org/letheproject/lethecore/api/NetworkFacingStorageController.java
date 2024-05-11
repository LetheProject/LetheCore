package org.letheproject.lethecore.api;

import org.letheproject.lethecore.shard.Shard;
/**
 * The network-facing API controller for file storage on the node's network.
 */
public interface NetworkFacingStorageController {
    Object storeShard(Shard shard);
    Object deleteShard(String shardUUID);
    Object getShard(String shardUUID);
    Object getShardsOfFile(String fileUUID);
    Object updateShard(Shard shard);
    Object shardUUIDs(String ownerIdentity);

    /**
     * The remaining storage quota of the sender node.
     * @return the remaining quota in bytes.
     */
    Object remainingQuota(String senderIdentity);

    /**
     * The remaining hourly storage bandwidth of the sender node.
     * @return the remaining bandwidth in bytes.
     */
    Object remainingHourlyBandwidth(String senderIdentity);

    /**
     * The remaining daily storage bandwidth of the sender node.
     * @return the remaining bandwidth in bytes.
     */
    Object remainingDailyBandwidth(String senderIdentity);
}
