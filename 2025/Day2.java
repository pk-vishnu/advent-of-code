import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day2 {
  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("../inputs/day2.txt");

    Set<Long> result = new HashSet<Long>();
    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        String line = scn.nextLine();
        String[] ranges = line.split(",");

        for (String range : ranges) {
          String[] high_low = range.split("-");
          long low = Long.parseLong(high_low[0]);
          long high = Long.parseLong(high_low[1]);

          for (long i = low; i <= high; i++) {
            String tmp = Long.toString(i);
            // part 1
            // if (tmp.length() % 2 != 0) {
            // continue;
            // }
            // if (Long.parseLong(tmp.substring(0, tmp.length() / 2)) == Long
            // .parseLong(tmp.substring(tmp.length() / 2))) {
            // result.add(i);
            // }

            // part 2
            int tmplen = tmp.length();

            for (int patLen = 1; patLen <= tmplen / 2; patLen++) {
              if (tmplen % patLen != 0)
                continue;

              String pattern = tmp.substring(0, patLen);
              int repeats = tmplen / patLen;

              StringBuilder stb = new StringBuilder();
              for (int k = 0; k < repeats; k++) {
                stb.append(pattern);
              }

              if (stb.toString().equals(tmp)) {
                result.add(i);
                break;
              }
            }
          }
        }
      }
      long res_sum = 0;
      for (long i : result) {
        res_sum += i;
      }
      System.out.println(res_sum);
    }
  }
}
