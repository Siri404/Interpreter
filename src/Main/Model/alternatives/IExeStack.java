package Main.Model.alternatives;

public interface IExeStack<T> {
    void push(T elem);
    //Push an element on the stack
    boolean isEmpty();
    //Check if the stack is empty
    T pop();
    //Pop an element from the stack
}
