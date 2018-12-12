package Main.Repository;

import Main.Exceptions.InvalidProgramException;
import Main.Model.ProgramState;

import java.util.ArrayList;
import java.util.List;

public interface IRepository {
    //ProgramState getCurrentProgram();
    //void setCurrentProgram(ProgramState program);
    //ProgramState setCurrentProgram(int opt) throws InvalidProgramException;
    public List<ProgramState> getProgramList();
    public void setProgramList(List<ProgramState> programList);
    void addProgram(ProgramState program);
    void logPrgStateExec(ProgramState programState);
    int size();
}
