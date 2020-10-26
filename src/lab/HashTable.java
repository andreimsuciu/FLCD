package lab;

public class HashTable {
    private final static int SIZE = 2053;
    String[] table;

    public HashTable() {
        table = new String[SIZE];
        for (int i = 0; i < SIZE; i++)
            table[i] = null;
    }
    private int hash(String token){
        int n=token.length();
        int sum=0;
        for(int i=0; i<n; i++){
            sum += (int)(token.charAt(i));
        }
        return (sum % SIZE) +1;
    }

    public int put(String token) {
        int hashedToken = hash(token);
        while (table[hashedToken] != null && !table[hashedToken].equals(token))
            hashedToken +=1;
        table[hashedToken] = token;
        return hashedToken;
    }

    public int get(String token) {
        int hashedToken = hash(token);
        while (table[hashedToken] != null && !table[hashedToken].equals(token))
            hashedToken +=1;
        if (table[hashedToken] == null)
            return -1;
        else
            return hashedToken;
    }
}
