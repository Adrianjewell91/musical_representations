package analyses.matrix_multiplication;

import util.Operations;
import util.Passage;
import util.Printer;
import util.Validator;

import static util.Operations.*;

public class MelodyAndFleshOut {
    public static void main(String[] args) {
        /*
         * Perform the analysis:
         *
         * Dot product of a melody-factor * analysis, then ascend the pitches.
         */
        int[][] result = dotProduct(Passage.melodyFactor, Passage.analysis);

        Operations.ascendPitches(result);

        //Now for each pitch, add the harmony back in.
        //Add three notes, according to a method
        int[][] matrix = result;
            // First note goes below.
                // If the note is a B flat it goes 2 down.
            // Second note goes on next beat directly one down
            // Third note goes on next beat and same here.
        for (int i = 0; i < matrix[0].length; i+=3)
        {
            for (int j = 0; j < matrix.length; j++)
            {
                if (matrix[j][i] == 1)
                {
                    //Add pitches:
                    int chordToneIdx = ( j % 5 == 0 ? 2 : 1 );
                    matrix[j + chordToneIdx][i] = 1;
                    matrix[j + chordToneIdx + 1][i+1] = 1;
                    matrix[j + chordToneIdx + 2][i+2] = 1;

                    // Break; because we don't want to go further.
                    break;
                }
            }
        }

        result = AndOperator(result, Passage.passage);
        /*
         * Print the Analysis.
         */
//        Printer.print(result);

        /*
         * Validate the melody + harmony == passage.
         */
//        Printer.print(Passage.harmonyOnly);
        Validator.validate(result, Passage.passage);
    }
}