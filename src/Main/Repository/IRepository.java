package Main.Repository;

import Main.Exceptions.InvalidProgramException;
import Main.Model.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgram();
    void setCurrentProgram(ProgramState program);
    ProgramState setCurrentProgram(int opt) throws InvalidProgramException;
    void addProgram(ProgramState program);
    void logPrgStateExec();
    int size();
}
