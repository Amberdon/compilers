package ru.leidenn.ndStateMachine;

import java.io.Reader;
import java.util.*;

class NStateMachine {
    private List<Integer> finalStates = new ArrayList<>();
    private Map<Integer, Map<Character, List<Integer>>> transitions = new HashMap<>();

    NStateMachine(Reader fileReader) {
        try (Scanner reader = new Scanner(fileReader)) {
            Arrays.stream(reader.nextLine().split(" "))
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .forEach(finalStates::add);

            while (reader.hasNextLine()) {
                String[] sp = reader.nextLine().split(" ");
                Integer fromState = Integer.parseInt(sp[0]);
                Character sym = sp[1].charAt(0);
                Integer toState = Integer.parseInt(sp[2]);
                if (!transitions.containsKey(fromState)) {
                    transitions.put(fromState, new HashMap<>());
                }
                if (!transitions.get(fromState).containsKey(sym)) {
                    transitions.get(fromState).put(sym, new ArrayList<>());
                }
                transitions.get(fromState).get(sym).add(toState);

            }
        } catch (NumberFormatException e) {
            // TODO throw my exc
        }
    }

    boolean check(String str) {
        return check(str, 1);
    }

    private boolean check(String str, Integer initState) {
        if (str.isEmpty() )
            return finalStates.contains(initState);

        if (transitions.containsKey(initState)) {
            for (Map.Entry<Character, List<Integer>> entry : transitions.get(initState).entrySet()) {
                if (str.charAt(0) == entry.getKey()) {
                    for (Integer state : entry.getValue()) {
                        if (check(str.substring(1), state)) return true;
                    }
                }
            }
        }
        return false;
    }
}
