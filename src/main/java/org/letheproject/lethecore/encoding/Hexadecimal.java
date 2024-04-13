package org.letheproject.lethecore.encoding;

import java.nio.charset.StandardCharsets;

public class Hexadecimal implements Encoder {
    @Override
    public String encode(String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }

    @Override
    public String decode(String string) {
        char[] characters = string.toCharArray();
        byte[] out = new byte[characters.length / 2];
        for (int i = 0; i < characters.length; i += 2) {
            int a = Character.digit(characters[i], 16);
            int b = Character.digit(characters[i + 1], 16);
            out[i / 2] = (byte) ((a << 4) + b);
        }
        return new String(out);
    }
}
