package contextual;

public class Contextual {
  private static final int[][] passage = {
      {0,0,0,0, 1,0,0,0},
      {0,0,0,0, 1,0,0,0},
      {0,0,0,0, 1,0,0,0},
      {0,0,0,0, 1,0,0,0},

      {0,0,0,0, 1,0,0,0},
      {0,0,0,0, 1,0,0,0},
      {0,0,0,0, 0,1,0,0}, // Neighbor
      {0,0,0,0, 1,0,0,0},

      {0,0,0,1, 0,0,0,0},
      {0,0,0,0, 0,0,0,0},
      {0,0,1,0, 0,0,0,0},
      {0,0,0,0, 0,0,0,0},

      {0,1,0,0, 0,0,0,0},
      {0,0,0,0, 0,0,0,0},
      {1,0,0,0, 0,0,0,0},
      {0,0,0,0, 0,0,0,0},


      {1,0,0,0, 0,0,0,0},
      {1,0,0,0, 0,0,0,0},
      {0,0,0,0, 0,1,0,0}, // Arpeggiation
      {0,0,0,0, 0,1,0,0},

      {0,0,0,0, 0,1,0,0}, // Tenuto
      {0,0,0,0, 0,1,0,0},
      {0,0,0,0, 0,1,0,0},
      {0,0,0,0, 0,1,0,0},

      {0,0,0,0, 0,1,0,0},
      {0,0,0,0, 0,1,0,0},
      {0,0,0,0, 1,0,0,0},
      {0,0,0,0, 1,0,0,0},

      {0,0,0,0, 0,0,1,0},
      {0,0,0,0, 0,0,1,0},
      {0,0,0,0, 0,0,0,1},
      {0,0,0,0, 0,0,0,1},
  };

  private static final String NEIGHBOR = "Neighbor";
  private static final String ARPEGGIATION = "Arpeggiation";
  private static final String TENUTO = "Tenuto";

  private static String[] harmonies = { "F", "Fadd9", "Bflatadd9"};

  public static void main(String[] args) {
    melodic();
    harmonic();
  }

  private static void harmonic() {
    for (int i = 0; i < harmonies.length; i++) {
      if (harmonies[i].equals("F")) {
        System.out.println(NEIGHBOR);
      }

      if (harmonies[i].equals("Fadd9")) {
        System.out.println(ARPEGGIATION);
      }

      if (harmonies[i].equals("Bflatadd9")) {
        System.out.println(TENUTO);
      }
    }
  }

  private static void melodic() {
    for (int i = 0; i < passage.length; i++)
    {
      if (passage[i][5] == 0) { continue; }

      if (passage[i-1][4] == 1 && passage[i+1][4] == 1)
      {

        System.out.println(NEIGHBOR);

      }

      if (passage[i-1][0] == 1)
      {

        System.out.println(ARPEGGIATION);

      }

      if (passage[i+1][5] == 1 &&
          passage[i+2][5] == 1 &&
          passage[i+3][5] == 1 &&
          passage[i+4][5] == 1 &&
          passage[i+5][5] == 1 &&
          passage[i+6][4] == 1)
      {

        System.out.println(TENUTO);

      }
    }
  }
}
