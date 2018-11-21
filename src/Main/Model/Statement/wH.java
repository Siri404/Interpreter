package Main.Model.Statement;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Expression.IExpression;
import Main.Model.ProgramState;
import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

public class wH implements IStmt {
    private String var_name;
    private IExpression exp;

    public wH(String var_name, IExpression exp){
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws VarNotDefinedException, DivisionByZeroException {
        SymTable<String, Integer> symTable = programState.getSymTable();
        Heap heap = programState.getHeap();

        if(!symTable.containsKey(var_name)) throw new VarNotDefinedException("Invalid variable!\n");
        int address = symTable.get(var_name);
        if(!heap.containsKey(address)) throw new VarNotDefinedException("Invalid address!\n");
        int value = exp.evaluate(symTable, heap);
        heap.replace(address, value);
        return programState;
    }

    @Override
    public String toString() {
        return "wH(" + var_name + ")";
    }
}
