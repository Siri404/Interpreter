package Main.Model.Statement;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Expression.IExpression;
import Main.Model.ProgramState;
import Main.Model.Utils.MyStack;

public class IfStmt implements IStmt {
    private IExpression exp;
    private IStmt thenStmt;
    private IStmt elseStmt;

    public IfStmt(IExpression exp,
                  IStmt thenStmt,
                  IStmt elseStmt){
        this.exp = exp;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public String toString(){
        return "if("
                +exp.toString()
                +")then("
                + thenStmt.toString()
                +")else("
                +elseStmt.toString()
                +")";
    }

    @Override
    public ProgramState execute(ProgramState ps)throws DivisionByZeroException, VarNotDefinedException {
        MyStack<IStmt> exeStack = ps.getExeStack();
        if(exp.evaluate(ps.getSymTable(), ps.getHeap()) != 0){
            exeStack.push(thenStmt);
        }
        else{
            exeStack.push(elseStmt);
        }
        return null;
    }
}
