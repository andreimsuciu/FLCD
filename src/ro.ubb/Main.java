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
    }
}
