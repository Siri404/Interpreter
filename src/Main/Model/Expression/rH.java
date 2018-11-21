package Main.Model.Expression;

import Main.Exceptions.VarNotDefinedException;
import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

public class rH implements IExpression {
    private String var_name;
    public rH(String var_name){
        this.var_name = var_name;
    }

    @Override
    public int evaluate(SymTable<String, Integer> symTable, Heap heap) throws VarNotDefinedException{
        if(!symTable.containsKey(var_name)) throw new VarNotDefinedException("Invalid variable!\n");
        int address = symTable.get(var_name);
        if(!heap.containsKey(address)) throw new VarNotDefinedException("Invalid addres!\n");
        return heap.get(address);
    }

    @Override
    public String toString() {
        return "rH(" + var_name + ")";
    }
}
