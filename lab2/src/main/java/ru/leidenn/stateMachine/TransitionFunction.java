package ru.leidenn.stateMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TransitionFunction {
    private Map<Integer, Map<Character, Integer>> transitions;

    TransitionFunction(Map<Integer, Map<Character, Integer>> transitions)
            throws TransitionFunctionException.NotDeterministicTransitionFunction {

        if (!validate(transitions))
            throw new TransitionFunctionException.NotDeterministicTransitionFunction();
        this.transitions = transitions;
    }

    Integer step(Integer state, Character sym) throws TransitionFunctionException.NoSuchTransition {
        if (transitions.containsKey(state) && transitions.get(state).containsKey(sym)) {
            return transitions.get(state).get(sym);
        } else {
            throw new TransitionFunctionException.NoSuchTransition();
        }
    }

    private boolean validate(Map<Integer, Map<Character, Integer>> transitions) {
        Map<Integer, Map<Character, Integer>> seen = new HashMap<>();
        for (Map.Entry<Integer, Map<Character, Integer>> first : transitions.entrySet()) {
            for (Map.Entry<Character, Integer> second : first.getValue().entrySet()) {
                if(seen.containsKey(first.getKey())) {
                    if(seen.get(first.getKey()).containsKey(second.getKey())) {
                        return false;
                    }
                    seen.get(first.getKey()).put(second.getKey(), second.getValue());
                } else {
                    seen.put(first.getKey(), new HashMap<>());
                }
            }
        }
        return true;
    }
}
