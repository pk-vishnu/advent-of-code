import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day6 {
  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day6.txt");

    // part1
    // ArrayList<String[]> inputs = new ArrayList<>();
    // try (Scanner scn = new Scanner(obj)) {
    // while (scn.hasNextLine()) {
    // String input = scn.nextLine();
    // String[] raw_row = input.split(" ");
    // String[] row = Arrays.stream(raw_row).filter(s ->
    // !s.trim().isEmpty()).toArray(String[]::new);
    // inputs.add(row);
    // }
    // }
    //
    // String[] symbols = inputs.get(inputs.size() - 1);
    // inputs.remove(inputs.size() - 1);
    //
    // long rows = inputs.size();
    // long cols = inputs.get(0).length;
    //
    // long result = 0;
    // for (int c = 0; c < cols; c++) {
    // String sym = symbols[c];
    // long tmp = 0;
    // if (sym.equals("*")) {
    // tmp = 1;
    // for (int r = 0; r < rows; r++) {
    // tmp *= Long.parseLong(inputs.get(r)[c]);
    // }
    // } else if (sym.equals("+")) {
    // for (int r = 0; r < rows; r++) {
    // tmp += Long.parseLong(inputs.get(r)[c]);
    // }
    // }
    // result += tmp;
    // }
    // System.out.println(result);

    // part2
    // ArrayList<String> inputs = new ArrayList<>();
    // try (Scanner scn = new Scanner(obj)) {
    // while (scn.hasNextLine()) {
    // inputs.add(scn.nextLine());
    // }
    // }
    // String symbols = inputs.get(inputs.size() - 1);
    // inputs.remove(inputs.size() - 1);
    //
    // int cols = inputs.get(0).length();
    // long result = 0;
    //
    // for (int c = 0; c < cols; c++) {
    // StringBuilder num = new StringBuilder();
    //
    // }

    // part2
    ArrayList<String> lines = new ArrayList<>();
    int maxLen = 0;

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        String line = scn.nextLine();
        lines.add(line);
        maxLen = Math.max(maxLen, line.length());
      }
    }

    String symbols = lines.get(lines.size() - 1);
    lines.remove(lines.size() - 1);

    long result = 0;

    ArrayList<Long> blocknums = new ArrayList<>();
    char blockOp = ' ';

    for (int c = 0; c < maxLen; c++) {
      StringBuilder num = new StringBuilder();

      boolean colEmpty = true;
      for (String line : lines) {
        if (c < line.length()) {
          char ch = line.charAt(c);
          if (Character.isDigit(ch)) {
            num.append(ch);
            colEmpty = false;
          }
        }
      }

      if (c < symbols.length()) {
        char sym = symbols.charAt(c);
        if (sym == '+' || sym == '*') {
          blockOp = sym;
        }
      }

      if (num.length() > 0) {
        blocknums.add(Long.parseLong(num.toString()));
      }

      if (colEmpty || c == maxLen - 1) {
        if (!blocknums.isEmpty()) {
          long tmp = 0;
          if (blockOp == '*') {
            tmp = 1;
            for (long n : blocknums) {
              tmp *= n;
            }
          } else if (blockOp == '+') {
            for (long n : blocknums) {
              tmp += n;
            }
          }
          result += tmp;
          blocknums.clear();
          blockOp = ' ';
        }
      }
    }
    System.out.println(result);
  }
}
