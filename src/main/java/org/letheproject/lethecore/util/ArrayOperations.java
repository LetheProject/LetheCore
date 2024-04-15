package org.letheproject.lethecore.util;

public class ArrayOperations {
    /**
     * Extract a sub-array from the byte array.
     * @param data the byte array.
     * @param start the starting index (inclusive) of the sub-array.
     * @param end the ending index (exclusive) of the sub-array.
     * @return the sub-array.
     */
    public static byte[] extract(byte[] data, int start, int end) {
        int length = end - start;
        byte[] out = new byte[length];
        System.arraycopy(data, start, out, 0, length);
        return out;
    }

    /**
     * Concatenate two byte arrays.
     * @param a the first byte array.
     * @param b the second byte array.
     * @return the concatenation of a and b.
     */
    public static byte[] concatenate(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
}
