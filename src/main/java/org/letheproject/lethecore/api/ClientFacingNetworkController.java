package org.letheproject.lethecore.api;

import org.letheproject.lethecore.node.NodeIdentity;

import java.util.List;

/**
 * The client-facing API controller for the node's network.
 */
public interface ClientFacingNetworkController {
    /**
     * Get the target node's identity.
     * @param target the identifying name or IP address of the target node.
     * @return the identity of the target node.
     */
    Object getIdentity(String target);

    /**
     * Provide the target node with its public key, signed by the sending node.
     * @param targetIdentity the identity of the target node.
     * @return true if this is successful, false otherwise
     */
    Object requestTrust(String targetIdentity);

    /**
     * Get the target node's known identities.
     * @param targetIdentity the identifying name of the target node.
     * @return the list of known identities.
     */
    Object knownIdentities(String targetIdentity);

    /**
     * Ask the target node if it trusts the sender.
     * @param targetIdentity the identifying name of the target node.
     * @return true if the sender is trusted, false otherwise.
     */
    Object isTrusted(String targetIdentity);
}
