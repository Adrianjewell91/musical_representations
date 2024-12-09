package analyses.transformations;

import analyses.util.Operations;
import analyses.util.Passage;
import analyses.util.Validator;

import static analyses.util.Operations.filterForHarmony;
import static analyses.util.Operations.sumMatrices;

public class HarmonyAndMelody {
    public static void main(String[] args) {
        /*
         * Perform the analysis.
         */
        int[][] melody  = Operations.filterForMelody(Passage.passage);
        int[][] harmony = filterForHarmony(melody, Passage.passage);

        /*
         * Print the Analysis.
         */
//        Printer.print(harmony);

        /*
         * Validate the Analysis.
         */
        Validator.validate(harmony, Passage.harmonyOnly);

        /*
         * Validate that the summing the melody and harmony returns to the passage.
         *
         * Actually, I found a bug by writing this test even though it worked for the other use case.
         */
        Validator.validate(sumMatrices(harmony, melody), Passage.passage);
    }
}