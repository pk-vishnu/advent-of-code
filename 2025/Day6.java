import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day6 {
  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day6.txt");

    ArrayList<String[]> inputs = new ArrayList<>();

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        String input = scn.nextLine();
        String[] raw_row = input.split(" ");
        String[] row = Arrays.stream(raw_row).filter(s -> !s.trim().isEmpty()).toArray(String[]::new);
        inputs.add(row);
      }
    }

    long rows = inputs.size();
    long cols = inputs.get(0).length;

    String[] symbols = inputs.get(inputs.size() - 1);
    inputs.remove(inputs.size() - 1);

    long result = 0;
    for (int c = 0; c < cols; c++) {
      String sym = symbols[c];
      long tmp = 0;
      if (sym.equals("*")) {
        tmp = 1;
        for (int r = 0; r < rows - 1; r++) {
          tmp *= Long.parseLong(inputs.get(r)[c]);
        }
      } else if (sym.equals("+")) {
        for (int r = 0; r < rows - 1; r++) {
          tmp += Long.parseLong(inputs.get(r)[c]);
        }
      }
      result += tmp;
    }
    System.out.println(result);
  }
}
