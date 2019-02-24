package ru.leidenn;

import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private void assertCalculation(long expected, String expression) throws Exception {
        try (Lexer lexer = new Lexer(new StringReader(expression))) {
            assertEquals(expected, new Parser(lexer).calculate());
        }
    }

    @Test
    void simple() throws Exception {
        assertCalculation(2123, "   2123  ");
    }

    @Test
    void simpleOp() throws Exception {
        assertCalculation(21 + 33, "21 +33");
        assertCalculation(21 - 33, "21 -33");
        assertCalculation(21 * 33, "21*33");
        assertCalculation(21 / 33, "21 /33");
        assertCalculation((long)Math.pow(21, 33), "21 ^33");
        assertCalculation( -33, "-33");
    }

    @Test
    void simpleWithBr() throws Exception {
        assertCalculation(23*(1 - 6), "23 *(1 - 6)");
        assertCalculation(2*(-1), "2*(-1)");
        assertCalculation(54 - (23), "54 - (23)");
    }

    @Test
    void errorTest() throws Exception {
        assertThrows(ParseException.class , () -> assertCalculation(0, "23 - (2"));
        assertThrows(ParseException.class, () -> assertCalculation(0, "2 + s"));
        assertThrows(ParseException.class, () -> assertCalculation(0, "2 + )"));
        assertThrows(ParseException.class, () -> assertCalculation(0, "2 2"));
    }


}