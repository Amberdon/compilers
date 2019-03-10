package ru.leidenn.stateMachine;

import java.io.Reader;
import java.util.*;

class StateMachine {
    private List<Integer> finalStates = new ArrayList<>();
    private TransitionFunction transitionFunction;

    StateMachine(Reader fileReader) {
        try (Scanner reader = new Scanner(fileReader)) {
            Arrays.stream(reader.nextLine().split(" "))
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .forEach(finalStates::add);
            Map<Integer, Map<Character, Integer>> transitions = new HashMap<>();
            while (reader.hasNextLine()) {
                String[] sp = reader.nextLine().split(" ");
                Integer fromState = Integer.parseInt(sp[0]);
                Character sym = sp[1].charAt(0);
                Integer toState = Integer.parseInt(sp[2]);
                if (!transitions.containsKey(fromState)) {
                    transitions.put(fromState, new HashMap<>()); // WHAT
                }
                transitions.get(fromState).put(sym, toState);

            }
            transitionFunction = new TransitionFunction(transitions);
        } catch (NumberFormatException e) {
            // TODO throw my exc
        } catch (TransitionFunctionException.NotDeterministicTransitionFunction e) {
            e.printStackTrace();
        }
    }

    boolean check(String str) {
        Integer currentState = 1;
        for (Character c : str.toCharArray()) {
            try {
                currentState = transitionFunction.step(currentState, c);
            } catch (TransitionFunctionException.NoSuchTransition e) {
                return false;
            }
        }
        return finalStates.contains(currentState);
    }
}
