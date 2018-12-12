package Main.Model.Statement;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Expression.IExpression;
import Main.Model.ProgramState;
import Main.Model.Utils.Heap;
import Main.Model.Utils.Output;
import Main.Model.Utils.SymTable;

public class PrintStmt implements IStmt{
    private IExpression exp;

    public PrintStmt(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" +exp.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState ps) throws DivisionByZeroException, VarNotDefinedException {
        Output<Integer> output = ps.getOutput();
        SymTable<String, Integer> symTable = ps.getSymTable();
        Heap heap = ps.getHeap();

        output.add(exp.evaluate(symTable, heap));
        return null;
    }
}
