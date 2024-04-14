package org.letheproject.lethecore.cryptography.encoding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Base64Test {

    @Test
    void encodeAndDecode() {
        Base64 base64 = new Base64();
        String data = "Hello World!";
        String encoded = base64.encode(data);
        String decoded = base64.decode(encoded);
        assertEquals(data, decoded);
    }
}