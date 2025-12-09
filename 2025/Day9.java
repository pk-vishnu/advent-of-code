import java.util.*;
import java.io.*;

public class Day9 {
  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day9.txt");
    List<int[]> tiles = new ArrayList<>();

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        String[] input = scn.nextLine().split(",");
        int[] coords = Arrays.stream(input)
            .mapToInt(Integer::parseInt)
            .toArray();
        tiles.add(coords);
      }
    }

    long result = 0;

    for (int i = 0; i < tiles.size(); i++) {
      for (int j = i + 1; j < tiles.size(); j++) {
        int x1 = tiles.get(i)[0];
        int y1 = tiles.get(i)[1];
        int x2 = tiles.get(j)[0];
        int y2 = tiles.get(j)[1];

        int width = Math.abs(x1 - x2) + 1;
        int height = Math.abs(y1 - y2) + 1;
        long area = (long) width * height;

        if (area > result) {
          result = area;
        }
      }
    }

    System.out.println(result);
  }
}
