package analyses;

import analyses.matrix_multiplication.Melody;
import analyses.matrix_multiplication.MelodyAndFleshOut;
import analyses.matrix_multiplication.MelodyAndSomeHarmony;
import analyses.transformations.HardCoded;
import analyses.transformations.HarmonyAndMelody;

public class RunAll {
    public static void main(String[] args) {
        Melody.main(new String[] {});
        MelodyAndFleshOut.main(new String[] {});
        MelodyAndSomeHarmony.main(new String[] {});
        HardCoded.main(new String[] {});
        HarmonyAndMelody.main(new String[] {});
    }
}
