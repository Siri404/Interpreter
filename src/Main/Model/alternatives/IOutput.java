package Main.Model.alternatives;

public interface IOutput<T> {
    void add(T value);
    //Add a new element to the output list
    int size();
    //Return the size of the output list
}
