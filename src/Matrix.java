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
            {0,1,0,0,0,0,0}, // D-flat
            {0,0,1,0,0,0,0}, // B-flat
            {0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0}, // G-flat
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1}  // D-flat
    };

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
            {0,0,0,0,0,0,0}, // The harmony factor is not a 1-1 direct mapping with notes, in that way that melody factor is so.
            {0,1,0,0,0,0,1},
            {0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0},
            {0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0}
    };

    private static void sumMatrices()
    {
        for (int i = 0; i < harmonyFactor.length; i++) {
            for (int j = 0; j < melodyFactor[j].length; j++) {
                factor[i][j] = harmonyFactor[i][j] + melodyFactor[i][j];
            }
        }

        return;
    }

    private static final int[][] factor =
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

        //Sum the melody factor and the harmonic factor.
        sumMatrices();

        //Perform matrix multiplication.
        int[][] result = analyze();

        /*
         * Print the Analysis.
         */
        Printer.print(result);
    }

    /*
     * Matrix multiplication (dot product)
     */
    private static int[][] analyze() {
        int[][] result = new int[20][48];

        // for each row in factor
        for (int i = 0; i < factor.length; i++)
        {
            // for each col in analysis.
            for (int k = 0; k < analysis[0].length; k++)
            {
                int sum = 0;
                for (int j = 0; j < factor[0].length; j++)
                {

                    sum += ( factor[i][j] * analysis[j][k] );

                }

                result[i][k] = sum;
            }
        }

        return result;
    }
}