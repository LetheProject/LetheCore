package org.letheproject.lethecore.cryptography.encryption;

import org.letheproject.lethecore.ArrayOperations;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA4096 implements Encryptor {
    private final SecureRandom random;
    private final Cipher cipher;

    public RSA4096(SecureRandom random) {
        this.random = random;
        try {
            this.cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] generateIV() {
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return iv;
    }

    private PublicKey toPublicKey(byte[] key) {
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePublic(new X509EncodedKeySpec(key));
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private PrivateKey toPrivateKey(byte[] key) {
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(new PKCS8EncodedKeySpec(key));
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) {
        byte[] iv = generateIV();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, toPublicKey(key));
            return cipher.doFinal(ArrayOperations.concatenate(iv, data));
        }
        catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, toPrivateKey(key));
            byte[] decrypted = cipher.doFinal(data);
            return ArrayOperations.extract(decrypted, 16, decrypted.length);
        }
        catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
