package analyses.transformations;

import util.Operations;
import util.Passage;
import util.Printer;
import util.Validator;

import static util.Operations.filterForHarmony;
import static util.Operations.sumMatrices;

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