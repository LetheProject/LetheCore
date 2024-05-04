package org.letheproject.lethecore.node.dao;

import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPPublicKey;

import java.util.List;

/**
 * The interface for a key data access object of a node.
 */
public interface KeyDAO {

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
