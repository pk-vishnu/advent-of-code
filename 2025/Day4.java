import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day4 {
  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day4.txt");

    int[][] directions = { { -1, 0 }, { -1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, {
        0, -1 }, { -1, 1 }, { 1, -1 } };
    ArrayList<char[]> matrix = new ArrayList<>();

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        char[] row_input = scn.nextLine().toCharArray();
        matrix.add(row_input);
      }
    }
    int rows = matrix.size();
    int cols = matrix.get(0).length;

    int result = 0;
    int iter_res = 1;
    while (iter_res != 0) {
      iter_res = 0;
      ArrayList<int[]> toRemove = new ArrayList<>();
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          if (matrix.get(r)[c] != '@') {
            continue;
          }
          int tmp_count = 0;
          for (int[] dir : directions) {
            int n_r = r + dir[0];
            int n_c = c + dir[1];
            if (n_r >= 0 && n_c >= 0 && n_r < rows && n_c < cols && matrix.get(n_r)[n_c] == '@') {
              tmp_count++;
            }
          }
          if (tmp_count < 4) {
            toRemove.add(new int[] { r, c });
          }
        }
      }
      for (int[] rc : toRemove) {
        matrix.get(rc[0])[rc[1]] = '.';
      }
      iter_res = toRemove.size();
      result += iter_res;
    }
    System.out.println(result);
  }
}
