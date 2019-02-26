package ru.leidenn.stateMachine;

public class Lexeme {
    private Type type;
    private String text;

    public Lexeme(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    enum Type {
        PLUS("+"),
        MINUS("-"),
        MULTIPLICATION("*"),
        DIVISION("/"),
        EXPONENT("^"),
        NUMBER,
        OPEN_BR("("),
        CLOSE_BR(")"),
        UNKNOWN,
        EOF;

        public String value;

        Type() {
            this(null);
        }

        Type(String string) {
            value = string;
        }

        static public Type parse(int c) {
            if (Character.isDigit(c)) {
                return NUMBER;
            }
            switch (c) {
                case -1:
                    return EOF;
                case '+':
                    return PLUS;
                case '-':
                    return MINUS;
                case '*':
                    return MULTIPLICATION;
                case '/':
                    return DIVISION;
                case '^':
                    return EXPONENT;
                case '(':
                    return OPEN_BR;
                case ')':
                    return CLOSE_BR;
                default:
                    return UNKNOWN;
            }
        }
    }

    public String toString() {
        return "(" + type + ", '" + text + "')";
    }
}
