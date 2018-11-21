package Main.Model.Expression;

import Main.Exceptions.DivisionByZeroException;
import Main.Exceptions.VarNotDefinedException;
import Main.Model.Utils.Heap;
import Main.Model.Utils.SymTable;

public interface IExpression {
    int evaluate(SymTable<String, Integer> st, Heap heap) throws DivisionByZeroException, VarNotDefinedException;
    String toString();
}
