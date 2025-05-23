
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    static final int[] dx = { 0, 0, 1, -1, 1, -1, 1, -1 };
    static final int[] dy = { 1, -1, 0, 0, 1, -1, -1, 1 };
    static final String TARGET = "XMAS";

    public static void main(String[] args) throws IOException {
        ArrayList<String>grid = new ArrayList<>();
        try (FileReader file = new FileReader("src/text.txt")) {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    grid.add(sc.nextLine());
                }
            }
        }

        char[][] board = new char[grid.size()][];
        for (int i = 0; i < grid.size(); i++) {
            board[i] = grid.get(i).toCharArray();
        }

        System.out.println(countXMAS(board));
        System.out.println(countMASX(board));
    }
    // PART 1
    public static int countXMAS(char[][] board) {
        int count = 0;
        int rows = board.length;
        int cols = board[0].length;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                for (int dir = 0; dir < 8; dir++) {
                    if (matches(board, x, y, dx[dir], dy[dir])) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static boolean matches(char[][] board, int x, int y, int dx, int dy) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < TARGET.length(); i++) {
            int nx = x + dx * i;
            int ny = y + dy * i;
            if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || board[nx][ny] != TARGET.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    //PART 2

    public static int countMASX(char[][] board) {
        int count = 0;
        int rows = board.length;
        int cols = board[0].length;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (matchesMASX(board, x, y)) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean matchesMASX(char[][] board, int x, int y) {
        int rows = board.length;
        int cols = board[0].length;

        if (x - 1 < 0 || x + 1 >= rows || y - 1 < 0 || y + 1 >= cols) {
            return false;
        }

        return board[x][y] == 'A' &&
            // case 1
            ((board[x - 1][y - 1] == 'M' &&
            board[x - 1][y + 1] == 'S' &&
            board[x + 1][y - 1] == 'M' &&
            board[x + 1][y + 1] == 'S') || 
            // case 2
            (board[x - 1][y - 1] == 'S' &&
            board[x - 1][y + 1] == 'M' &&
            board[x + 1][y - 1] == 'S' &&
            board[x + 1][y + 1] == 'M') ||
            // case 3
            (board[x - 1][y - 1] == 'M' &&
            board[x - 1][y + 1] == 'M' &&
            board[x + 1][y - 1] == 'S' &&
            board[x + 1][y + 1] == 'S') || 
            // case 4
            (board[x - 1][y - 1] == 'S' &&
            board[x - 1][y + 1] == 'S' &&
            board[x + 1][y - 1] == 'M' &&
            board[x + 1][y + 1] == 'M'));
    }
}
