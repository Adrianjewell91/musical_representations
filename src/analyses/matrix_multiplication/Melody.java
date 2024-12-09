package analyses.matrix_multiplication;

import analyses.util.Operations;
import analyses.util.Passage;
import analyses.util.Validator;

import static analyses.util.Operations.dotProduct;
import static analyses.util.Operations.sumMatrices;

public class Melody {
    public static void main(String[] args) {
        /*
         * Perform the analysis:
         *
         * Dot product of a melody-factor * analysis, then ascend the pitches.
         */
        int[][] result = dotProduct(Passage.melodyFactor, Passage.analysis);

        Operations.ascendPitches(result);

        /*
         * Print the Analysis.
         */
//        Printer.print(result);

        /*
         * Validate the melody + harmony == passage.
         */
//        Printer.print(Passage.harmonyOnly);
        Validator.validate(sumMatrices(result, Passage.harmonyOnly), Passage.passage);
    }

}