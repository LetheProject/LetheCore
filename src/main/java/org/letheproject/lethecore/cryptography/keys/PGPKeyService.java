package org.letheproject.lethecore.cryptography.keys;

import org.bouncycastle.bcpg.PublicKeyAlgorithmTags;
import org.bouncycastle.crypto.generators.Ed448KeyPairGenerator;
import org.bouncycastle.crypto.params.Ed448KeyGenerationParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.*;
import org.bouncycastle.openpgp.operator.bc.BcPGPKeyPair;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;

import java.security.Provider;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Iterator;

public class PGPKeyService {
    private static final int KEY_ALGORITHM = PublicKeyAlgorithmTags.Ed448;
    private static final Provider PROVIDER = new BouncyCastleProvider();
    private final SecureRandom random;

    public PGPKeyService(SecureRandom random) {
        this.random = random;
    }

    public PGPKeyPair generate(String identity) {
        try {
            Ed448KeyPairGenerator generator = new Ed448KeyPairGenerator();
            generator.init(new Ed448KeyGenerationParameters(random));
            PGPKeyPair keyPair = new BcPGPKeyPair(KEY_ALGORITHM, generator.generateKeyPair(), new Date());
            PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator(
                    new JcaPGPContentSignerBuilder(keyPair.getPublicKey().getAlgorithm(), PGPUtil.SHA256)
            );
            signatureGenerator.init(PGPSignature.DEFAULT_CERTIFICATION, keyPair.getPrivateKey());
            PGPSignature signature = signatureGenerator.generateCertification(identity, keyPair.getPublicKey());
            PGPPublicKey publicKey = PGPPublicKey.addCertification(keyPair.getPublicKey(), identity, signature);
            return new PGPKeyPair(publicKey, keyPair.getPrivateKey());
        }
        catch (PGPException e) {
            throw new RuntimeException(e);
        }
    }

    public PGPPublicKey sign(PGPPublicKey keyToSign, PGPKeyPair signingKeyPair) {
        PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator(
                new JcaPGPContentSignerBuilder(signingKeyPair.getPublicKey().getAlgorithm(), PGPUtil.SHA256)
        );
        PGPSignature signature;
        String id = signingKeyPair.getPublicKey().getUserIDs().next();
        try {
            signatureGenerator.init(PGPSignature.DEFAULT_CERTIFICATION, signingKeyPair.getPrivateKey());
            signature = signatureGenerator.generateCertification(id, keyToSign);
        }
        catch (PGPException e) {
            throw new RuntimeException(e);
        }
        return PGPPublicKey.addCertification(keyToSign, id, signature);
    }

    public PGPSignature sign(byte[] dataToSign, PGPKeyPair signingKeyPair) {
        PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator(
                new JcaPGPContentSignerBuilder(signingKeyPair.getPublicKey().getAlgorithm(), PGPUtil.SHA256)
        );
        try {
            signatureGenerator.init(PGPSignature.BINARY_DOCUMENT, signingKeyPair.getPrivateKey());
            signatureGenerator.update(dataToSign);
            return signatureGenerator.generate();
        }
        catch (PGPException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verify(PGPPublicKey keyToVerify, PGPPublicKey verifyingKey) {
        Iterator<PGPSignature> signatures = keyToVerify.getSignatures();
        String id = verifyingKey.getUserIDs().next();
        while (signatures.hasNext()) {
            PGPSignature signature = signatures.next();
            if (signature.getSignatureType() != PGPSignature.DEFAULT_CERTIFICATION) {
                continue;
            }
            try {
                signature.init(new JcaPGPContentVerifierBuilderProvider().setProvider(PROVIDER), verifyingKey);
            }
            catch (PGPException e) {
                throw new RuntimeException(e);
            }
            try {
                if (signature.verifyCertification(id, keyToVerify)) {
                    return true;
                }
            }
            catch (PGPException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean verify(byte[] data, PGPSignature signature, PGPPublicKey verifyingKey) {
        try {
            signature.init(new JcaPGPContentVerifierBuilderProvider().setProvider(PROVIDER), verifyingKey);
            signature.update(data);
            return signature.verify();
        }
        catch (PGPException e) {
            throw new RuntimeException(e);
        }
    }
}
