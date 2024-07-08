package util;

public class Printer {
    public static void print(int[][] result) {
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
    }
}
