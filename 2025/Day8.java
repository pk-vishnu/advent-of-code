import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day8 {

  static class Edge {
    long d;
    int a, b;

    Edge(long d, int a, int b) {
      this.d = d;
      this.a = a;
      this.b = b;
    }
  }

  static List<int[]> coords = new ArrayList<>();

  static int[] parent;
  static int[] size;

  static void initUnionFind(int n) {
    parent = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  static int find(int x) {
    if (x != parent[x]) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }

  static boolean union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rootX == rootY) {
      return false;
    }

    if (size[rootX] >= size[rootY]) {
      parent[rootY] = rootX;
      size[rootX] += size[rootY];
    } else {
      parent[rootX] = rootY;
      size[rootY] += size[rootX];
    }

    return true;
  }

  public static void main(String[] args) throws FileNotFoundException {
    File obj = new File("./inputs/day8.txt");

    try (Scanner scn = new Scanner(obj)) {
      while (scn.hasNextLine()) {
        String[] input = scn.nextLine().split(",");
        int[] input_coords = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        coords.add(input_coords);
      }
    }

    int n = coords.size();
    initUnionFind(n);

    List<Edge> edges = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        long dx = coords.get(i)[0] - coords.get(j)[0];
        long dy = coords.get(i)[1] - coords.get(j)[1];
        long dz = coords.get(i)[2] - coords.get(j)[2];
        long dist = dx * dx + dy * dy + dz * dz;
        edges.add(new Edge(dist, i, j));
      }
    }
    edges.sort((e1, e2) -> Long.compare(e1.d, e2.d));

    // part1
    // for (int i = 0; i < 1000 && i < edges.size(); i++) {
    // Edge e = edges.get(i);
    // union(e.a, e.b);
    // }
    //
    // List<Integer> components = new ArrayList<>();
    // for (int i = 0; i < n; i++) {
    // if (find(i) == i) {
    // components.add(size[i]);
    // }
    // }
    // components.sort((a, b) -> Integer.compare(b, a));
    //
    // long result = (long) components.get(0) * components.get(1) *
    // components.get(2);
    // System.out.println(result);

    int components = n;
    for (Edge e : edges) {
      if (union(e.a, e.b)) {
        components--;
        if (components == 1) {
          long x1 = coords.get(e.a)[0];
          long x2 = coords.get(e.b)[0];
          System.out.println(x1 * x2);
          break;
        }
      }
    }
  }
}
