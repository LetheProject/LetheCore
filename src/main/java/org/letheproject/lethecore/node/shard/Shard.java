package org.letheproject.lethecore.node.shard;

/**
 * Represents a processed fragment of a file.
 */
public class Shard {
    private final ShardPrivateData privateData;
    private final ShardPublicData publicData;
    private final byte[] signature;

    /**
     * Instantiate a shard.
     * @param privateData the private, or processed, data of the shard.
     * @param publicData the public, non-processed, data of the shard.
     * @param signature the owner's signature.
     */
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
