package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumOperationsToMakeAUniValueGrid {
    class Solution {
        public int minOperations(int[][] grid, int x) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[] arr = new int[m * n];
            int index = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    arr[index++] = grid[r][c];
                }
            }

            Arrays.sort(arr);
            int median = arr[arr.length / 2];

            int operations = 0;
            for (int i = 0; i < arr.length; i++) {
                int a = arr[i];
                if (a % x != median % x) {
                    return -1;
                }

                operations += (Math.abs(median - a)) / x;
            }

            return operations;
        }
    }
}

//    2033. Minimum Operations to Make a Uni-Value Grid
//    Medium
//    You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.
//
//    A uni-value grid is a grid where all the elements of it are equal.
//
//    Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[2,4],[6,8]], x = 2
//    Output: 4
//    Explanation: We can make every element equal to 4 by doing the following:
//    - Add x to 2 once.
//    - Subtract x from 6 once.
//    - Subtract x from 8 twice.
//    A total of 4 operations were used.
//    Example 2:
//
//
//    Input: grid = [[1,5],[2,3]], x = 1
//    Output: 5
//    Explanation: We can make every element equal to 3.
//    Example 3:
//
//
//    Input: grid = [[1,2],[3,4]], x = 2
//    Output: -1
//    Explanation: It is impossible to make every element equal.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 105
//    1 <= m * n <= 105
//    1 <= x, grid[i][j] <= 104