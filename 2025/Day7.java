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

  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day7.txt");

    ArrayList<char[]> manifold = new ArrayList<>();

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

    int rows = manifold.size();
    int cols = manifold.get(0).length;

    Queue<Beam> q = new LinkedList<>();
    q.add(new Beam(0, sc));

    boolean[][] visited = new boolean[rows][cols];

    int splits = 0;

    while (!q.isEmpty()) {
      Beam b = q.poll();
      int nr = b.r + 1;
      int nc = b.c;

      if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
        continue;
      }

      if (visited[nr][nc]) {
        continue;
      }
      visited[nr][nc] = true;

      char ch = manifold.get(nr)[nc];

      if (ch == '.') {
        q.add(new Beam(nr, nc));
      } else if (ch == '^') {
        splits++;
        q.add(new Beam(nr, nc - 1));
        q.add(new Beam(nr, nc + 1));
      }
    }
    System.out.println(splits);
  }
}
