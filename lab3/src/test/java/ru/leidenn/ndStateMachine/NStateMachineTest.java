package ru.leidenn.ndStateMachine;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class NStateMachineTest {
    @Test
    void startAEndB() throws FileNotFoundException {
        NStateMachine stateMachine = new NStateMachine(new FileReader("res/transitionF/startAEndB.txt"));

        assertTrue(stateMachine.check("ab"));
        assertTrue(stateMachine.check("aab"));
        assertTrue(stateMachine.check("abb"));
        assertTrue(stateMachine.check("aabb"));
        assertTrue(stateMachine.check("abab"));

        assertFalse(stateMachine.check("a"));
        assertFalse(stateMachine.check("b"));
        assertFalse(stateMachine.check("ba"));
        assertFalse(stateMachine.check("aa"));
        assertFalse(stateMachine.check("bb"));
        assertFalse(stateMachine.check("baaaa"));
        assertFalse(stateMachine.check("aaaaa"));
    }
}