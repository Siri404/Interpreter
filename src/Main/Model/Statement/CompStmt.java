package Main.Model.Statement;

import Main.Model.ProgramState;
import Main.Model.Utils.MyStack;

public class CompStmt implements IStmt {
    private IStmt st1, st2;

    public CompStmt(IStmt st1, IStmt st2){
        this.st1 = st1;
        this.st2 = st2;
    }

    @Override
    public String toString(){
        return (st1.toString() + ";" + st2.toString());
    }

    @Override
    public ProgramState execute(ProgramState ps){
        MyStack<IStmt> exeStack = ps.getExeStack();
        exeStack.push(st2);
        exeStack.push(st1);
        return  ps;
    }
}
