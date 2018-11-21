package Main.Model.Utils;

public class ExeStack<T> extends MyStack<T> {
    @Override
    public synchronized String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T elem: this) {
            stringBuilder
                    .append(elem.toString())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
