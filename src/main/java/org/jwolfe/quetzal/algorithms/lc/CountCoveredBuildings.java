package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class CountCoveredBuildings {
    class Solution {
        public int countCoveredBuildings(int n, int[][] buildings) {
            if (n < 1 || buildings == null || buildings.length < 5) {
                return 0;
            }

            int count = 0;

            int[] minYForX = new int[n + 1];
            int[] maxYForX = new int[n + 1];
            int[] minXForY = new int[n + 1];
            int[] maxXForY = new int[n + 1];

            Arrays.fill(minYForX, n + 1);
            Arrays.fill(minXForY, n + 1);

            for (int[] building : buildings) {
                int x = building[0];
                int y = building[1];

                minYForX[x] = Math.min(minYForX[x], y);
                maxYForX[x] = Math.max(maxYForX[x], y);

                minXForY[y] = Math.min(minXForY[y], x);
                maxXForY[y] = Math.max(maxXForY[y], x);
            }

            for (int[] building : buildings) {
                int x = building[0];
                int y = building[1];

                if (
                        x > minXForY[y] &&
                                x < maxXForY[y] &&
                                y > minYForX[x] &&
                                y < maxYForX[x]) {
                    count++;
                }
            }

            return count;
        }
    }
}

//    3531. Count Covered Buildings
//    Medium
//    You are given a positive integer n, representing an n x n city. You are also given a 2D grid buildings, where buildings[i] = [x, y] denotes a unique building located at coordinates [x, y].
//
//    A building is covered if there is at least one building in all four directions: left, right, above, and below.
//
//    Return the number of covered buildings.
//
//
//
//    Example 1:
//
//
//
//    Input: n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]
//
//    Output: 1
//
//    Explanation:
//
//    Only building [2,2] is covered as it has at least one building:
//    above ([1,2])
//    below ([3,2])
//    left ([2,1])
//    right ([2,3])
//    Thus, the count of covered buildings is 1.
//    Example 2:
//
//
//
//    Input: n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]
//
//    Output: 0
//
//    Explanation:
//
//    No building has at least one building in all four directions.
//    Example 3:
//
//
//
//    Input: n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]
//
//    Output: 1
//
//    Explanation:
//
//    Only building [3,3] is covered as it has at least one building:
//    above ([1,3])
//    below ([5,3])
//    left ([3,2])
//    right ([3,5])
//    Thus, the count of covered buildings is 1.
//
//
//    Constraints:
//
//    2 <= n <= 105
//    1 <= buildings.length <= 105
//    buildings[i] = [x, y]
//    1 <= x, y <= n
//    All coordinates of buildings are unique.