package ru.leidenn;

import java.io.StringReader;

public class Main {
    static public void main(String[] argv) throws Exception {
        Lexer lexer = new Lexer(new StringReader("2 3 + - 546  */ ^ 12"));
        Lexeme lexeme = lexer.readNext();
        while(lexeme.getType() != Lexeme.Type.EOF ) {
            System.out.println(lexeme);
            lexeme = lexer.readNext();
        }
    }
}
