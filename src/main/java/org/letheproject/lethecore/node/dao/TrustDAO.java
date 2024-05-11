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
     * Check if the identity is known.
     * @param identity the identity.
     * @return true if it is known, false otherwise.
     */
    boolean isKnown(String identity);
}
