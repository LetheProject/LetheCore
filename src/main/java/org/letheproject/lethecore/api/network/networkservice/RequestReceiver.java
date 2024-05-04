package org.letheproject.lethecore.api.network.networkservice;

import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSignature;
import org.letheproject.lethecore.api.network.requests.Request;
import org.letheproject.lethecore.api.network.requests.Response;
import org.letheproject.lethecore.node.Node;
import org.letheproject.lethecore.node.NodeIdentity;

import java.util.List;

public class RequestReceiver {
    private Node node;
    private Response<NodeIdentity> identityResponse;
    private Response<Boolean> trustResponse;
    private Response<List<NodeIdentity>> trustedResponse;

    public void receiveIdentityRequest(String senderIdentity) {
        identityResponse.run(node, senderIdentity, node.getIdentityDAO().getIdentity());
    }

    public void receiveTrustRequest(String senderIdentity, PGPSignature signature) {
        PGPPublicKey.addCertification(node.getKeyDAO().getKeyPair().getPublicKey(), senderIdentity, signature);
        trustResponse.run(node, senderIdentity, true);
    }

    public void receiveTrustedRequest(String senderIdentity) {
        trustedResponse.run(node, senderIdentity, node.getTrustDAO().getTrustedIdentities());
    }
}
