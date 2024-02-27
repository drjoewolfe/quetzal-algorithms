package org.jwolfe.quetzal.algorithms.lc;

public class FindTheWidthOfColumnsOfAGrid {
    class Solution {
        public int[] findColumnWidth(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return new int[0];
            }

            int m = grid.length;
            int n = grid[0].length;

            int[] results = new int[n];

            for(int j = 0; j < n; j++) {
                for(int i = 0; i < m; i++) {
                    int val = grid[i][j];
                    int length = getLength(val);

                    results[j] = Math.max(results[j], length);
                }
            }

            return results;
        }

        private int getLength(int val) {
            if(val == 0) {
                return 1;
            }

            int length = 0;

            if(val < 0) {
                val *= -1;
                length++;
            }

            while(val > 0) {
                length++;
                val /= 10;
            }

            return length;
        }
    }

// [[1],[22],[333]]
// [[-15,1,3],[15,7,12],[5,6,-2]]
// [[0]]
}

//    2639. Find the Width of Columns of a Grid
//    Easy
//    You are given a 0-indexed m x n integer matrix grid. The width of a column is the maximum length of its integers.
//
//    For example, if grid = [[-10], [3], [12]], the width of the only column is 3 since -10 is of length 3.
//    Return an integer array ans of size n where ans[i] is the width of the ith column.
//
//    The length of an integer x with len digits is equal to len if x is non-negative, and len + 1 otherwise.
//
//
//
//    Example 1:
//
//    Input: grid = [[1],[22],[333]]
//    Output: [3]
//    Explanation: In the 0th column, 333 is of length 3.
//    Example 2:
//
//    Input: grid = [[-15,1,3],[15,7,12],[5,6,-2]]
//    Output: [3,1,2]
//    Explanation:
//    In the 0th column, only -15 is of length 3.
//    In the 1st column, all integers are of length 1.
//    In the 2nd column, both 12 and -2 are of length 2.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 100
//    -109 <= grid[r][c] <= 109