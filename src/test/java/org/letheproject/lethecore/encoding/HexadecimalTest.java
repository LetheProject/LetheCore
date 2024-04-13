package org.letheproject.lethecore.encoding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexadecimalTest {

    @Test
    void encode() {
        Hexadecimal hex = new Hexadecimal();
        String string = "Hello World!";
        String hexadecimal = hex.encode(string);
        assertEquals("48656C6C6F20576F726C6421", hexadecimal);
    }

    @Test
    void decode() {
        Hexadecimal hex = new Hexadecimal();
        String hexadecimal = "48656C6C6F20576F726C6421";
        String string = hex.decode(hexadecimal);
        assertEquals("Hello World!", string);
    }
}