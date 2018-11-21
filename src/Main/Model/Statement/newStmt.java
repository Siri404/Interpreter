package Main.Model.Statement;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Expression.IExpression;
import Main.Model.ProgramState;
import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

public class newStmt implements IStmt {
    private String var_name;
    private IExpression exp;

    public newStmt(String var_name, IExpression exp){
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws DivisionByZeroException, VarNotDefinedException {
        SymTable<String, Integer> symTable = programState.getSymTable();
        Heap heap = programState.getHeap();
        int value = exp.evaluate(symTable, heap);
        int address = heap.add(value);
        if(symTable.containsKey(var_name)){
            symTable.replace(var_name, address);
        }
        else{
            symTable.put(var_name, address);
        }
        return programState;
    }

    @Override
    public String toString() {
        return "new(" + var_name + "," + exp.toString() + ")";
    }
}
