package util;

public class Operations {
    /*
     * Matrix multiplication (dot product).
     *
     * Factor and Analysis will be different dimensions.
     *
     *
     * EX:
     *
     *
     * Factor    Analysis    Result
     *
     * 000       0000        0000
     * 000       0000        0000
     * 000    +  0000   =    0000
     * 000                   0000
     */
    public static int[][] dotProduct(int[][] factor, int[][] analysis) {
        int[][] result = new int[factor.length][analysis[0].length];

        // for each row in factor
        for (int i = 0; i < factor.length; i++)
        {
            // for each col in analysis.
            for (int k = 0; k < analysis[0].length; k++)
            {
                int sum = 0;

                // across the row and down the column, sum the products.
                for (int j = 0; j < factor[0].length; j++)
                {

                    /*
                    * Multiply the entries in the row and column and add the sum.
                    *
                    * Practically, sum <= 1 given the nature of the matrices.
                     */
                    sum += ( factor[i][j] * analysis[j][k] );

                }

                result[i][k] = sum;
            }
        }

        return result;
    }

    /*
     * Sum two matrices.
     */
    public static int[][] sumMatrices(int[][] matrix1, int[][] matrix2)
    {
        int[][] result = new int[matrix1.length][matrix1[0].length];

        for (int i = 0; i < matrix1.length; i++)
        {
            for (int j = 0; j < matrix2[j].length; j++)
            {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return result;
    }

    /*
     * Filters a melody from a passage.
     *
     * It uses the XOR operator to return only the harmony from a passage
     */
    public static int[][] filterForHarmony(int[][] melody, int[][] passage) {
        int[][] harmony = new int[passage.length][passage[0].length];

        for (int i = 0; i < harmony.length; i++)
        {
            for (int j = 0; j < harmony[0].length; j++)
            {
                harmony[i][j] = melody[i][j] ^ passage[i][j];
            }

        }

        return harmony;
    }


    /*
     * Filter the pitches for only the melody.
     */
    public static int[][] filterForMelody(int[][] passage) {
        int[][] filtered = new int[passage.length][passage[0].length];

        for (int i = 0; i < filtered.length; i++)
        {
            int[] row = filtered[i];

            for (int j = 0; j < row.length; j++)
            {
                /*
                 * Only collect the pitches on the downbeats.
                 */
                if (j == 0  ||
                        j == 3  ||
                        j == 6  ||
                        j == 9  ||
                        j == 12 ||
                        j == 15 ||
                        j == 18 ||
                        j == 21 ||
                        j == 24 ||
                        j == 27 ||
                        j == 30 ||
                        j == 33 ||
                        j == 36 ||
                        j == 39 ||
                        j == 42 ||
                        j == 45
                ) {
                    row[j] = passage[i][j];

                    //Also remove the notes that aren't the melody.
                    if (i > 1 && (passage[i-1][j] == 1 || passage[i-2][j] == 1))
                    {
                        row[j] = 0;
                    }
                }
            }
        }

        return filtered;
    }
}
