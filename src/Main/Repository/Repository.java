package Main.Repository;

import Main.Model.ProgramState;
import Main.Model.Utils.Observable;
import Main.Model.Utils.Observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository extends Observable implements IRepository, Observer {
    private List<ProgramState> programList = new ArrayList<ProgramState>();
    private ProgramState currentProgram;
    private String logFilePath;

    public Repository(String path, ProgramState program){
        super();
        logFilePath = path;
        this.currentProgram = program;
        programList.add(program);
    }

    @Override
    public void update() {
        // received update from one of the program states, notify the Observers higher up of the change
        notifyObservers();
    }

    public Repository(String path){
        logFilePath = path;
    }

//    @Override
//    public ProgramState getCurrentProgram(){
//        return currentProgram;
//    }
//
//    @Override
//    public void setCurrentProgram(ProgramState program){
//        this.currentProgram = program;
//    }
//
//    @Override
//    public ProgramState setCurrentProgram(int opt)throws InvalidProgramException{
//        if(opt >= programList.size()) {
//            throw new InvalidProgramException("Invalid program was chosen!\n");
//        }
//        this.currentProgram = programList.get(opt);
//        return currentProgram;
//    }


    @Override
    public List<ProgramState> getProgramList() {
        return programList;
    }

    @Override
    public void addProgram(ProgramState program){
        programList.add(program);
        //setCurrentProgram(program);
    }

    @Override
    public void setProgramList(List<ProgramState> programList) {
        this.programList = programList;
    }

    @Override
    public int size(){
        return programList.size();
    }

    @Override
    public void logPrgStateExec(ProgramState programState) {
        PrintWriter logFile = null;
        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(programState);
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
