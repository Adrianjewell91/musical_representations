package analyses;

import util.Passage;
import util.Printer;
import util.Validator;

import static util.Operations.dotProduct;
import static util.Operations.sumMatrices;

public class MatrixMelodyOnly {
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
         * Perform the analysis:
         *
         * Dot product of a melody-factor * analysis, then ascend the pitches.
         */
        int[][] result = dotProduct(melodyFactor, analysis);

        ascendPitches(result);

        /*
         * Print the Analysis.
         */
        Printer.print(result);

        /*
         * Validate the melody + harmony == passage.
         */
//        Printer.print(Passage.harmonyOnly);
        Validator.validate(sumMatrices(result, Passage.harmonyOnly), Passage.passage);
    }

    public static int[][] ascendPitches(int[][] matrix) {
        Integer prev = null;
        int OCTAVE_UP = 5;

        for (int i = 0; i < matrix[0].length; i++)
        {
            for (int j = 0; j < matrix.length; j++)
            {
                if (matrix[j][i] == 1 && prev == null)
                {
                    prev = j;
                }
                else if (matrix[j][i] == 1)
                {
                    int current = j;
                    while (current > prev)
                    {
                        current -= OCTAVE_UP;
                    }

                    if (current < 0)
                    {
                        current += OCTAVE_UP;
                    }

                    matrix[j][i] = 0;
                    matrix[current][i] = 1;

                    prev = current;
                }
            }
        }



        return matrix;
    }
}