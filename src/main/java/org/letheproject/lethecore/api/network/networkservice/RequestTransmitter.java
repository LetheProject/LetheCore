package org.letheproject.lethecore.api.network.networkservice;

import org.letheproject.lethecore.api.network.requests.Request;
import org.letheproject.lethecore.node.Node;
import org.letheproject.lethecore.node.NodeIdentity;

import java.util.List;

/**
 * Provides a service through which the node should make requests to its network.
 */
public class RequestTransmitter {
    private Node node;
    private Request<NodeIdentity, String> identityRequest;
    private Request<Boolean, String> trustRequest;
    private Request<List<NodeIdentity>, String> trustedRequest;

    public RequestTransmitter(Node node, Request<NodeIdentity, String> identityRequest, Request<Boolean, String> trustRequest, Request<List<NodeIdentity>, String> trustedRequest) {
        this.node = node;
        this.identityRequest = identityRequest;
        this.trustRequest = trustRequest;
        this.trustedRequest = trustedRequest;
    }

    /**
     * Request the identity information of the target node.
     * @param target the information used to communicate with the node; typically a network address or node identity.
     * @return the identity of the node.
     */
    public NodeIdentity requestIdentity(String target) {
        NodeIdentity identity = identityRequest.run(node, target);
        node.getTrustDAO().addKnownIdentity(identity);
        return identity;
    }

    /**
     * Send your signature of the target node's public key to them.
     * Established that you trust the node and that you will consider this identity to be associated with their key pair.
     * @param targetIdentity the identity of the target node.
     * @return true if the request is successful, false otherwise.
     */
    public boolean requestTrust(String targetIdentity) {
        boolean success = trustRequest.run(node, targetIdentity);
        if (success) {
            node.getTrustDAO().setAsTrusted(targetIdentity);
        }
        return success;
    }

    /**
     * Request the target node's list of identities they trust.
     * @param targetIdentity the target node's identities.
     * @return a list of node identities.
     */
    public List<NodeIdentity> requestTrusted(String targetIdentity) {
        List<NodeIdentity> trusted = trustedRequest.run(node, targetIdentity);
        trusted.forEach(node.getTrustDAO()::addKnownIdentity);
        return trusted;
    }
}
