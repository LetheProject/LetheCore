package org.letheproject.lethecore.node.dao;

import org.letheproject.lethecore.shard.Shard;

import java.util.List;
import java.util.Optional;

/**
 * The interface for a shard data access object of a node.
 */
public interface ShardDAO {
    /**
     * Determine if a shard with the provided UUID is being stored.
     * @param uuid the UUID of the shard.
     * @return true if the shard is being stored, false otherwise.
     */
    boolean containsShard(String uuid);

    /**
     * Get the shard corresponding to the provided UUID.
     * @param uuid the UUID.
     * @return an optional containing the shard if it is store or an empty one if there is none being stored.
     */
    Optional<Shard> getShard(String uuid);

    /**
     * Get the UUIDs of all shards that have the owner with the provided UUID.
     * @param uuid the UUID of the owner.
     * @return the list of shard UUIDs.
     */
    List<String> getShardsOfOwner(String uuid);

    /**
     * Remove the shard with the provided UUID.
     * @param uuid the UUID.
     * @return true if the shard was remove, false there was no shard to remove.
     */
    boolean removeShard(String uuid);

    /**
     * Get the UUIDs of all shards being stored.
     * @return the list of UUIDs.
     */
    List<String> getAllShardUUIDs();

    /**
     * Get the UUIDs of all owners that have shards being stored.
     * @return the list of UUIDs.
     */
    List<String> getAllShardOwnerUUIDs();

    /**
     * Get the number of bytes used by the provided owner.
     * @param uuid the UUID of the owner.
     * @return the space in bytes.
     */
    long spaceUsedByOwner(String uuid);

    /**
     * Get the bandwidth, or space used since the provided date, by the provided owner.
     * @param identity the identity of the owner.
     * @param period the length of time in milliseconds to use in bandwidth calculation.
     * @return the number of bytes stored by the owner since now minus the provided period.
     */
    long bandwidthUsedByOwner(String identity, long period);

    /**
     * Get the total number of bytes used on disk to store shards.
     * @return the number of bytes used.
     */
    long usedSpace();

    /**
     * Get the total number of bytes still free on disk to store shards.
     * @return the number of bytes free.
     */
    long freeSpace();
}
