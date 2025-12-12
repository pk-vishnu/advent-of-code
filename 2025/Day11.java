import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day11 {
  static Map<String, List<String>> adj = new HashMap<>();
  static Map<String, Long> memo = new HashMap<>();

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

    // long result_p1 = dfsP1("you");
    long result_p2 = dfsP2("svr", false, false);

    System.out.println(result_p2);
  }

  private static long dfsP2(String node, boolean dacSeen, boolean fftSeen) {
    if (node.equals("fft")) {
      fftSeen = true;
    }
    if (node.equals("dac")) {
      dacSeen = true;
    }

    if (node.equals("out")) {
      return (dacSeen && fftSeen) ? 1 : 0;
    }

    String key = node + "|" + dacSeen + "|" + fftSeen;

    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    long paths = 0;
    for (String next : adj.getOrDefault(node, Collections.emptyList())) {
      paths += dfsP2(next, dacSeen, fftSeen);
    }
    memo.put(key, paths);
    return paths;
  }

  private static long dfsP1(String node) {
    if (node.equals("out")) {
      return 1;
    }
    if (memo.containsKey(node)) {
      return memo.get(node);
    }
    long paths = 0;
    for (String next : adj.getOrDefault(node, Collections.emptyList())) {
      paths += dfsP1(next);
    }
    memo.put(node, paths);
    return paths;
  }
}
