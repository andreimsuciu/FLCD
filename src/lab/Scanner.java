package lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Scanner {
    public static final String numberRegex = "(?:[(])(?:[)])[1-9][0-9]*";
    public static final String identRegex = "[_a-zA-Z][_a-zA-Z0-9]*";
    private final static int SIZE = 2053;
    private int tokensCount=0;
    private String[] tokens;
    private HashMap<String, Integer>  genPIF;
    private SymbolTable symbolTable;



    public Scanner(SymbolTable st) {
        tokens = new String[SIZE];
        this.genPIF = new HashMap<>();
        this.symbolTable =  st;
    }


    public void scan() throws FileNotFoundException {
        readTokenFile();
        readInputFile();
    }

    private void readInputFile() throws FileNotFoundException {
        File inputFile = new File("input.txt");;
        java.util.Scanner fileReader = new java.util.Scanner(inputFile);
        int lineCount=0;
        while (fileReader.hasNextLine()) {
            lineCount++;
            String line = fileReader.nextLine();
            for(String token: line.split(" ")){
                if(Arrays.stream(tokens).anyMatch(token::equals)){
                    genPIF.put(token,0);
                }
                else if( token.matches(numberRegex) || token.matches(identRegex) ){
                    int index=symbolTable.pos(token);
                    genPIF.put(token,index);
                }
                else{
                    System.out.println("Lexical error at line " + lineCount + " for token " + token);
                }
            }

        }
    }

    public void readTokenFile() throws FileNotFoundException {
        File tokenFile = new File("tokens.in");
        java.util.Scanner tokenReader = new java.util.Scanner(tokenFile);
        while (tokenReader.hasNextLine()) {
            String data = tokenReader.nextLine();
            tokens[tokensCount]=data;
            tokensCount++;
        }
    }
}