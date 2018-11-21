package Main.Repository;

import Main.Exceptions.InvalidProgramException;
import Main.Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Repository implements IRepository {
    private ArrayList<ProgramState> programs = new ArrayList<ProgramState>();
    private ProgramState currentProgram;
    private String logFilePath;

    public Repository(String path, ProgramState program){
        logFilePath = path;
        this.currentProgram = program;
        programs.add(program);
    }

    public Repository(String path){
        logFilePath = path;
    }

    @Override
    public ProgramState getCurrentProgram(){
        return currentProgram;
    }

    @Override
    public void setCurrentProgram(ProgramState program){
        this.currentProgram = program;
    }

    @Override
    public ProgramState setCurrentProgram(int opt)throws InvalidProgramException{
        if(opt >= programs.size()) {
            throw new InvalidProgramException("Invalid program was chosen!\n");
        }
        this.currentProgram = programs.get(opt);
        return currentProgram;
    }

    @Override
    public void addProgram(ProgramState program){
        programs.add(program);
        setCurrentProgram(program);
    }

    @Override
    public int size(){
        return programs.size();
    }

    @Override
    public void logPrgStateExec() {
        PrintWriter logFile = null;
        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(currentProgram);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        finally {
            if(logFile != null)
                logFile.close();
        }
    }
}
