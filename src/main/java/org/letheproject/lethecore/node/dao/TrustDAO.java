package org.letheproject.lethecore.node.dao;


import org.letheproject.lethecore.node.NodeIdentity;

import java.util.List;

public interface TrustDAO {
    /**
     * Get the list of all known identities, regardless of trust.
     * @return the list of identities.
     */
    List<NodeIdentity> getKnownIdentities();

    /**
     * Add an identity as known.
     * @param identity the identity to add.
     */
    void addKnownIdentity(NodeIdentity identity);

    /**
     * Get the list of trusted identities, both direct and indirect.
     * @return the list of identities.
     */
    List<NodeIdentity> getTrustedIdentities();

    /**
     * Set the node identity as trusted.
     * @param identity the identity of the node.
     */
    void setAsTrusted(String identity);

    /**
     * Get the shortest path, in terms of identities, from this node to the target node.
     * The path must be of mutual trust.
     * @param targetIdentity the identity of the target node.
     * @return the shortest path.
     */
    List<String> shortestIdentityPath(String targetIdentity);
}
