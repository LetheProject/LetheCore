package org.letheproject.lethecore.node.shard;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.letheproject.lethecore.cryptography.encoding.Hexadecimal;
import org.letheproject.lethecore.cryptography.hashing.SHA256;
import org.letheproject.lethecore.cryptography.keys.PGPKeyService;

import java.nio.charset.StandardCharsets;

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

    public Shard build(PGPKeyService keyService, PGPKeyPair keyPair) {
        Hexadecimal hex = new Hexadecimal();
        SHA256 sha256 = new SHA256();
        String concatenated = String.format(
                "{%s, %s, %s, %s, %s, %s, %s, %s, %s, %s}",
                hex.encode(new String(name)),
                hex.encode(new String(contents)),
                hex.encode(new String(virtualFilePath)),
                hex.encode(new String(positionInOriginFile)),
                hex.encode(ownerIdentity),
                hex.encode(fileUUID),
                hex.encode(String.valueOf(shardID)),
                hex.encode(String.valueOf(duplicationID)),
                hex.encode(String.valueOf(created)),
                hex.encode(transformationUUID)
        );
        byte[] signature;
        try {
            signature = keyService.sign(concatenated.getBytes(StandardCharsets.UTF_8), keyPair).getSignature();
        }
        catch (PGPException e) {
            throw new RuntimeException(e);
        }
        concatenated += "\n" + hex.encode(new String(signature));
        byte[] checksum = sha256.hash(concatenated.getBytes(StandardCharsets.UTF_8));
        ShardMetadata metadata = new ShardMetadata(fileUUID, shardID, transformationUUID, checksum);
        ShardPublicData publicData = new ShardPublicData(metadata, ownerIdentity, duplicationID, created, signature);
        return new Shard(publicData, contents, name, virtualFilePath, positionInOriginFile);
    }
}
