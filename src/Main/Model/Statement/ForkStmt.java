package Main.Model.Statement;

import Main.Exceptions.*;
import Main.Model.ProgramState;
import Main.Model.Utils.ExeStack;
import Main.Model.Utils.SymTable;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ForkStmt implements IStmt {
    private IStmt stmt;
    public ForkStmt(IStmt stmt){
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "fork(" +stmt.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws DivisionByZeroException, VarNotDefinedException,
            FileAlreadyOpenException, VarAlreadyDefined, IOException, InvalidFileException {
        ProgramState newState = new ProgramState(prgState);
        newState.setProgram(stmt);
        newState.getExeStack().push(stmt);
        return newState;
    }
}
