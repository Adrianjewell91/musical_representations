package analyses;

import util.Printer;

import static util.Operations.dotProduct;
import static util.Operations.sumMatrices;

public class Matrix {
    private static final int[][] melodyFactor = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0}, // D-flat.
            {0,0,1,0,0,0,0}, // B-flat.
            {0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0}, // G-flat.
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1}  // D-flat.
    };

    /*
     *  The harmony factor is not interpretable on the basis of direct 1-1 mappings from pitch to index (in the way that the melody factor is).
     */
    private static final int[][] harmonyFactor = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,1,0,0,0,0,1}, // B-flat chord tones.
            /* ^=High    ^=Lower chord tones. */
            {0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0}, // G-flat chord tone.
            {0,0,0,0,1,0,0}, // E-flat chord tone.
            {0,0,0,0,0,0,0}
    };

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

    private static final int[][] analysis = {
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},

        {1,0,0,0,0,0,0,0,0,1,0,0, 0,0,0,0,0,0,1,0,0,0,0,0, 0,0,0,1,0,0,0,0,0,1,0,0, 0,0,0,1,0,0,0,0,0,1,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,1,0,0,0,0,0, 0,0,0,1,0,0,0,0,0,0,0,0, 1,0,0,0,0,0,1,0,0,0,0,0, 1,0,0,0,0,0,1,0,0,0,0,0},

        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,1,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,1,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0}
    };

    public static void main(String[] args) {
        /*
         * Perform the analysis.
         */
        factor         = sumMatrices(harmonyFactor, melodyFactor);
        int[][] result = dotProduct(factor, analysis);

        /*
         * Print the Analysis.
         */
        Printer.print(result);
    }
}