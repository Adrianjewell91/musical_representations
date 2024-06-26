public class Matrix {
    private static final int[][] factor = {
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
            {0,1,0,0,0,0,0}, // corresponds to a b flat. it will produce the correct subset of d flats in the analysis.
            {0,0,1,0,0,0,0}, // corresponds to a b flat. it will produce the b flats in the analysis.
            {0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0}, // corresponds to the g flat. it will produce the g flats in the analysis.
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1}  // corresponds to a d flat. it will produce the other correct subset of d flats in the analysis.
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

    private static final int[][] passage = {
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,1,0,0,0,0,0,1,0,0, 0,0,0,1,0,0,0,0,0,1,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 1,0,0,1,0,0,1,0,0,1,0,0, 1,0,0,1,0,0,1,0,0,1,0,0},

        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 1,0,0,0,1,0,1,0,0,0,1,0, 1,0,0,0,1,0,1,0,0,0,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,1,0,0, 0,1,0,0,0,1,0,1,0,0,0,1, 0,1,0,0,0,1,0,1,0,0,0,1},

        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,1,0,0,1,0,0, 0,0,1,0,0,0,0,0,1,0,0,0, 0,0,1,0,0,0,0,0,1,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,1,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,1,0,0,1,0,0,0,0,1, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},

        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,1,0,0,0,1,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 1,0,0,0,1,0,0,0,1,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},

        {0,0,0,0,0,0,0,0,0,1,0,0, 1,0,0,0,0,1,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,1,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,1,0,0,1,0,0, 0,0,1,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},

        {0,0,0,0,0,0,1,0,0,0,1,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,1,0,0,0,1,0,0,0,1, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},

        {1,0,0,1,0,0,0,0,1,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},

        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0, 0,0,0,0,0,0,0,0,0,0,0,0},
    };

    public static void main(String[] args) {
        /*
         * Perform the analysis.
         */
        int[][] result = new int[20][48];


        /*
         * Matrix multiplication (dot product)
         */
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

        /*
         * Print the Analysis. There is formatting logic to print according to groups of 2-3 (to match the black keys).
         */
        int space = 0;
        int spaceExtra = 0;


        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[i].length; j++)
            {
                System.out.print(result[i][j] + "");

                if ((j + 1) % 12 == 0)
                {
                    System.out.print(' ');
                }
            }

            System.out.println();

            space++;
            if (space + spaceExtra == 3)
            {
                System.out.println();
                space = 0;

                if (spaceExtra == 1)
                {
                    spaceExtra = 0;
                }
                else
                {
                    spaceExtra = 1;
                }
            }
        }

        /*
         * Validate the Analysis.
         */
        for (int i = 0; i < result.length; i++)
        {
            for (int j = 0; j < result[i].length; j++)
            {
                if (result[i][j] != analysis[i][j])
                {
                    System.out.println("invalid");
                    return;
                }
            }
        }

        System.out.println("valid");
    }
}
