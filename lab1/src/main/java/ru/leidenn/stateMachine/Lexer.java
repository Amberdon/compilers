package ru.leidenn.stateMachine;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

public class Lexer implements AutoCloseable{
    private Reader reader;
    private int current;
    private int position;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        position = -1;
        current = reader.read();
    }

    public Lexeme readNext() throws IOException, ParseException {
        while (Character.isWhitespace(current))
            readChar();
        if(Lexeme.Type.parse(current) == Lexeme.Type.NUMBER) {
            StringBuilder buf = new StringBuilder();
            do {
                buf.append((char) current);
                readChar();
            } while (Lexeme.Type.parse(current) == Lexeme.Type.NUMBER);
            return new Lexeme(Lexeme.Type.NUMBER, buf.toString());
        }
        Lexeme.Type type = Lexeme.Type.parse(current);
        int c = current;
        readChar();
        if(type == Lexeme.Type.UNKNOWN) {
            throw new ParseException(String.format("Unknown character %c at %d", c, position), position);
        }
        return new Lexeme(type, type.value);
    }

    private void readChar() throws IOException {
        position++;
        current = reader.read();
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
