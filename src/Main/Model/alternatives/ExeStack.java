package Main.Model.alternatives;

import java.util.Stack;

public class ExeStack<T> implements IExeStack<T> {
    private Stack<T> stack;

    public ExeStack(){
        stack = new Stack<T>();
    }

    public void push(T elem){
        stack.push(elem);
    }

    public T pop() {
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public String toString(){
        String s = "";
        for(T elem : stack)
            s += elem.toString() + "\n";
        return s;
    }
}
