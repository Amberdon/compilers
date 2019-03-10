package ru.leidenn.stateMachine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {
    public static void main(String[] argv) throws FileNotFoundException {
        StateMachine stateMachine = new StateMachine(new FileReader(argv[0]));
        try {
            String str = new String(Files.readAllBytes(Paths.get(argv[1])));
            System.out.println(stateMachine.check(str));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CHECK ARGV
//        try (Reader reader = new FileReader(argv[0])) {
//            StateMachine stateMachine = new StateMachine(reader);
//        }
    }
}
