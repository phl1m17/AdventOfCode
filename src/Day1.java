
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        try (FileReader file = new FileReader("src/text.txt")) {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    list1.add(sc.nextInt());
                    list2.add(sc.nextInt());
                }
            }
        }
        
        Collections.sort(list1);
        Collections.sort(list2);
        System.out.println(partOne(list1, list2));
        System.out.println(partTwo(list1, list2));
    }
    public static int partOne(ArrayList<Integer> a, ArrayList<Integer> b) {
        int sum = 0;
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            sum += Math.abs(a.get(i) - b.get(i));
        }

        return sum;
    }
    public static int partTwo(ArrayList<Integer> a, ArrayList<Integer> b) {
        int score = 0;
        int counter = 0;
        for (int i = 0; i < a.size(); i++) {
            while (b.contains(a.get(i))) {
                counter++;
                b.remove(a.get(i));
            }
            score += a.get(i) * counter;
            counter = 0;
        }
        return score;
    }
}
