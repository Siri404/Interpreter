package Main.Controller;

import Main.Exceptions.*;
import Main.Model.ProgramState;
import Main.Model.Statement.IStmt;
import Main.Model.Utils.ExeStack;
import Main.Model.Utils.FileTable;
import Main.Model.Utils.Heap;
import Main.Repository.IRepository;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;

    public Controller(IRepository repository){
        this.repository = repository;
    }

    private Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                             Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private ProgramState executeStep(ProgramState programState) throws EmptyStackException, DivisionByZeroException,
            VarNotDefinedException, FileAlreadyOpenException, VarAlreadyDefined, IOException, InvalidFileException
    {
        System.out.print(programState.toString());
        ExeStack<IStmt> executionStack = programState.getExeStack();
        if (executionStack.isEmpty()) {
            throw new EmptyStackException("Empty Execution Stack\n");
        }
        IStmt statement = executionStack.pop();
        return statement.execute(programState);
    }

    public void executeOneStep() throws NoProgramInputException, EmptyStackException, DivisionByZeroException,
            VarNotDefinedException, FileAlreadyOpenException, VarAlreadyDefined, IOException, InvalidFileException{
        if(repository.size() == 0){
            throw new NoProgramInputException("No program input yet\n");
        }
        ProgramState programState = repository.getCurrentProgram();
        executeStep(programState);
        repository.logPrgStateExec();

    }

    public void executeAllSteps() throws NoProgramInputException, EmptyStackException, DivisionByZeroException,
            VarNotDefinedException, FileAlreadyOpenException, VarAlreadyDefined, IOException, InvalidFileException{
        if(repository.size() == 0){
            throw new NoProgramInputException("No program input yet\n");
        }
        ProgramState programState = repository.getCurrentProgram();
        try {
            while (true) {
                executeStep(programState);
                Heap heap = new Heap();
                heap.putAll(conservativeGarbageCollector(
                        programState.getSymTable().values(),
                        programState.getHeap()));
                programState.setHeap(heap);
                repository.logPrgStateExec();
            }
        }catch (EmptyStackException e){
            throw new EmptyStackException("End of program reached!\n");
        }
        finally {
            FileTable fileTable = programState.getFileTable();
            fileTable.forEach((key, value) -> {
                try {
                    value.y.close();
                }catch (IOException e){
                    throw new FileCloseException(e.getMessage());
                }
            }
            );
        }
    }

    public void setCurrentProgramState(ProgramState programState) {
        this.repository.setCurrentProgram(programState);
    }

    public void setCurrentProgramState(int opt) {
        try{
            ProgramState programState = this.repository.setCurrentProgram(opt);
            System.out.print(programState.toString());
        }
        catch (InvalidProgramException e){
            System.out.print(e.getMessage());
        }
    }

    public void addProgram(ProgramState program){
        this.repository.addProgram(program);
    }

}
