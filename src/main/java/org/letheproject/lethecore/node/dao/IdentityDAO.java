package org.letheproject.lethecore.node.dao;

import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.letheproject.lethecore.node.NodeIdentity;

public interface IdentityDAO {
    NodeIdentity getIdentity();
    /**
     * Get the node's PGP key pair.
     * @return the key pair.
     */
    PGPKeyPair getKeyPair();

    /**
     * Update the node's public PGP key.
     * Used for applying signatures.
     * @param publicKey the updated public key.
     */
    void updatePublicKey(PGPPublicKey publicKey);
}
