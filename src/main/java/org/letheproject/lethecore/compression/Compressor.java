package org.letheproject.lethecore.compression;

public interface Compressor {
    /**
     * Compress the provided data.
     * The inverse of Compressor.decompress.
     * @param data the data to compress.
     * @return the compressed data.
     */
    byte[] compress(byte[] data);

    /**
     * Decompress the provided compressed data.
     * The inverse of Compressor.compress.
     * @param data the compressed data to decompress.
     * @return the decompressed data.
     */
    byte[] decompress(byte[] data);
}
