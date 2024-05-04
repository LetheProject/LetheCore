package org.letheproject.lethecore.node.dao.mock;

import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.letheproject.lethecore.node.dao.KeyDAO;

public class MockKeyDao implements KeyDAO {
    private PGPKeyPair keyPair;

    public MockKeyDao(PGPKeyPair keyPair) {
        this.keyPair = keyPair;
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
