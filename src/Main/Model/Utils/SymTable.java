package Main.Model.Utils;

public class SymTable<K, V> extends MyDict<K, V> {
    @Override
    public synchronized String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (K key : keySet()) {
            stringBuilder
                    .append(key)
                    .append("=")
                    .append(get(key))
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
