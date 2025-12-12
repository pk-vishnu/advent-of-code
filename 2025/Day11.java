import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day11 {
  static Map<String, List<String>> adj = new HashMap<>();
  static Map<String, Integer> memo = new HashMap<>();

  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day11.txt");

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        String[] input = scn.nextLine().split(" ");
        String key = input[0].substring(0, input[0].length() - 1);
        List<String> values = new ArrayList<>();
        for (int i = 1; i < input.length; i++) {
          values.add(input[i]);
        }
        adj.put(key, values);
      }
    }

    int result = dfs("you");
    System.out.println(result);
  }

  private static int dfs(String node) {
    if (node.equals("out")) {
      return 1;
    }

    if (memo.containsKey(node)) {
      return memo.get(node);
    }

    int paths = 0;
    for (String next : adj.getOrDefault(node, Collections.emptyList())) {
      paths += dfs(next);

    }
    memo.put(node, paths);
    return paths;
  }
}
