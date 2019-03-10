package ru.leidenn.stateMachine;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class StateMachineTest {
    @Test
    void startWithA() throws FileNotFoundException {
        StateMachine stateMachine = new StateMachine(new FileReader("res/transitionF/startWithA.txt"));
        assertTrue(stateMachine.check("a"));
        assertTrue(stateMachine.check("ab"));
        assertTrue(stateMachine.check("aa"));
        assertTrue(stateMachine.check("abaaba"));

        assertFalse(stateMachine.check("b"));
        assertFalse(stateMachine.check("ba"));
        assertFalse(stateMachine.check("baaaa"));
    }

    @Test
    void startWithAEndWithB() throws FileNotFoundException {
        StateMachine stateMachine = new StateMachine(new FileReader("res/transitionF/startWithAEndWithB.txt"));
        assertTrue(stateMachine.check("ab"));
        assertTrue(stateMachine.check("aab"));
        assertTrue(stateMachine.check("abb"));
        assertTrue(stateMachine.check("aabbb"));

        assertFalse(stateMachine.check("a"));
        assertFalse(stateMachine.check("b"));
        assertFalse(stateMachine.check("ba"));
        assertFalse(stateMachine.check("aa"));
        assertFalse(stateMachine.check("bb"));
        assertFalse(stateMachine.check("baba"));
    }
}