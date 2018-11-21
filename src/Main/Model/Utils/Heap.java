package Main.Model.Utils;


import java.util.Map;

public class Heap extends MyDict<Integer, Integer> {
    private int nextFree;
    public Heap(){
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
        nextFree +=1;
        put(nextFree, value);
        return nextFree;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Integer> m) {
        super.putAll(m);
        nextFree = this.size();
    }
}
