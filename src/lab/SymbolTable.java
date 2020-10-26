package lab;

public class SymbolTable {
    private HashTable table;

    public SymbolTable(){
        table = new HashTable();
    }

    public int pos(String token){
        int position=table.get(token);
        if(position == -1){
            return table.put(token);
        }
        return position;
    }
}
