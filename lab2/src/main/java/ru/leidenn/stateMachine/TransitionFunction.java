package ru.leidenn.stateMachine;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TransitionFunction {
    private Map<Integer , Map<Character, Integer>> transitions;

    TransitionFunction(Map<Integer , Map<Character, Integer>> transitions) {
        this.transitions = transitions;
        System.out.println(transitions);
    }
}
