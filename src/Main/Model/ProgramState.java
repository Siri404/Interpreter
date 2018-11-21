package Main.Model;

import Main.Model.Statement.IStmt;
import Main.Model.Utils.*;

public class ProgramState {
    private ExeStack<IStmt> exeStack = new ExeStack<>();
    private Output<Integer> output = new Output<>();
    private SymTable<String, Integer> symTable = new SymTable<>();
    private IStmt program;
    private FileTable fileTable = new FileTable();
    private Heap heap = new Heap();


    public ProgramState(ExeStack<IStmt> exeStack,
                        Output<Integer> output,
                        SymTable<String, Integer> symTable,
                        IStmt program){
        this.exeStack = exeStack;
        this.output = output;
        this.symTable = symTable;
        this.program = program;
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
                + "\nProgram:\n"
                + program.toString()
                + "\n\n\n";
    }
}
