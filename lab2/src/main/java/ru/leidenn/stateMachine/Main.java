package ru.leidenn.stateMachine;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] argv) throws FileNotFoundException {
        StateMachine stateMachine = new StateMachine(new FileReader(argv[0]));
        // CHECK ARGV
//        try (Reader reader = new FileReader(argv[0])) {
//            StateMachine stateMachine = new StateMachine(reader);
//        }
    }
}
