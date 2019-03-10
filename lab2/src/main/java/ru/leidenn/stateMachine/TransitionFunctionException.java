package ru.leidenn.stateMachine;

class TransitionFunctionException {
    public static class NotDeterministicTransitionFunction extends Throwable {
    }

    public static class NoSuchTransition extends Throwable {
    }
}