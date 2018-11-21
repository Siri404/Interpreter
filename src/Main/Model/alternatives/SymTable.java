package Main.Model.alternatives;

import java.util.HashMap;

public class SymTable<K, V> implements ISymTable<K, V> {
    private HashMap<K, V> dictionary;

    public SymTable(){
        dictionary =  new HashMap<K, V>();
    }

    @Override
    public void add(K key, V value){
        dictionary.put(key, value);
    }

    @Override
    public void remove(K key){
        dictionary.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public V getValue(K key) {
        return dictionary.get(key);
    }

    public String toString(){
        String s = "";
        for(K key : dictionary.keySet())
            s += key.toString() + "->" + dictionary.get
                    (key).toString();
        return s;
    }
}
