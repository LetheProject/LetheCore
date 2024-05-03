package org.letheproject.lethecore.node;

import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSignature;

import java.util.List;

public record NodeIdentity(String identity, PGPPublicKey publicKey, List<PGPSignature> signature) {
}
