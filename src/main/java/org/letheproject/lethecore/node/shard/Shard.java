package org.letheproject.lethecore.node.shard;

public class Shard {
    private final ShardPrivateData privateData;
    private final ShardPublicData publicData;
    private final byte[] signature;

    public Shard(ShardPrivateData privateData, ShardPublicData publicData, byte[] signature) {
        this.privateData = privateData;
        this.publicData = publicData;
        this.signature = signature;
    }

    public ShardPrivateData getPrivateData() {
        return privateData;
    }

    public ShardPublicData getPublicData() {
        return publicData;
    }

    public byte[] getSignature() {
        return signature;
    }
}
