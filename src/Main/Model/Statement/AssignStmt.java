package Main.Model.Statement;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.VarAlreadyDefined;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Expression.IExpression;
import Main.Model.ProgramState;
import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

public class AssignStmt implements IStmt {
    private String varName;
    private IExpression expr;

    public AssignStmt(String varName, IExpression expr) {
        this.varName = varName;
        this.expr = expr;
    }

    @Override
    public String toString(){
        return varName + "=" + expr.toString();
    }

    @Override
    public ProgramState execute(ProgramState ps)throws DivisionByZeroException, VarNotDefinedException {
        SymTable<String, Integer> st = ps.getSymTable();
        Heap heap = ps.getHeap();
        if(st.containsKey(varName)) {
            st.replace(varName, expr.evaluate(st, heap));
        }
        else{
            st.put(this.varName, this.expr.evaluate(st, heap));
        }
        return ps;
    }
}
