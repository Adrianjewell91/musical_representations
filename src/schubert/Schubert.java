package schubert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Schubert {
  private static Map<Integer, String> pitches = Map.of(
       8, "Eflat",
       10, "F",
       0, "G",
       1, "Aflat",
       3, "Bflat",
       5, "C",
       7, "D",
       // Non scale tones:
       9, "Enat",
       11, "Fsharp"
  );

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

    System.out.println("scale Eflat, it is a 7 tone scale with trace usages of passing tones:");
    System.out.print(scale(passage));
    Map<Integer,Integer> map = scale(passage);
    System.out.println(
        map.entrySet()
           .stream()
           .collect(
               Collectors.toMap(
                   e -> pitches.get(e.getKey()),
                   e -> e.getValue()
               )
           )
    );

    voiceExchange(passage);


    System.out.println(Arrays.toString(melodicGroupings(passage)));
  }

  // Chords
  // Scale
  // Voice Exchange
  // Melody decomposition

  /*
   * At each E flat on the downbeat, divide the passage.
   */
  private static int[] segment(int[][] passage)
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

  // count the pitches and asses them.
  private static Map<Integer, Integer> scale(int [][] passage)
  {
    Map<Integer, Integer> pitchCount = new HashMap<>();

    for (int[] ints : passage) {
      for (int anInt : ints) {
        if (anInt != 0)
        {
          pitchCount.put(anInt % 12, pitchCount.getOrDefault(anInt % 12, 0) + 1);
        }
      }
    }

    // take the min and reduce min times?

    // use the musical key algorithm, which probably accounts for frequency etc.
    return pitchCount;
  }


  // voice exchange: find transformation of a major tenth to a major sixth and back again.
  private static void voiceExchange(int[][] passage)
  {
    int[] segments = segment(passage);

    int[] rootVoices = new int[3];

    int i = 0;
    for (int segmentation: segments)
    {
      if (passage[0][segmentation] % 12 == 8)
      {
        rootVoices[i] = 0;
        i++;

        if (passage[2][segmentation] % 12 != 0)
        {
          throw new RuntimeException("Not a voice exchange");
        }
      }

      else if (passage[2][segmentation] % 12 == 8)
      {
        rootVoices[i] = 2;
        i++;

        if (passage[0][segmentation] % 12 != 0)
        {
          throw new RuntimeException("Not a voice exchange");
        }
      }

      if (i > 2)
      {
        break;
      }
    }

  }

  // melodic groupings: chord tones begin and end the motif.
  private static String[] melodicGroupings(int[][] passage)
  {
    Map<List<Integer>, String> chords = Map.of(List.of(1,5,8), "IV", List.of(3, 7, 10), "V", List.of(8, 12, 3), "I");

    int[] group1 = new int[] { passage[0][6]  % 12, passage[0][14] % 12 };
    int[] group2 = new int[] { passage[0][15] % 12, passage[0][17] % 12 };
    int[] group3 = new int[] { passage[0][18] % 12, passage[0][21] % 12 };
    int[] group4 = new int[] { passage[0][22] % 12, passage[0][26] % 12 };

    int[][] groups = new int[][] { group1, group2, group3, group4 };

    // For each group return greatest match with the chord.
    int i = 0;
    String[] groupNames = new String[groups.length];

    for (int[] group: groups)
    {
      int maxCount = 0;
      String bestChord = "";

      for (Map.Entry<List<Integer>, String> entry: chords.entrySet())
      {
        int count = 0;
        String chord = entry.getValue();
        for (int pitch : group)
        {
          if (entry.getKey().contains(pitch))
          {
            count++;
          }
        }

        if (count >= maxCount) {
          bestChord = chord;
        }
        maxCount = Math.max(maxCount, count);

      }

      groupNames[i] = bestChord;
      i++;
    }

    return groupNames;
  }

}