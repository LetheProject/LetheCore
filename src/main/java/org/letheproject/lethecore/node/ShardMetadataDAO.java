package org.letheproject.lethecore.node;

import org.letheproject.lethecore.node.shard.ShardMetadata;

import java.util.List;

/**
 * Provides and interface through which a node stores the metadata of shards it has stored on its network.
 */
public interface ShardMetadataDAO {
    /**
     * Get the metadata of a shard.
     * @param uuid the UUID of the shard.
     * @return the metadata.
     */
    ShardMetadata get(String uuid);

    /**
     * Add the metadata of a shard.
     * @param metadata the metadata.
     */
    void add(ShardMetadata metadata);

    /**
     * Remove the metadata of a shard.
     * @param uuid the UUID of the shard.
     */
    void remove(String uuid);

    /**
     * Update the metadata of a shard.
     * @param metadata the metadata.
     */
    void update(ShardMetadata metadata);

    /**
     * List the shard UUIDs of all stored metadata.
     * @return the list of shard UUIDs.
     */
    List<String> list();

    /**
     * List the shard UUIDs corresponding to a file.
     * @param fileUUID the UUID of the file.
     * @return the list of shard UUIDs.
     */
    List<String> list(String fileUUID);
}
