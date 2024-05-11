package org.letheproject.lethecore.api.service;

import org.bouncycastle.openpgp.PGPPublicKey;
import org.letheproject.lethecore.node.NodeIdentity;

import java.util.List;

public interface NetworkService {
    /**
     * Get the identity of this node.
     * @return the node's identity.
     */
    NodeIdentity getIdentity();

    /**
     * Get the identity of the target node.
     * @param target the identifying name or IP address of the target node.
     * @return the target node's identity.
     */
    NodeIdentity getIdentity(String target);

    /**
     * Send this node their public key, signed by the sender.
     * @param senderIdentity the identifying name of the sender.
     * @param signedPublicKey the signed public key.
     * @return true if successful, false otherwise.
     */
    boolean requestTrust(String senderIdentity, PGPPublicKey signedPublicKey);

    /**
     * Send the target node their public key, signed by the sender.
     * @param targetIdentity the identifying name of the target.
     * @param senderIdentity the identifying name of the sender.
     * @param signedPublicKey the signed public key.
     * @return true if successful, false otherwise.
     */
    boolean requestTrust(String targetIdentity, String senderIdentity, PGPPublicKey signedPublicKey);

    /**
     * Get this node's known identities.
     * @return the list of known identities.
     */
    List<NodeIdentity> knownIdentities();

    /**
     * Get the target node's known identities.
     * @param targetIdentity the identifying name of the target node.
     * @return the list of known identities.
     */
    List<NodeIdentity> knownIdentities(String targetIdentity);

    /**
     * Ask the target node if it trusts the sender.
     * @param senderIdentity the identifying name of the sender node.
     * @return true if the sender is trusted, false otherwise.
     */
    boolean isTrusted(String senderIdentity);

    /**
     * Ask the target node if it trusts the sender.
     * @param targetIdentity the identifying name of the target node.
     * @param senderIdentity the identifying name of the sender node.
     * @return true if the sender is trusted, false otherwise.
     */
    boolean isTrusted(String targetIdentity, String senderIdentity);
}
