package Main.Model.Utils;

public class Output<T> extends MyList<T> {
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
