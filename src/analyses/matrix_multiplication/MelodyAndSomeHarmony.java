package analyses.matrix_multiplication;

import analyses.util.Passage;
import analyses.util.Printer;

import static analyses.util.Operations.dotProduct;
import static analyses.util.Operations.sumMatrices;

public class MelodyAndSomeHarmony {
    private static int[][] factor =
            new int[20][7];
//    {
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,0,0,0,0,0,0},
//            {0,1,0,0,0,0,0}, // corresponds to a D-flat. it will produce the correct subset of D-flats in the analysis.
//            {0,1,1,0,0,0,1}, // first 1 corresponds to the singly B-flat harmony above the high D-flat. Second 1 will produce the B-flat melody notes, third 1 maps to B-flats in the chord harmony of other D-flats.
//            {0,0,0,0,0,0,0},
//            {0,0,1,0,1,0,0}, // corresponds to the G-flat. it will produce the G-flats in the analysis. first 1 puts G-flats below the B-flats, and second 1 puts melodic G-flats.
//            {0,0,0,0,1,0,0}, // gets the E-flat below the G-flat. (chord harmony)
//            {0,0,0,0,0,0,1}  // corresponds to a D-flat. it will produce the other correct subset of D-flats in the analysis.
//    };

    /*
     * This one is kind of a mess right now.
     */
    public static void main(String[] args) {
        /*
         * Perform the analysis.
         */
        factor         = sumMatrices(Passage.harmonyFactor, Passage.melodyFactor);
        int[][] result = dotProduct(factor, Passage.analysis);

        /*
         * Print the Analysis.
         */
        Printer.print(result);
        System.out.println("valid");
    }
}