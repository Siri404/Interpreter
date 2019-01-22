package Main.Controller;

import Main.Exceptions.*;
import Main.Exceptions.EmptyStackException;
import Main.Model.ProgramState;
import Main.Model.Statement.IStmt;
import Main.Model.Utils.ExeStack;
import Main.Model.Utils.FileTable;
import Main.Model.Utils.Heap;
import Main.Model.Utils.Observer;
import Main.Repository.IRepository;
import Main.Repository.Repository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private ExecutorService executor;

    Controller(IRepository repository, Observer o) {
        this.repository = repository;
        ((Repository) repository).registerObserver(o);
    }

    private Map<Integer, Integer> conservativeGarbageCollector(List<ProgramState> programStateList,
                                                               Map<Integer, Integer> heap) {
        Collection<Integer> symTableValues = new ArrayList<Integer>();
        programStateList.forEach(programState -> (symTableValues).addAll(programState.getSymTable().values()));
        return heap.entrySet().stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<ProgramState> removeCompletedPrograms(List<ProgramState> inProgramList) {
        return inProgramList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    void oneStepAllPrg(List<ProgramState> programStateList) throws InterruptedException, EmptyStackException {
        if(executor == null){
            executor = Executors.newFixedThreadPool(2);
        }
        List<Callable<ProgramState>> callList = programStateList.stream()
                .map((programState) -> (Callable<ProgramState>) (() -> {
                    return programState.executeOneStep();
                }))
                .collect(Collectors.toList());

        List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                            try {
                                return future.get();
                            } catch (Exception e) {
                                return null;
                            }
                        }
                )
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        programStateList.addAll(newProgramList);
        programStateList.forEach(programState -> {repository.logPrgStateExec(programState);System.out.println(programState);});
        repository.setProgramList(programStateList);
    }

    private ProgramState executeStep(ProgramState programState) throws EmptyStackException, DivisionByZeroException,
            VarNotDefinedException, FileAlreadyOpenException, VarAlreadyDefined, IOException, InvalidFileException {
        System.out.print(programState.toString());
        ExeStack<IStmt> executionStack = programState.getExeStack();
        if (executionStack.isEmpty()) {
            throw new EmptyStackException("Empty Execution Stack\n");
        }
        IStmt statement = executionStack.pop();
        return statement.execute(programState);
    }

    public void executeAllSteps() throws NoProgramInputException, EmptyStackException, DivisionByZeroException,
            VarNotDefinedException, FileAlreadyOpenException, VarAlreadyDefined, IOException, InvalidFileException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList = removeCompletedPrograms(repository.getProgramList());
        while (programStateList.size() > 0) {
            Heap heap = new Heap();
            heap.putAll(conservativeGarbageCollector(
                    programStateList,
                    programStateList.get(0).getHeap()));
            programStateList.forEach(p->p.setHeap(heap));
            oneStepAllPrg(programStateList);
            programStateList = removeCompletedPrograms(repository.getProgramList());
        }
        executor.shutdownNow();
        List<ProgramState> tempList = repository.getProgramList();
        FileTable fileTable = tempList.get(0).getFileTable();
        fileTable.forEach((key, value) -> {
            try {
                value.y.close();
            } catch (IOException e) {
                throw new FileCloseException(e.getMessage());
            }
        });
        repository.setProgramList(programStateList);
    }

//    public void setCurrentProgramState(ProgramState programState) {
//        this.repository.setCurrentProgram(programState);
//    }

//    public void setCurrentProgramState(int opt) {
//        try{
//            ProgramState programState = this.repository.setCurrentProgram(opt);
//            System.out.print(programState.toString());
//        }
//        catch (InvalidProgramException e){
//            System.out.print(e.getMessage());
//        }
//    }

    public void addProgram(ProgramState program) {
        this.repository.addProgram(program);
    }
    public IRepository getRepo(){
        return repository;
    }

}
