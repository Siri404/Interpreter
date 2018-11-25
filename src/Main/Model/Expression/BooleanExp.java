package Main.Model.Expression;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

public class BooleanExp implements IExpression {
    private IExpression exp1, exp2;
    private String op;
    public BooleanExp(IExpression exp1, IExpression exp2, String op){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public String toString() {
        return "(" + exp1.toString() + op + exp2.toString() + ")";
    }

    @Override
    public int evaluate(SymTable<String, Integer> st, Heap heap) throws DivisionByZeroException, VarNotDefinedException {
        int firstRes = this.exp1.evaluate(st, heap);
        int secondRes = this.exp2.evaluate(st, heap);

        switch (this.op) {
            case "<":
                return firstRes < secondRes ? 1 : 0;

            case "<=":
                return firstRes <= secondRes ? 1 : 0;

            case "==":
                return firstRes == secondRes ? 1 : 0;

            case "!=":
                return firstRes != secondRes ? 1 : 0;

            case ">=":
                return firstRes >= secondRes ? 1 : 0;

            case ">":
                return firstRes > secondRes ? 1 : 0;

            default:
                throw new RuntimeException("Invalid Operator!\n");
        }
    }
}
