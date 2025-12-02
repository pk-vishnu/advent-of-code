import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class Day1 {
  public static void main(String[] args) throws FileNotFoundException {
    File fileObj = new File("./inputs/day1.txt");
    int result = 0;
    int dialPos = 50;
    try (Scanner fileReader = new Scanner(fileObj)) {
      while (fileReader.hasNextLine()) {
        String input = fileReader.nextLine();
        char dir = input.charAt(0);
        int rotations = Integer.parseInt(input.substring(1));
        if (dir == 'L') {
          while (rotations > 0) {
            dialPos -= 1;
            if (((dialPos % 100) + 100) % 100 == 0) {
              result += 1;
            }
            rotations--;
          }
        } else if (dir == 'R') {
          while (rotations > 0) {
            dialPos += 1;
            if (((dialPos % 100) + 100) % 100 == 0) {
              result += 1;
            }
            rotations--;
          }
        }
      }
    }
    System.out.println(result);
  }
}
