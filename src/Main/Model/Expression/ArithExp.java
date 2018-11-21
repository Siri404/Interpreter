package Main.Model.Expression;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

public class ArithExp implements IExpression {
    private IExpression op1, op2;
    private char operator;

    public ArithExp(char operator, IExpression op1, IExpression op2) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
    }

    @Override
    public String toString(){
        return String.valueOf(op1) + operator + String.valueOf(op2);
    }

    @Override
    public int evaluate(SymTable<String, Integer> st, Heap heap) throws DivisionByZeroException, VarNotDefinedException {
        int firstRes = this.op1.evaluate(st, heap);
        int secondRes = this.op2.evaluate(st, heap);

        switch (this.operator) {
            case '+':
                return firstRes + secondRes;

            case '-':
                return firstRes - secondRes;

            case '*':
                return firstRes * secondRes;

            case '/':
                if (secondRes == 0) {
                    throw new DivisionByZeroException("Division by 0!\n");
                }

                return firstRes / secondRes;

            default:
                throw new RuntimeException("Invalid Operator!\n");
        }
    }
}
