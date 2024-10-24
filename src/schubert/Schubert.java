package schubert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schubert {

  // Nine bars: eight plus pick-up.
  // Divided by voice.
  // 0 == not played.
  // lowest note is A flat (1) highest is High C. (
  // Chromatically indexed.
  static private int[][] passage = new int[][] {
      //Melody:
      new int[] { 0,0,0, 0,0,0, 39,39,39,  36,39,37, 36,34,32, 31,29,27,  25,24,22, 20,19,17, 15,17,19,   20,19,20, 21,20,21, 22,21,22,   23,22,23, 24,23,24, 25,24,25,  24,27,25, 24,25,27, 29,31,32,  34,41,39, 37,36,34, 32,31,29,  27,34,32, 31,29,27, 25,24,22,  20,22,24, 25,27,29, 31,32,34 },

      // Inner voice (approx)
      new int[] { 0,0,0, 0,0,0, 0,0,0,     0,0,0, 15,15,15, 15,15,15,     0,0,0,  15,15,15, 15,15,15,     0,0,0,  15,15,15, 15,15,15,     0,0,0, 15,15,15, 15,15,15,     0,0,0, 15,15,15, 15,15,15,     0,0,0, 17,17,17, 17,17,17,     0,0,0, 19,19,19, 19,19,19,     0,0,0, 20,20,20, 20,20,20 },

      // Bass
      new int[] { 0,0,0, 0,0,0, 0,0,0,     8,8,8, 8,8,8, 8,8,8,           10,10,10, 10,10,10, 10,10,10,   12,12,12, 12,12,12, 12,12,12,   7,7,7, 7,7,7, 7,7,7,           8,8,8, 8,8,8, 8,8,8,           1,1,1, 0,0,0, 0,0,0,           3,3,3, 0,0,0, 0,0,0,           8,8,8, 0,0,0, 0,0,0 }
  };

  public static void main(String[] args)
  {
    System.out.println("schubert:");

    System.out.println("divisions should be 9, 27, 45, 72: ");
    System.out.println(Arrays.toString(segment(passage)));
  }

  // Chords
  // Scale
  // Voice Exchange
  // Melody decomposition

  /*
   * At each E flat, divide the passage.
   */
  public static int[] segment(int[][] passage)
  {
    int[] divisions = new int[4];

    int idx = 0;
    for (int i = 0; i < passage[0].length; i++) {
      if (
          (passage[0][i] % 12 == 8 || passage[1][i] % 12 == 8 || passage[2][i] % 12 == 8)
          && i % 9 == 0
      )
      {
        divisions[idx] = i;
        idx++;
      }
    }

    return divisions;
  }

}
