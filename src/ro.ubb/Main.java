package ro.ubb;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        SymbolTable symbolTable = new SymbolTable();
        Scanner scanner = new Scanner(symbolTable);
        scanner.scan();

        //PIF
        FileWriter pifWriter = new FileWriter("PIF.out");
        pifWriter.write(String.valueOf(scanner.getGenPIF()));
        pifWriter.close();

        //SYMBOL TABLE
        FileWriter stWriter = new FileWriter("ST.out");
        String[] stArray = scanner.getSymbolTable().getTable().getTable();
        StringBuilder stString= new StringBuilder();
        for(int i=0; i<stArray.length;i++){
            if (stArray[i] != null) {
                stString.append(stArray[i]).append("=").append(i).append(", ");
            }
        }


        stString.append("\nThe data structure for the Symbol Table: \nIt contains a hashtable implemented by me, which contains an array of strings. \n" +
                "The position in the array signals the token index, all other positions are null. For the hashing function I used the one presented at the course.\n" +
                "The put method hashes the token, verifies if the position is empty or the token already exists and puts the token there. If it is not empty, it adds 1 to the index and tries again.\n" +
                "The get method hashes the token, looks if the hashed position has the token. If not, it adds 1 to the position and tries again.");
        stWriter.write(stString.toString());
        stWriter.close();

        if(scanner.lexicalErrors.equals("")){
            System.out.println("No lexical errors!");
        }
        else{
            System.out.println(scanner.lexicalErrors);
        }

        FiniteAutomata fa = new FiniteAutomata();
        fa.readInputFile();
        boolean isValid = fa.verifyFaValidity();
        System.out.println("Checking validity of DFA...");
        if(!isValid){
            System.out.println("The DFA is NOT valid!");
        }
        else{
            System.out.println("The DFA is valid!");
        }

        isValid=fa.verifySequence("00101");
        if(!isValid){
            System.out.println("The sequence is NOT valid!");
        }
        else{
            System.out.println("The sequence is valid!");
        }

        //TRANSITION FUNCTIONS
        System.out.println("===Finite Automata===");
        System.out.print("Q(all states): ");
        System.out.println(fa.getFiniteStatesQ());
        System.out.print("Sigma(input symbols): ");
        System.out.println(fa.getInputSymbolsSigma());
        System.out.print("Initial state: ");
        System.out.println(fa.getInitialSymbol());
        System.out.print("All transitions: ");
        System.out.println(fa.getTransitionFunctionsDelta());
    }

}
