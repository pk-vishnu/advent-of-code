
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day3 {
  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day3.txt");

    int result = 0;

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {

        int best = 0;
        char[] input = scn.nextLine().toCharArray();
        //
        // for (int i = 0; i < input.length - 1; i++) {
        // int first = input[i] - '0';
        // for (int j = i + 1; j < input.length; j++) {
        // int second = input[j] - '0';
        // int num = first * 10 + second;
        // best = Integer.max(num, best);
        // }
        // }
        // result += best;

        int right_best = 0;

        for (int i = input.length - 1; i >= 0; i--) {
          if (right_best == 0) {
            right_best = input[i] - '0';
            continue;
          }

          int left = input[i] - '0';
          int right = right_best;
          int num = left * 10 + right;

          best = Integer.max(best, num);

          if (left > right) {
            right_best = left;
          }
        }
        result += best;
      }
    }
    System.out.println(result);
  }
}
