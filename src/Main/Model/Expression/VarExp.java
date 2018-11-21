package Main.Model.Expression;

import Main.Exceptions.VarNotDefinedException;
import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

public class VarExp implements IExpression{
    private String name;

    public VarExp(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(SymTable<String, Integer> st, Heap heap) throws VarNotDefinedException{
        if (st.containsKey(this.name)) {
            return st.get(this.name);
        } else {
            throw new VarNotDefinedException("Non-existent variable!\n");
        }
    }

    @Override
    public String toString(){
        return name;
    }
}
