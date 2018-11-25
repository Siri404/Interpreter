package Main.Model.Statement;

import Main.Exceptions.*;
import Main.Model.Expression.IExpression;
import Main.Model.ProgramState;
import Main.Model.Utils.ExeStack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.chrono.IsoChronology;

public class WhileStmt implements IStmt {
    private IExpression exp;
    private IStmt stmt;
    public WhileStmt(IExpression exp, IStmt stmt){
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "(while(" + exp.toString() + ")" + stmt.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws DivisionByZeroException, VarNotDefinedException,
            FileAlreadyOpenException, VarAlreadyDefined, IOException, InvalidFileException {
        ExeStack<IStmt> exeStack = prgState.getExeStack();
        if(exp.evaluate(prgState.getSymTable(), prgState.getHeap()) != 0) {
            exeStack.push(this);
            exeStack.push(stmt);
        }
        return prgState;
    }
}
