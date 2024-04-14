package org.letheproject.lethecore.cryptography.encoding;

import java.nio.charset.StandardCharsets;

public class Base64 implements Encoder {
    @Override
    public String encode(String string) {
        return java.util.Base64.getEncoder().encodeToString(string.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String decode(String string) {
        return new String(java.util.Base64 .getDecoder().decode(string.getBytes(StandardCharsets.UTF_8)));
    }
}
