package org.letheproject.lethecore.shard;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.letheproject.lethecore.cryptography.encoding.Encoder;
import org.letheproject.lethecore.cryptography.hashing.Hasher;
import org.letheproject.lethecore.cryptography.keys.PGPKeyService;

import java.nio.charset.StandardCharsets;

/**
 * A factory for the Shard class.
 */
public class ShardFactory {
    private byte[] contents;
    private byte[] name;
    private byte[] virtualFilePath;
    private byte[] positionInOriginFile;
    private String ownerIdentity;
    private long duplicationID;
    private long created;
    private String fileUUID;
    private long shardID;
    private String transformationUUID;


    public ShardFactory setContents(byte[] contents) {
        this.contents = contents;
        return this;
    }

    public ShardFactory setName(byte[] name) {
        this.name = name;
        return this;
    }

    public ShardFactory setVirtualFilePath(byte[] virtualFilePath) {
        this.virtualFilePath = virtualFilePath;
        return this;
    }

    public ShardFactory setPositionInOriginFile(byte[] positionInOriginFile) {
        this.positionInOriginFile = positionInOriginFile;
        return this;
    }

    public ShardFactory setOwnerIdentity(String ownerIdentity) {
        this.ownerIdentity = ownerIdentity;
        return this;
    }

    public ShardFactory setDuplicationID(long duplicationID) {
        this.duplicationID = duplicationID;
        return this;
    }

    public ShardFactory setCreated(long created) {
        this.created = created;
        return this;
    }

    public ShardFactory setFileUUID(String fileUUID) {
        this.fileUUID = fileUUID;
        return this;
    }

    public ShardFactory setShardID(long shardID) {
        this.shardID = shardID;
        return this;
    }

    public ShardFactory setTransformationUUID(String transformationUUID) {
        this.transformationUUID = transformationUUID;
        return this;
    }



    /**
     * Build a shard from the set arguments.
     * @param encoder the encoder to use.
     * @param hasher the hasher to use.
     * @param keyService the key service to use for signing the shard.
     * @param keyPair the key pair to use in signing the shard.
     * @return the shard.
     */
    public Shard build(Encoder encoder, Hasher hasher, PGPKeyService keyService, PGPKeyPair keyPair) {
        String concatenated = String.format(
                "{%s, %s, %s, %s, %s, %s, %s, %s, %s, %s}",
                encoder.encode(new String(name)),
                encoder.encode(new String(contents)),
                encoder.encode(new String(virtualFilePath)),
                encoder.encode(new String(positionInOriginFile)),
                encoder.encode(ownerIdentity),
                encoder.encode(fileUUID),
                encoder.encode(String.valueOf(shardID)),
                encoder.encode(String.valueOf(duplicationID)),
                encoder.encode(String.valueOf(created)),
                encoder.encode(transformationUUID)
        );
        byte[] signature;
        try {
            signature = keyService.sign(concatenated.getBytes(StandardCharsets.UTF_8), keyPair).getSignature();
        }
        catch (PGPException e) {
            throw new RuntimeException(e);
        }
        concatenated += "\n" + encoder.encode(new String(signature));
        byte[] checksum = hasher.hash(concatenated.getBytes(StandardCharsets.UTF_8));
        ShardMetadata metadata = new ShardMetadata(fileUUID, shardID, transformationUUID, checksum);
        ShardPublicData publicData = new ShardPublicData(metadata, ownerIdentity, duplicationID, created, signature);
        return new Shard(publicData, contents, name, virtualFilePath, positionInOriginFile);
    }
}
