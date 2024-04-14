package org.letheproject.lethecore.cryptography.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
public class AES256 implements Encryptor {
    private SecureRandom random;
    private Cipher cipher;

    public AES256(SecureRandom random) {
        this.random = random;
        try {
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] generateIV() {
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return iv;
    }

    private byte[] extract(byte[] data, int start, int end) {
        int length = end - start;
        byte[] out = new byte[length];
        System.arraycopy(data, start, out, 0, length);
        return out;
    }

    private byte[] concatenate(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    private void validateKeyLength(byte[] key) {
        if (key.length != 32) {
            throw new RuntimeException(String.format("Key length for AES256 must be 32 bytes, not %d", key.length));
        }
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        validateKeyLength(key);
        byte[] iv = generateIV();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(data);
            return concatenate(iv, encrypted);
        }
        catch (InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        validateKeyLength(key);
        byte[] iv = extract(data, 0, 16);
        byte[] encrypted = extract(data, 16, data.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(encrypted);
        }
        catch (InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
