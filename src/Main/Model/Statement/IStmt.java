package Main.Model.Statement;

import Main.Exceptions.*;
import Main.Model.ProgramState;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt {
    String toString();
    ProgramState execute(ProgramState prgState) throws DivisionByZeroException, VarNotDefinedException,
            FileAlreadyOpenException, FileNotFoundException, VarAlreadyDefined, IOException, InvalidFileException;
}
