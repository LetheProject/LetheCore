package org.letheproject.lethecore.api;

import org.bouncycastle.openpgp.PGPPublicKey;
import org.letheproject.lethecore.node.NodeIdentity;

import java.util.List;

/**
 * The network-facing API controller for the node's network.
 */
public interface NetworkFacingNetworkController {
    /**
     * Get this node's identity.
     * @return the identity of this node.
     */
    Object getIdentity();

    /**
     * Provide the target node with its public key, signed by the sending node.
     * If this is not the target node, then this request is passed along.
     * @param targetIdentity the identifying name of the target node.
     * @param senderIdentity the identifying name of the sender node.
     * @param signedPublicKey the target node's signed public key.
     * @return true if this is successful, false otherwise
     */
    Object requestTrust(String targetIdentity, String senderIdentity, PGPPublicKey signedPublicKey);

    /**
     * Get the target node's known identities.
     * If this is not the target node, then this request is passed along.
     * @param targetIdentity the identifying name of the target node.
     * @return the list of known identities.
     */
    Object knownIdentities(String targetIdentity);

    /**
     * Ask the target node if it trusts the sender.
     * If this node is not the target, then this request is passed along.
     * @param targetIdentity the identifying name of the target node.
     * @param senderIdentity the identifying name of the sender node.
     * @return true if the sender is trusted, false otherwise.
     */
    Object isTrusted(String targetIdentity, String senderIdentity);
}
