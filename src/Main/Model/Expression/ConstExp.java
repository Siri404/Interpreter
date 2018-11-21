package Main.Model.Expression;

import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

public class ConstExp implements IExpression {
    private int value;

    public ConstExp(int val){
    value = val;
    }

    @Override
    public int evaluate(SymTable<String, Integer> st, Heap heap){
        return this.value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
