import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day7 {
  static class Beam {
    int r, c;

    Beam(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }

  static ArrayList<char[]> manifold = new ArrayList<>();
  static int rows, cols;
  static Long[][] memo;

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

    // Queue<Beam> q = new LinkedList<>();
    // q.add(new Beam(0, sc));
    //
    // boolean[][] visited = new boolean[rows][cols];
    //
    // int splits = 0;
    //
    // while (!q.isEmpty()) {
    // Beam b = q.poll();
    // int nr = b.r + 1;
    // int nc = b.c;
    //
    // if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
    // continue;
    // }
    //
    // if (visited[nr][nc]) {
    // continue;
    // }
    // visited[nr][nc] = true;
    //
    // char ch = manifold.get(nr)[nc];
    //
    // if (ch == '.') {
    // q.add(new Beam(nr, nc));
    // } else if (ch == '^') {
    // splits++;
    // q.add(new Beam(nr, nc - 1));
    // q.add(new Beam(nr, nc + 1));
    // }
    // }

    long splits = solve(0, sc);
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
}
