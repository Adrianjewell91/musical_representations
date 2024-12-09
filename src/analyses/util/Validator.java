package analyses.util;

public class Validator {
    public static void validate(int[][] result, int[][] expected) {
        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[i].length; j++)
            {
                if (result[i][j] != expected[i][j])
                {
                    System.out.println("invalid");
                    System.out.println(i);
                    System.out.println(j);
                    return;
                }
            }
        }

        System.out.println("valid");
    }
}
