package org.letheproject.lethecore.node;

public class TrustedNodeIdentity {
    private NodeIdentity identity;
    private int degreesOfSeparation;
    private boolean isMutual;

    public TrustedNodeIdentity(NodeIdentity identity, int degreesOfSeparation, boolean isMutual) {
        this.identity = identity;
        this.degreesOfSeparation = degreesOfSeparation;
        this.isMutual = isMutual;
    }

    public NodeIdentity getIdentity() {
        return identity;
    }

    public int getDegreesOfSeparation() {
        return degreesOfSeparation;
    }

    public boolean isMutual() {
        return isMutual;
    }
}
