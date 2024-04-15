package org.letheproject.lethecore.cryptography.signing;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface Signer {
    /**
     * Sign some data with the provided private key.
     * @param data the data to sign.
     * @param privateKey the private key.
     * @return the signature.
     */
    byte[] sign(byte[] data, PrivateKey privateKey);

    /**
     * Verify the signature of some data using the provided public key.
     * @param data the signed data.
     * @param signature the signature.
     * @param publicKey the public key.
     * @return true if the signature is valid, false otherwise.
     */
    boolean verify(byte[] data, byte[] signature, PublicKey publicKey);
}