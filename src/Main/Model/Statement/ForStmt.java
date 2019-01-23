package Main.Model.Statement;

import Main.Model.Expression.IExpression;
import Main.Model.ProgramState;
import Main.Model.Utils.ExeStack;

public class ForStmt implements IStmt {
    private IExpression condition;
    private IStmt body, init, increment;
    public ForStmt(IStmt init, IExpression condition, IStmt increment, IStmt body){
        this.init = init;
        this.condition = condition;
        this.increment = increment;
        this.body = body;
    }


    @Override
    public String toString() {
        return "(for(" + init.toString() + ";" + condition.toString() + ";" + increment.toString() + ")" + body.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState prgState){
        ExeStack<IStmt> exeStack = prgState.getExeStack();
        IStmt newStmt = new CompStmt(init,
                new WhileStmt(condition, new CompStmt(body, increment)));
        exeStack.push(newStmt);
        return null;
    }
}
