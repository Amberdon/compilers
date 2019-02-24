package ru.leidenn;

import java.io.IOException;
import java.text.ParseException;

public class Parser {
    private Lexer lexer;
    private int currentIndex = 0;
    private Lexeme current;

    public Parser(Lexer lexer) throws IOException, ParseException {
        this.lexer = lexer;
        current = lexer.readNext();
    }

    private void readNext() throws IOException, ParseException {
        current = lexer.readNext();
    }

    public long calculate() throws Exception {
        long res = parseExpr();
        if(current.getType() != Lexeme.Type.EOF) {
            throw new ParseException("Expected EOF", 0);
        }
        return res;
    }

    private long parseExpr() throws Exception { // expr = term +- term +- ...
        long res = parseTerm();
        while (current.getType() == Lexeme.Type.PLUS || current.getType() == Lexeme.Type.MINUS) {
            Lexeme.Type type = current.getType();
            readNext();
            switch (type) {
                case PLUS:
                    res += parseTerm();
                    break;
                case MINUS:
                    res -= parseTerm();
                    break;
            }
        }
        return res;
    }

    private long parseTerm() throws Exception { // term = factor */ factor ...
        long res = parseFactor();
        while (current.getType() == Lexeme.Type.MULTIPLICATION || current.getType() == Lexeme.Type.DIVISION) {
            Lexeme.Type type = current.getType();
            readNext();
            switch (type) {
                case DIVISION:
                    res /= parseFactor();
                    break;
                case MULTIPLICATION:
                    res *= parseFactor();
                    break;
            }
        }
        return res;
    }

    private long parseFactor() throws Exception { // factor = power ^ factor | power
        long res;
        res = parsePower();
        if (current.getType() == Lexeme.Type.EXPONENT) {
            readNext();
            res = (long) Math.pow(res, parseFactor());
        }
        return res;
    }

    private long parsePower() throws Exception { // power = atom | -atom
        long res;
        if (current.getType() == Lexeme.Type.MINUS) {
            readNext();
            res = -parseAtom();
        } else {
            res = parseAtom();
        }
        return res;
    }

    private long parseAtom() throws Exception { // atom = number | (expr)
        long res;
        if (current.getType() == Lexeme.Type.NUMBER) {
            res = Long.parseLong(current.getText());
            readNext();
            return res;
        }
        if (current.getType() == Lexeme.Type.OPEN_BR) {
            readNext();
            res = parseExpr();
            if (current.getType() != Lexeme.Type.CLOSE_BR) {
                throw new ParseException("Expected close bracket", 0);
            }
            readNext();
            return res;
        }
        throw new ParseException("Lexeme type not NUMBER or OPEN_BR", 0);
    }
}
