import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Arrays;

/*

Example 1
[
    # 0    1    2    3    4    5    6    7    8
    ['X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'], # 0
    ['X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'], # 1
    [' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '], # 2
    [' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '], # 3
    [' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'], # 4
    [' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X']  # 5
]

' ' represents an open road that you can travel over in any direction (up, down, left, or right).
'X' represents an blocked road that you cannot travel through.
'D' represents a DashMart.

# list of pairs [row, col]
locations = [
    [200, 200],
    [1, 4],
    [0, 3],
    [5, 8],
    [1, 8],
    [5, 5]
]

answer = [-1, 2, 0, -1, 6, 9]

Provided:
- city: char[][]
- locations: int[][2]

Return:
- answer: int[]

Return a list of the distances from a given point to its closest DashMart.
Expected Answer: In this case, you should return [-1, 2, 0, -1, 6, 9].

*/




class Solution {
    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        char[][] city = {
                // 0    1    2    3    4    5    6    7    8
                { 'X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X' }, // 0
                { 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X' }, // 1
                { ' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' ' }, // 2
                { ' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' ' }, // 3
                { ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X' }, // 4
                { ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X' }  // 5
        };

        int[][] locations = {
                { 200, 200 },
                { 1, 4 },
                { 0, 3 },
                { 5, 8 },
                { 1, 8 },
                { 5, 5 }
        };

        int[] expectedAnswer = { -1, 2, 0, -1, 6, 9 };
        // int[] result = { -1, 2, 0, -1, 6, 9 }; // Replace with function call
        int[] result = getResult(city, locations); // Replace with function call

        System.out.println(
                Arrays.equals(expectedAnswer, result)
                        ? "Passed test 1" : "X Failed test 1"
        );
    }

    public static void test2() {
        char[][] city = {
                // 0    1    2
                { 'D', 'X', 'X' }, // 0
                { 'D', 'D', 'X' }  // 1
        };

        int[][] locations = {
                { 0, 2 },
                { 1, 1 },
                { 1, 2 },
        };

        int[] expectedAnswer = { -1, 0, 1 };
        // int[] result = { -1, 0, 1 }; // Replace with function call
        int[] result = getResult(city, locations); // Replace with function call

        System.out.println(
                Arrays.equals(expectedAnswer, result)
                        ? "Passed test 2" : "X Failed test 2"
        );
    }

    public static int[] getResult(char[][] city, int[][] locations)
    {
        // prepare the map
        int[][] cityMap = prepareMap(city);

        // generate the result.
        int[] result = processLocations(cityMap, locations);


        return result;
    }

    public static int[][] prepareMap(char[][] city)
    {
        int[][] cityMap = new int[city.length][city[0].length];

        // Get dashmart locations.
        List<List<Integer>> marts = new ArrayList<List<Integer>>();

        for (int i = 0; i < city.length; i++)
        {
            for (int j = 0; j < city[i].length; j++)
            {
                char place = city[i][j];

                if (place == 'D')
                {
                    List<Integer> mart = new ArrayList<>();
                    mart.add(i);
                    mart.add(j);
                    marts.add(mart);

                    cityMap[i][j] = 0;
                }
                if (place == 'X')
                {
                    cityMap[i][j] = -1;
                }
                else
                {
                    cityMap[i][j] = Integer.MAX_VALUE;
                }

            }
        }


        // Perform the bfs

        // Set the mart location = 0
        // bfs
        // set number if not set, else set the min number.
        for (List<Integer> mart : marts)
        {
            boolean[][] traversed = new boolean[city.length][city[0].length];
            Queue<List<Integer>> queue = new LinkedList<List<Integer>>();

            // add starting distance.
            mart.add(0);

            queue.add(mart);

            while (queue.size() > 0)
            {
                List<Integer> currentSpace = queue.poll();

                int x = currentSpace.get(0);
                int y = currentSpace.get(1);
                int distance = currentSpace.get(2);

                int newDistance = Math.min(distance, cityMap[x][y]);
                cityMap[x][y] = newDistance;

                traversed[x][y] = true;

                int[][] directions = {
                        {1,0},{-1,0},{0,1},{0,-1}
                };

                for (int[] d : directions)
                {
                    int x1 = d[0] + x;
                    int y1 = d[1] + y;

                     if (    x1 >= 0 &&
                             y1 >= 0 &&
                             x1 < city.length &&
                             y1 < city[0].length &&
                             traversed[x1][y1] == false
                     )
                     {
                            char nextSpace = city[x1][y1];

                            if (nextSpace == ' ')
                            {
                                queue.add(List.of(x1, y1, newDistance+1));
                            }
                            else if (nextSpace == 'X' && cityMap[x1][y1] == -1)
                            {
                                cityMap[x1][y1] = newDistance + 1;
                            }
                            else if (nextSpace == 'X' && cityMap[x1][y1] > -1)
                            {
                                cityMap[x1][y1] = Math.min(cityMap[x1][y1], newDistance + 1);
                            }
                     }
                }

            }
        }

        return cityMap;
    }

    public static int[] processLocations(int[][] city, int[][] locations)
    {
        int[] result = new int[locations.length];

        for (int i = 0; i < locations.length; i++)
        {
            int[] location = locations[i];

            //Check bounds
            if (location[0] < city.length &&
                    location[1] < city[0].length
            )
            {
                int value = city[location[0]][location[1]];
                if (value == Integer.MAX_VALUE)
                {
                    result[i] = -1;
                }
                else
                {
                    result[i] = value;
                }
            }
            else
            {
                result[i] = -1;
            }

        }

        return result;
    }

}
