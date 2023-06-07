package com.krunal.demo.javaPractice;

public class Enums {

    public static void main(String[] args) {
        Signal sig = Signal.valueOf("GREEN");
        checkSignal(sig);
        sig = sig.next();
        checkSignal(sig);
        sig = sig.next();
        checkSignal(sig);
    }

    public static void checkSignal(Signal sig) {
        switch (sig) {
            case GREEN:
                System.out.println("\u001B[32m" + sig.action);
                break;
            case YELLOW:
                System.out.println("\u001B[33m" + sig.action);
                break;
            case RED:
                System.out.println("\u001B[31m" + sig.action);
                break;
        }
    }
}

enum Signal {

    GREEN("Go"),
    YELLOW("Slow down"),
    RED("Stop");

    final String action;

    Signal(String action) {
        this.action = action;
    }

    public Signal next() {
        switch (this) {
            case GREEN:
                return YELLOW;
            case YELLOW:
                return RED;
            case RED:
                return GREEN;
        }
        return null;
    }
}

// Internal implementation of enum

class SignalClass {

    public static final SignalClass GREEN = new SignalClass("Go");
    public static final SignalClass YELLOW = new SignalClass("Slow down");
    public static final SignalClass RED = new SignalClass("Stop");

    final String action;

    SignalClass(String action) {
        this.action = action;
    }

    public SignalClass next() {
        if (GREEN.equals(this)) {
            return YELLOW;
        } else if (YELLOW.equals(this)) {
            return RED;
        } else if (RED.equals(this)) {
            return GREEN;
        }
        return null;
    }
}
