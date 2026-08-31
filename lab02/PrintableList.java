import java.util.Arrays;
import java.util.List;

public class PrintableList<T> {

    private List<T> list;

    public PrintableList(T[] array) {
        list = Arrays.asList(array);
    }

    public void printItems() {
        for (T item : list) {
            System.out.println(item);
        }
    }
}