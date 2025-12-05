import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day5 {
  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day5.txt");
    ArrayList<long[]> ranges = new ArrayList<>();
    long fresh = 0;

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        String line = scn.nextLine().trim();
        if (line.isEmpty()) {
          break;
        }
        String[] range = line.split("-");
        long lower = Long.parseLong(range[0]);
        long higher = Long.parseLong(range[1]);
        ranges.add(new long[] { lower, higher });
      }
      // part1
      // while (scn.hasNextLine()) {
      // String line = scn.nextLine().trim();
      // if (line.isEmpty()) {
      // break;
      // }
      // long id = Long.parseLong(line);
      // for (long[] range : ranges) {
      // if (id >= range[0] && id <= range[1]) {
      // fresh++;
      // break;
      // }
      // }
      // }
      // System.out.println(fresh);

      // part2- merge intervals
      ranges.sort(Comparator.comparingLong(a -> a[0]));
      long total = 0;
      long currentStart = ranges.get(0)[0];
      long currentEnd = ranges.get(0)[1];

      for (int i = 1; i < ranges.size(); i++) {
        long start = ranges.get(i)[0];
        long end = ranges.get(i)[1];

        if (start <= currentEnd + 1) {
          currentEnd = Math.max(currentEnd, end);
        } else {
          total += (currentEnd - currentStart + 1);
          currentStart = start;
          currentEnd = end;
        }
      }
      total += (currentEnd - currentStart + 1);
      System.out.println(total);
    }
  }
}
