package ro.ubb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.spi.AbstractResourceBundleProvider;

public class FiniteAutomata {
    private ArrayList<String> finiteStatesQ;
    private ArrayList<Integer> inputSymbolsSigma;
    private String initialState;
    private ArrayList<String> finalStatesF;
    private ArrayList<TransitionFunction> transitionFunctionsDelta;

    public ArrayList<String> getFiniteStatesQ() {
        return finiteStatesQ;
    }

    public ArrayList<Integer> getInputSymbolsSigma() {
        return inputSymbolsSigma;
    }

    public String getInitialSymbol() {
        return initialState;
    }

    public ArrayList<String> getFinalStatesF() {
        return finalStatesF;
    }

    public ArrayList<TransitionFunction> getTransitionFunctionsDelta() {
        return transitionFunctionsDelta;
    }

    public FiniteAutomata() {
        finiteStatesQ = new ArrayList<>();
        inputSymbolsSigma = new ArrayList<>();
        finalStatesF = new ArrayList<>();
        transitionFunctionsDelta = new ArrayList<>();
    }

    public boolean verifySequence(){
        ArrayList<String> states= new ArrayList<>();
        Set<String> finalStates = new HashSet<>();

        for(TransitionFunction tf: transitionFunctionsDelta){
            if(tf.getFromState().equals(initialState)){
                if(!inputSymbolsSigma.contains(tf.getInputSymbol())){
                    return false;
                }
                else{
                    states.add(tf.getToState());
                }
            }
        }
        if(finalStatesF.contains(initialState)){
            if(!states.contains(initialState)){
                return false;
            }
        }

        for(int stateCounter=0; stateCounter<states.size(); stateCounter++) {
            String state = states.get(stateCounter);
            for(TransitionFunction tf: transitionFunctionsDelta){
                if(tf.getFromState().equals(state) && inputSymbolsSigma.contains(tf.getInputSymbol())){
                    if(finalStatesF.contains(tf.getToState())){
                        finalStates.add(tf.getToState());
                    }
                    if(!states.contains(tf.getToState())){
                        states.add(tf.getToState());
                    }
                }
            }
        }
        Set<String> finalStatesFSet = new HashSet<>(finalStatesF);
        return finalStatesFSet.equals(finalStates);
    }

    public void readInputFile() throws FileNotFoundException {
        File faFile = new File("inputFA.in");
        java.util.Scanner faReader = new java.util.Scanner(faFile);

        String data = faReader.nextLine();
        finiteStatesQ.addAll(Arrays.asList(data.split(" ")));

        data = faReader.nextLine();
        for(String token: data.split(" ")){
            inputSymbolsSigma.add(Integer.parseInt(token));
        }

        data = faReader.nextLine();
        initialState=data;

        data = faReader.nextLine();
        finalStatesF.addAll(Arrays.asList(data.split(" ")));

        while (faReader.hasNextLine()) {
            data = faReader.nextLine();
            TransitionFunction tf = new TransitionFunction("", 0, "");

            String[] splitedData=data.split(" ");
            //construct transition function
            tf.setFromState(splitedData[0]);
            tf.setInputSymbol(Integer.parseInt(splitedData[1]));
            tf.setToState(splitedData[2]);

            if(transitionFunctionsDelta.contains(tf)){
                System.out.println("This is not a deterministic finite automata");
            }
            transitionFunctionsDelta.add(tf);
        }
    }
}
