package Main.Model.Utils;

import java.io.BufferedReader;

public class FileTable extends MyDict<Integer, Tuple<String, BufferedReader>> {
    private int key;
    public FileTable(){
        super();
        key = 0;
    }

    @Override
    public synchronized String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int key : keySet()) {
            stringBuilder
                    .append(key)
                    .append("=")
                    .append(get(key).x)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public int add(Tuple<String, BufferedReader> value){
        key++;
        put(key, value);
        return key;
    }
}
