
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

        try (Scanner sc = new Scanner(new FileReader("src/text.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                ArrayList<Integer> row = new ArrayList<>();

                for (String part : parts) {
                    row.add(Integer.valueOf(part));
                }
                matrix.add(row);
            }
        } catch (IOException e) {}

        System.out.println(partOne(matrix));
        System.out.println(partTwo(matrix));
    }
    public static int partOne(ArrayList<ArrayList<Integer>> a) {
        int safeCount = 0;

        for (ArrayList<Integer> row : a) {
            if (isSafe(row)) {
                safeCount++;
            }
        }
        return safeCount;
    }
    
    public static int partTwo(ArrayList<ArrayList<Integer>> a) {
        int safeCount = 0;

        for (ArrayList<Integer> row : a) {
            if (isSafe(row)) {
                safeCount++;
            } else {
                boolean safeWithRemoval = false;
                for (int i = 0; i < row.size(); i++) {
                    ArrayList<Integer> copy = new ArrayList<>(row);
                    copy.remove(i);
                    if (isSafe(copy)) {
                        safeWithRemoval = true;
                        break;
                    }
                }
                if (safeWithRemoval) {
                    safeCount++;
                }
            }
        }
        return safeCount;
    }
    public static boolean isSafe(ArrayList<Integer> row) {
        boolean increasing = row.get(0) < row.get(1);

        for (int i = 0; i < row.size() - 1; i++) {
            int diff = row.get(i + 1) - row.get(i);
            if ((Math.abs(diff) < 1 || Math.abs(diff) > 3) ||
                (increasing && diff <= 0) ||
                (!increasing && diff >= 0)) {
                return false;
            }
        }
        return true;
    }
}