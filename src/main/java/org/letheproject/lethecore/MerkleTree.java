package org.letheproject.lethecore;

import org.letheproject.lethecore.cryptography.hashing.Hasher;
import org.letheproject.lethecore.util.ArrayOperations;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree<T> {
    private Hasher hasher;
    private List<T> elements;

    public MerkleTree(Hasher hasher) {
        this.hasher = hasher;
        this.elements = new ArrayList<>();
    }

    public void add(T element) {
        elements.add(element);
    }

    public T remove(int index) {
        return elements.remove(index);
    }

    public boolean remove(T element) {
        return elements.remove(element);
    }

    public T get(int index) {
        return elements.get(index);
    }

    public byte[] getHash() {
        List<byte[]> hashes = new ArrayList<>();
        for (T element : elements) {
            hashes.add(hasher.hash(element.toString().getBytes(StandardCharsets.UTF_8)));
        }
        while (hashes.size() > 1) {
            List<byte[]> nextHashes = new ArrayList<>();
            for (int i = 0; i < hashes.size(); i++) {
                byte[] a = hashes.get(i);
                if (i == hashes.size() - 1) {
                    a = ArrayOperations.concatenate(a, hashes.get(i + 1));
                }
                nextHashes.add(a);
            }
            hashes = nextHashes;
        }
        return hashes.get(0);
    }
}
