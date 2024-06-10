import java.util.Stack;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

public class loopCollection {

    public static void main(String[] args) {
        List<String> fruits = new Stack<>();
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Watermelon");
        fruits.add("Leci");
        fruits.add("Salak");

        for (String fruit : fruits) {
            System.out.printf("%s ", fruit);
        }
        System.out.println("\n" + fruits.toString());

        while (!fruits.isEmpty()) {
            System.out.printf("%s ", ((Stack<String>) fruits).pop());
        }

        // Mengganti elemen terakhir dengan "Strawberry"
        fruits.add("Melon");
        fruits.add("Durian");
        fruits.set(fruits.size() - 1, "Strawberry");

        // Menambahkan buah baru
        fruits.add("Mango");
        fruits.add("guava");
        fruits.add("avocado");

        // Melakukan sorting
        Collections.sort(fruits);

        System.out.println("");
        for (Iterator<String> it = fruits.iterator(); it.hasNext();) {
            String fruit = it.next();
            System.out.printf("%s ", fruit);
        }
        System.out.println("");
        fruits.stream().forEach(e -> {
            System.out.printf("%s ", e);
        });
        System.out.println("");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.printf("%s ", fruits.get(i));
        }
    }
}
