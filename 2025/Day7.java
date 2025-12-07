import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day7 {
  static ArrayList<char[]> manifold = new ArrayList<>();
  static int rows, cols, splits;
  static Long[][] memo;
  static boolean[][] visited;

  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day7.txt");

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        String input = scn.nextLine();
        manifold.add(input.toCharArray());
      }
    }

    int sc = 0;
    char[] startLine = manifold.get(0);
    for (int i = 0; i < startLine.length; i++) {
      if (startLine[i] == 'S') {
        sc = i;
        break;
      }
    }

    rows = manifold.size();
    cols = manifold.get(0).length;
    memo = new Long[rows][cols];
    visited = new boolean[rows][cols];

    dfs(0, sc);
    // long splits = solve(0, sc); pt2
    System.out.println(splits);
  }

  static long solve(int r, int c) {
    if (r >= rows) {
      return 1;
    }
    if (c < 0 || c >= cols) {
      return 1;
    }
    if (memo[r][c] != null) {
      return memo[r][c];
    }

    char ch = manifold.get(r)[c];
    long result = 0;
    if (ch == '.' || ch == 'S') {
      result = solve(r + 1, c);
    } else if (ch == '^') {
      result = solve(r + 1, c - 1) + solve(r + 1, c + 1);
    }
    memo[r][c] = result;
    return result;
  }

  static void dfs(int r, int c) {
    int nr = r + 1;
    int nc = c;
    if (nr >= rows) {
      return;
    }
    if (visited[nr][nc]) {
      return;
    }
    visited[nr][nc] = true;

    if (manifold.get(nr)[nc] == '.') {
      dfs(nr, nc);
    } else if (manifold.get(nr)[nc] == '^') {
      splits++;
      if (nc - 1 >= 0) {
        dfs(nr, nc - 1);
      }
      if (nc + 1 < cols) {
        dfs(nr, nc + 1);
      }
    }
  }
}
