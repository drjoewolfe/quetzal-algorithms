package org.jwolfe.quetzal.algorithms.lc;

public class FindTheMinimumAreaToCoverAllOnesI {
    class Solution {
        public int minimumArea(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int minRow = m;
            int maxRow = 0;
            int minColumn = n;
            int maxColumn = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        minRow = Math.min(minRow, i);
                        maxRow = Math.max(maxRow, i);
                        minColumn = Math.min(minColumn, j);
                        maxColumn = Math.max(maxColumn, j);
                    }
                }
            }

            return (maxRow - minRow + 1) * (maxColumn - minColumn + 1);
        }
    }
}

//    3195. Find the Minimum Area to Cover All Ones I
//    Medium
//    You are given a 2D binary array grid. Find a rectangle with horizontal and vertical sides with the smallest area, such that all the 1's in grid lie inside this rectangle.
//
//    Return the minimum possible area of the rectangle.
//
//
//
//    Example 1:
//
//    Input: grid = [[0,1,0],[1,0,1]]
//
//    Output: 6
//
//    Explanation:
//
//
//
//    The smallest rectangle has a height of 2 and a width of 3, so it has an area of 2 * 3 = 6.
//
//    Example 2:
//
//    Input: grid = [[1,0],[0,0]]
//
//    Output: 1
//
//    Explanation:
//
//
//
//    The smallest rectangle has both height and width 1, so its area is 1 * 1 = 1.
//
//
//
//    Constraints:
//
//    1 <= grid.length, grid[i].length <= 1000
//    grid[i][j] is either 0 or 1.
//    The input is generated such that there is at least one 1 in grid.