package org.letheproject.lethecore.profiling;

import org.letheproject.lethecore.cryptography.keys.PGPKeyService;

import java.security.SecureRandom;
import java.util.List;

public class Profiling {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        ProfileResult<List<?>> generatePGPKeyPairResult = Profile.runMany(() -> new PGPKeyService(random).generate("identity"), 1000);
        System.out.printf(
                "Generated PGP Key Pair: %d ns",
                generatePGPKeyPairResult.getRuntime()
        );
    }
}
