package org.letheproject.lethecore.node;

public class TrustedNodeIdentity {
    private NodeIdentity identity;
    private int degreesOfSeparation;

    public TrustedNodeIdentity(NodeIdentity identity, int degreesOfSeparation) {
        this.identity = identity;
        this.degreesOfSeparation = degreesOfSeparation;
    }

    public NodeIdentity getIdentity() {
        return identity;
    }

    public int getDegreesOfSeparation() {
        return degreesOfSeparation;
    }
}
