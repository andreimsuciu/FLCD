package ro.ubb;

import java.util.Objects;

public class TransitionFunction {

    private String fromState;
    private int inputSymbol;
    private String toState;

    public TransitionFunction(String fromState, int inputSymbol, String toState) {
        this.fromState = fromState;
        this.inputSymbol = inputSymbol;
        this.toState = toState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransitionFunction that = (TransitionFunction) o;
        return inputSymbol == that.inputSymbol &&
                Objects.equals(fromState, that.fromState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromState, inputSymbol, toState);
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public int getInputSymbol() {
        return inputSymbol;
    }

    public void setInputSymbol(int inputSymbol) {
        this.inputSymbol = inputSymbol;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    @Override
    public String toString() {
        return "Delta(" + fromState  + "," + inputSymbol + " -> " + toState +  ')';
    }
}
