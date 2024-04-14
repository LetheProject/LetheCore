package org.letheproject.lethecore.cryptography.hashing;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

public class Argon2ID extends Hasher {
    private static final int HASH_LENGTH = 64;
    private static final int PARALLELISM_FACTOR = 1;
    private static final int MEMORY_COST = 60_000;
    private static final int ITERATIONS = 10;
    private final Argon2BytesGenerator generator;

    public Argon2ID() {
        this.generator = new Argon2BytesGenerator();
    }

    @Override
    public byte[] hash(byte[] data, byte[] salt) {
        generator.init(new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withIterations(ITERATIONS)
                .withMemoryAsKB(MEMORY_COST)
                .withParallelism(PARALLELISM_FACTOR)
                .withSalt(salt)
                .build()
        );
        return hash(data);
    }

    @Override
    public byte[] hash(byte[] data) {
        byte[] hash = new byte[HASH_LENGTH];
        generator.generateBytes(data, hash, 0, HASH_LENGTH);
        return hash;
    }
}
