package org.letheproject.lethecore.api;

import org.letheproject.lethecore.node.NodeIdentity;
import org.letheproject.lethecore.node.shard.Shard;
import org.letheproject.lethecore.node.shard.ShardMetadata;

import java.util.List;

/**
 * Provides an interface through which the node should make requests to its network.
 */
public interface NetworkService {
    /**
     * Request the identity information of the target node.
     * @param target the information used to communicate with the node; typically a network address or node identity.
     * @return the identity of the node.
     */
    NodeIdentity requestIdentity(String target);

    /**
     * Send your signature of the target node's public key to them.
     * Established that you trust the node and that you will consider this identity to be associated with their key pair.
     * @param targetIdentity the identity of the target node.
     * @return true if the request is successful, false otherwise.
     */
    boolean requestTrust(String targetIdentity);

    /**
     * Request the target node's list of identities they directly trust.
     * Used to determine indirect trust and to expand network access.
     * @param targetIdentity the target node's identities.
     * @return a list of node identities.
     */
    List<NodeIdentity> requestTrusted(String targetIdentity);

    /**
     * Request that the target node store the provided shard.
     * @param targetIdentity the identity of the target node.
     * @param shard the shard to store.
     * @return the result of the request.
     */
    ShardRequestResult requestShardStorage(String targetIdentity, Shard shard);

    /**
     * Request that the target node the provided updated shard, replacing the existing one.
     * Can be used to "quietly" delete shards by providing an updated shard with garbage data.
     * @param targetIdentity the identity of the target node.
     * @param shard the updated shard.
     * @return the result of the request.
     */
    ShardRequestResult requestShardUpdate(String targetIdentity, Shard shard);


    /**
     * Request that the target node delete the shard with the provided UUID.
     * @param targetIdentity the identity of the target node.
     * @param shardUUID the UUID of the shard to delete.
     * @return the result of the request.
     */
    ShardRequestResult requestShardDeletion(String targetIdentity, String shardUUID);

    /**
     * Request the target node provide the shard with the provided UUID.
     * @param targetIdentity the identity of the target node.
     * @param shardUUID the UUID of the shard to retrieve.
     * @return the result of the request.
     */
    ShardRequestResult requestShardRetrieval(String targetIdentity, String shardUUID);

    /**
     * Request the remaining shard quota the target node has determined this node to have.
     * @param targetIdentity the identity of the target node.
     * @return the remaining quota in bytes.
     */
    long requestRemainingShardQuota(String targetIdentity);

    /**
     * Request the remaining shard bandwidth of the target node.
     * @param targetIdentity the identity of the target node.
     * @return the remaining bandwidth in bytes.
     */
    long requestRemainingShardBandwidth(String targetIdentity);

    /**
     * Request that the target node provide all of this node's shard metadata.
     * Typically used for metadata reconstruction.
     * @param targetIdentity the identity of the target node.
     * @return the metadata.
     */
    List<ShardMetadata> requestAllShardMetadata(String targetIdentity);

    /**
     * Request that the target node delete all the shards this node owns.
     * @param targetIdentity the identity of the target node.
     * @return the number of nodes deleted, used for verifying the completion of the request.
     */
    int requestMassShardDeletion(String targetIdentity);
}
