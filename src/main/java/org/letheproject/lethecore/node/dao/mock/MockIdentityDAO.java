package org.letheproject.lethecore.node.dao.mock;

import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.letheproject.lethecore.node.NodeIdentity;
import org.letheproject.lethecore.node.dao.IdentityDAO;

public class MockIdentityDAO implements IdentityDAO {
    private NodeIdentity identity;
    private PGPKeyPair keyPair;

    public MockIdentityDAO(NodeIdentity identity, PGPKeyPair keyPair) {
        this.identity = identity;
        this.keyPair = keyPair;
    }

    @Override
    public NodeIdentity getIdentity() {
        return identity;
    }

    @Override
    public PGPKeyPair getKeyPair() {
        return keyPair;
    }

    @Override
    public void updatePublicKey(PGPPublicKey publicKey) {
        keyPair = new PGPKeyPair(publicKey, keyPair.getPrivateKey());
    }
}
