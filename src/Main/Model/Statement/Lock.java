package Main.Model.Statement;

import Main.Exceptions.VarNotDefinedException;
import Main.Model.ProgramState;
import Main.Model.Utils.LockTable;
import Main.Model.Utils.SymTable;

import java.util.concurrent.locks.ReadWriteLock;

public class Lock implements IStmt {
    private String var_name;
    public Lock(String var_name){
        this.var_name = var_name;
    }

    @Override
    public String toString() {
        return "Lock(" + var_name + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws VarNotDefinedException {
        SymTable<String, Integer> symTable = programState.getSymTable();
        if(!symTable.containsKey(var_name)) throw new VarNotDefinedException("Invalid variable!\n");
        int foundIndex = symTable.get(var_name);
        LockTable lockTable = programState.getLockTable();

        ReadWriteLock lock = lockTable.readWriteLock;
        lock.writeLock().lock();
        if(!lockTable.containsKey(foundIndex)) throw new VarNotDefinedException("Invalid lock!\n");
        if(lockTable.get(foundIndex) == -1){
            lockTable.put(foundIndex, programState.getID());
        }
        else
        {
            programState.getExeStack().push(new Lock(var_name));
        }
        lock.writeLock().unlock();
        return null;
    }
}
