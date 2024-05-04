package org.letheproject.lethecore.node;

import java.util.List;

public interface TrustDAO {
    /**
     * Get the list of trusted identities, both direct and indirect.
     * @return the list of identities.
     */
    List<TrustedNodeIdentity> getTrustedIdentities();

    /**
     * Get the shortest path, in terms of identities, from this node to the target node.
     * @param targetIdentity the identity of the target node.
     * @return the shortest path.
     */
    List<String> shortestIdentityPath(String targetIdentity);
}
