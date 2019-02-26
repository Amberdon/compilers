package ru.leidenn.stateMachine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static ru.leidenn.stateMachine.Lexeme.Type.*;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

class LexerTest {

    @Test
    void simpleTypeString() throws IOException, ParseException {
        Lexer lexer = new Lexer(new StringReader("1+- */^(   )s"));
        assertEquals(lexer.readNext().getType(), NUMBER);
        assertEquals(lexer.readNext().getType(), PLUS);
        assertEquals(lexer.readNext().getType(), MINUS);
        assertEquals(lexer.readNext().getType(), MULTIPLICATION);
        assertEquals(lexer.readNext().getType(), DIVISION);
        assertEquals(lexer.readNext().getType(), EXPONENT);
        assertEquals(lexer.readNext().getType(), OPEN_BR);
        assertEquals(lexer.readNext().getType(), CLOSE_BR);
        assertThrows(ParseException.class, lexer::readNext);
        assertEquals(lexer.readNext().getType(), EOF);
    }

    @Test
    void simpleTextString() throws IOException, ParseException {
        Lexer lexer = new Lexer(new StringReader("12+-*   /^()s"));
        assertEquals(lexer.readNext().getText(), "12");
        assertEquals(lexer.readNext().getText(), PLUS.value);
        assertEquals(lexer.readNext().getText(), MINUS.value);
        assertEquals(lexer.readNext().getText(), MULTIPLICATION.value);
        assertEquals(lexer.readNext().getText(), DIVISION.value);
        assertEquals(lexer.readNext().getText(), EXPONENT.value);
        assertEquals(lexer.readNext().getText(), OPEN_BR.value);
        assertEquals(lexer.readNext().getText(), CLOSE_BR.value);
        assertThrows(ParseException.class, lexer::readNext);
        assertEquals(lexer.readNext().getText(), EOF.value);
    }

    @Test
    void lexemeToString() throws IOException, ParseException {
        Lexer lexer = new Lexer(new StringReader("12+-*   /^()s"));
        assertEquals(lexer.readNext().toString(), "(NUMBER, '12')");
        assertEquals(lexer.readNext().toString(), "(PLUS, '+')");
        assertEquals(lexer.readNext().toString(), "(MINUS, '-')");
        assertEquals(lexer.readNext().toString(), "(MULTIPLICATION, '*')");
        assertEquals(lexer.readNext().toString(), "(DIVISION, '/')");
        assertEquals(lexer.readNext().toString(), "(EXPONENT, '^')");
        assertEquals(lexer.readNext().toString(), "(OPEN_BR, '(')");
        assertEquals(lexer.readNext().toString(), "(CLOSE_BR, ')')");
        assertThrows(ParseException.class, lexer::readNext);
        assertEquals(lexer.readNext().toString(), "(EOF, 'null')");
    }

}