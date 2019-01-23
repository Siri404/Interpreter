package Main.Model;

import Main.Exceptions.*;
import Main.Model.Statement.IStmt;
import Main.Model.Utils.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class ProgramState {
    private static int nextID = 1;
    private ExeStack<IStmt> exeStack;
    private Output<Integer> output;
    private SymTable<String, Integer> symTable;
    private IStmt program;
    private FileTable fileTable = new FileTable();
    private Heap heap = new Heap();
    private LockTable lockTable = new LockTable();
    private int ID;


    public ProgramState(ExeStack<IStmt> exeStack,
                        Output<Integer> output,
                        SymTable<String, Integer> symTable,
                        IStmt program,
                        FileTable fileTable,
                        Heap heap,
                        LockTable lockTable){
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;
        this.fileTable = fileTable;
        this.heap=heap;
        this.lockTable = lockTable;
        this.ID = ProgramState.nextID;
        ProgramState.nextID +=1;
    }

    public ProgramState(ExeStack<IStmt> exeStack,
                        Output<Integer> output,
                        SymTable<String, Integer> symTable,
                        IStmt program){
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;
        this.ID = ProgramState.nextID;
        ProgramState.nextID +=1;
    }

    public ProgramState(ProgramState source) {
        exeStack = new ExeStack<IStmt>();
        symTable = new SymTable<String, Integer>();

        // copy
        source.symTable.forEach(symTable::put);

        // reference
        output = source.output;
        // reference
        fileTable = source.fileTable;
        // reference
        heap = source.heap;
        //reference
        lockTable = source.lockTable;
        ID = ProgramState.nextID;
        ProgramState.nextID +=1;
    }

    public ProgramState executeOneStep() throws  EmptyStackException, DivisionByZeroException,
            VarNotDefinedException, FileAlreadyOpenException, VarAlreadyDefined, IOException, InvalidFileException{
        if(exeStack.empty()) throw new EmptyStackException("Empty Execution Stack\n");
        IStmt statement = exeStack.pop();
        return statement.execute(this);

    }

    public boolean isNotCompleted(){
        return !exeStack.empty();
    }

    public ExeStack<IStmt> getExeStack(){
        return exeStack;
    }

    public Output<Integer> getOutput(){
        return output;
    }

    public SymTable<String, Integer> getSymTable(){
        return symTable;
    }

    public IStmt getProgram(){
        return program;
    }

    public FileTable getFileTable(){
        return fileTable;
    }

    public Heap getHeap() {
        return heap;
    }

    public LockTable getLockTable() {
        return lockTable;
    }

    public int getID(){
        return ID;
    }

    public void setExeStack(ExeStack<IStmt> exeStack){
        this.exeStack = exeStack;
    }

    public void setOutput(Output<Integer> output){
        this.output = output;
    }

    public void setSymTable(SymTable<String, Integer> symTable){
        this.symTable = symTable;
    }

    public void setProgram(IStmt program){
        this.program = program;
    }

    public void setFileTable(FileTable fileTable){
        this.fileTable = fileTable;
    }

    public void setHeap(Heap heap) {
        this.heap = heap;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Execution stack:\n"
                + exeStack.toString()
                + "\nSymTable:\n"
                + symTable.toString()
                + "\nOutput:\n"
                + output.toString()
                + "\nFileTable:\n"
                + fileTable.toString()
                + "\nHeap:\n"
                + heap.toString()
                + "\nLock Table:\n"
                + lockTable.toString()
                + "\nProgram:\n"
                + program.toString()
                + "\nID:"
                + ID
                + "\n\n\n";
    }
}
