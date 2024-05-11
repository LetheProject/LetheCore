package org.letheproject.lethecore.node;

import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSignature;

import java.util.List;

public class NodeIdentity {
    private String identity;
    private PGPPublicKey publicKey;
    private List<PGPSignature> signatures;
    private boolean isUserless;

    public NodeIdentity(String identity, PGPPublicKey publicKey, List<PGPSignature> signatures, boolean isUserless) {
        this.identity = identity;
        this.publicKey = publicKey;
        this.signatures = signatures;
        this.isUserless = isUserless;
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

    public boolean isUserless() {
        return isUserless;
    }
}
