package Main.Model.Utils;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTable extends MyDict<Integer, Integer> {
    private int nextFree;
    public ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public LockTable(){
        super();
        nextFree = 0;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int key : keySet()) {
            stringBuilder
                    .append(key)
                    .append(" -> ")
                    .append(get(key))
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public int add(int value){
        nextFree -=1;
        put(nextFree, value);
        return nextFree;
    }

}
