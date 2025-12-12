import java.util.*;
import java.util.regex.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day10 {
  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day10.txt");

    Pattern indicatorsPattern = Pattern.compile("\\[([.#]+)\\]");
    Pattern buttonsPattern = Pattern.compile("\\(([\\d,]+)\\)");

    int result = 0;

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        String line = scn.nextLine();

        List<Integer> buttons = new ArrayList<>();
        Matcher buttonM = buttonsPattern.matcher(line);

        while (buttonM.find()) {
          String button = buttonM.group(1);
          int mask = 0;

          for (String num : button.split(",")) {
            if (!num.isEmpty()) {
              int index = Integer.parseInt(num);
              mask |= 1 << index;
            }
          }
          buttons.add(mask);
        }

        int targetIndicators = 0;
        Matcher indicatorM = indicatorsPattern.matcher(line);
        while (indicatorM.find()) {
          String indicators = indicatorM.group(1);

          for (int i = 0; i < indicators.length(); i++) {
            if (indicators.charAt(i) == '#') {
              targetIndicators |= 1 << i;
            }
          }
        }

        result += dfs(0, 0, targetIndicators, buttons);
      }
      System.out.println(result);
    }
  }

  private static int dfs(int idx, int cur, int target, List<Integer> buttons) {
    if (idx == buttons.size()) {
      return (cur == target) ? 0 : Integer.MAX_VALUE;
    }

    int noPress = dfs(idx + 1, cur, target, buttons);
    int pressed = dfs(idx + 1, cur ^ buttons.get(idx), target, buttons);
    if (pressed != Integer.MAX_VALUE)
      pressed += 1;

    return Math.min(noPress, pressed);
  }
}
