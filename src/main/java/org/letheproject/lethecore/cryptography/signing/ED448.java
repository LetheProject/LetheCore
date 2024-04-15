package org.letheproject.lethecore.cryptography.signing;

import java.security.*;
import java.security.spec.*;

public class ED448 implements Signer {
    private PrivateKey toPrivateKey(byte[] bytes) {
        try {
            KeyFactory factory = KeyFactory.getInstance("EdDSA");
            return factory.generatePrivate(new PKCS8EncodedKeySpec(bytes));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private PublicKey toPublicKey(byte[] bytes) {
        try {
            KeyFactory factory = KeyFactory.getInstance("EdDSA");
            return factory.generatePublic(new X509EncodedKeySpec(bytes));
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] sign(byte[] data, byte[] privateKeyBytes) {
        return sign(data, toPrivateKey(privateKeyBytes));
    }

    @Override
    public byte[] sign(byte[] data, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("Ed448");
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        }
        catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verify(byte[] data, byte[] signatureBytes, byte[] publicKeyBytes) {
        return verify(data, signatureBytes, toPublicKey(publicKeyBytes));
    }

    @Override
    public boolean verify(byte[] data, byte[] signatureBytes, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance("Ed448");
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(signatureBytes);
        }
        catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
