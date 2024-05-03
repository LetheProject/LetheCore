package org.letheproject.lethecore.duplication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DuplicationFormulaParserTest {
    @Test
    void test() {
        String formula = "ln(x * 8) + 10";
        long nodes = 25;
        DuplicationFormulaParser parser = new DuplicationFormulaParser(formula, nodes);
        long duplication = parser.get();
        assertEquals(15, duplication);

    }
}