package Main.Model.alternatives;

public interface ISymTable<K, V> {
    void add(K key, V value);
    //Add a new <key, value> pair in the symbol table
    void remove(K key);
    //Remove the pair with key k
    //void setValue(K key, V value);
    //Set a new value for an existing key in the symbol table
    boolean contains(K key);
    //Return true if the key exists in the symbol table
    V getValue(K key);
    //Return the value associated with the given key
}
