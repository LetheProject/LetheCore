package org.letheproject.lethecore.node;

import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSignature;

import java.util.List;

public class NodeIdentity {
    private String identity;
    private PGPPublicKey publicKey;
    private List<PGPSignature> signatures;

    public NodeIdentity(String identity, PGPPublicKey publicKey, List<PGPSignature> signatures) {
        this.identity = identity;
        this.publicKey = publicKey;
        this.signatures = signatures;
    }

    public String getIdentity() {
        return identity;
    }

    public PGPPublicKey getPublicKey() {
        return publicKey;
    }

    public List<PGPSignature> getSignatures() {
        return signatures;
    }
}
