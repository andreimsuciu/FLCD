package ro.ubb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Scanner {
    public static final String numberRegex = "(([:][)])|([:][(])?)[1-9][0-9]*";
    public static final String identRegex = "[_a-zA-Z][_a-zA-Z0-9]*";
    private final static int SIZE = 32;
    private int tokensCount=0;
    private String[] tokens;
    private List<Pair>  genPIF;
    private SymbolTable symbolTable;
    public String lexicalErrors = "";

    public String[] getTokens() {
        return tokens;
    }

    public List<Pair> getGenPIF() {
        return genPIF;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public Scanner(SymbolTable st) {
        tokens = new String[SIZE];
        this.genPIF = new ArrayList<Pair>();
        this.symbolTable =  st;
    }


    public void scan() throws FileNotFoundException {
        readTokenFile();
        readInputFile();
    }

    private void readInputFile() throws FileNotFoundException {
        File inputFile = new File("input.in");;
        java.util.Scanner fileReader = new java.util.Scanner(inputFile);
        int lineCount=0;
        StringBuilder lexicalErrorsSb= new StringBuilder();
        while (fileReader.hasNextLine()) {
            lineCount++;
            String line = fileReader.nextLine();
            for(String token: line.split(" ")){
                if(Arrays.stream(tokens).anyMatch(token::equals)){
                    genPIF.add(new Pair(token,0));
                }
                else if( token.matches(numberRegex) ){
                    int index=symbolTable.pos(token);
                    genPIF.add(new Pair("0",index));
                }
                else if( token.matches(identRegex) ){
                    int index=symbolTable.pos(token);
                    genPIF.add(new Pair("1",index));
                }
                else{
                    lexicalErrorsSb.append("Lexical error at line ").append(lineCount).append(" for token ").append(token).append("\n");
                }
            }
        }
        lexicalErrors= String.valueOf(lexicalErrorsSb);
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