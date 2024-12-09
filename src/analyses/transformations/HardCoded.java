package analyses.transformations;

import analyses.util.Operations;
import analyses.util.Passage;
import analyses.util.Validator;

public class HardCoded {
    public static void main(String[] args) {
        /*
         * Perform the analysis.
         */
        int[][] result = analyze(Passage.passage);
        /*
         * Print the Analysis.
         */
//        Printer.print(result);

        /*
         * Validate the Analysis.
         */
        Validator.validate(result, Passage.analysisFullDimensions);
    }

    private static int[][] analyze(int[][] passage) {
        return
                transform(
                        Operations.filterForMelody(
                                passage
                        )
                )
                ;
    }

    /*
     * Transform the notes into a singable range.
     *
     * It depends on first calling filter().
     *
     * This logic of getting the new range makes no sense at the moment.
     */
    private static int[][] transform(int[][] passage) {
        int MODULO = 5;
        int RANGE_MODIFIER = passage.length - 1;
        int[][] transformed = new int[passage.length][passage[0].length];

        for (int i = 0; i < transformed.length; i++)
        {
            int[] row = transformed[i];

            for (int j = 0; j < row.length; j++)
            {
                // move each note down until it can't go anymore.
                if (passage[i][j] == 1) {
                    transformed[RANGE_MODIFIER - ((RANGE_MODIFIER-i) % MODULO)][j] = passage[i][j];
                }
            }
        }

        /*
         * Hard Coded,
         *
         * Post-Processing for One Pitch in the middle of the phrase: The High D.
         */
        int HIGH_D_TIMESTAMP = 12;

        int TURN_OFF = 19;
        transformed[TURN_OFF][HIGH_D_TIMESTAMP] = 0;

        int TURN_ON = 14;
        transformed[TURN_ON][HIGH_D_TIMESTAMP] = 1;

        return transformed;
    }

}