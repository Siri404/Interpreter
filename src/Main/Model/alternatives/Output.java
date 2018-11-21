package Main.Model.alternatives;

import java.util.ArrayList;

public class Output<T> implements IOutput<T> {
    private ArrayList<T> list;
    public Output(){
        list = new ArrayList<T>();
    }
    @Override
    public void add(T elem) {
        list.add(elem);
    }

    public int size(){
        return list.size();
    }

    public String toString(){
        String s = "";
        for(T elem : list)
            s += elem.toString() + " ";
        return s;
    }
}
