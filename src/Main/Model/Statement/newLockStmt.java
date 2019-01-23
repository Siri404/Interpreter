package Main.Model.Statement;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Expression.IExpression;
import Main.Model.ProgramState;
import Main.Model.Utils.Heap;
import Main.Model.Utils.LockTable;
import Main.Model.Utils.SymTable;

import java.util.concurrent.locks.ReadWriteLock;

public class newLockStmt implements IStmt {
    private String var_name;

    public newLockStmt(String var_name){
        this.var_name = var_name;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        SymTable<String, Integer> symTable = programState.getSymTable();
        LockTable lockTable = programState.getLockTable();
        ReadWriteLock lock = lockTable.readWriteLock;

        lock.writeLock().lock();
        int address = lockTable.add(-1);
        if(symTable.containsKey(var_name)){
            symTable.replace(var_name, address);
        }
        else{
            symTable.put(var_name, address);
        }
        lock.writeLock().unlock();
        return null;
    }

    @Override
    public String toString() {
        return "newLock(" + var_name + ")";
    }
}
