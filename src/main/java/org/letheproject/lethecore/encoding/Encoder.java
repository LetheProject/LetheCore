package org.letheproject.lethecore.encoding;

public interface Encoder {
    /**
     * Convert the provided string into an encoding.
     * This is the inverse of Encoder.decode.
     * @param string the string to encode.
     * @return the encoded string.
     */
    String encode(String string);

    /**
     * Convert the provided encoded string back to its pre-encoded form.
     * This is the inverse of Encoder.encode.
     * @param string the string to decode.
     * @return the decoded string.
     */
    String decode(String string);
}
