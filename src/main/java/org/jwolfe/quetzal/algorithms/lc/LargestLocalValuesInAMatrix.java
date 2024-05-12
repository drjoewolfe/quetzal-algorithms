package org.jwolfe.quetzal.algorithms.lc;

public class LargestLocalValuesInAMatrix {
    class Solution {
        private int[] dimensions = new int[]{-1, 0, 1};

        public int[][] largestLocal(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return new int[0][0];
            }

            int n = grid.length;
            int[][] results = new int[n - 2][n - 2];
            for (int i = 0; i < n - 2; i++) {
                for (int j = 0; j < n - 2; j++) {
                    results[i][j] = findMax(grid, i + 1, j + 1);
                }
            }

            return results;
        }

        private int findMax(int[][] grid, int row, int col) {
            int max = Integer.MIN_VALUE;

            for (int rd : dimensions) {
                for (int cd : dimensions) {
                    int i = row + rd;
                    int j = col + cd;

                    max = Math.max(max, grid[i][j]);
                }
            }

            return max;
        }
    }

    class Solution_Correct_1 {
        public int[][] largestLocal(int[][] grid) {
            if (grid == null || grid.length == 0 || grid.length != grid[0].length) {
                return new int[0][0];
            }

            int n = grid.length;
            int[][] results = new int[n - 2][n - 2];

            for (int i = 0; i < n - 2; i++) {
                for (int j = 0; j < n - 2; j++) {

                    int localMax = Integer.MIN_VALUE;
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            localMax = Math.max(localMax, grid[i + k][j + l]);
                        }
                    }

                    results[i][j] = localMax;
                }
            }

            return results;
        }
    }
}

//    2373. Largest Local Values in a Matrix
//    Easy
//    You are given an n x n integer matrix grid.
//
//    Generate an integer matrix maxLocal of size (n - 2) x (n - 2) such that:
//
//    maxLocal[i][j] is equal to the largest value of the 3 x 3 matrix in grid centered around row i + 1 and column j + 1.
//    In other words, we want to find the largest value in every contiguous 3 x 3 matrix in grid.
//
//    Return the generated matrix.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
//    Output: [[9,9],[8,6]]
//    Explanation: The diagram above shows the original matrix and the generated matrix.
//    Notice that each value in the generated matrix corresponds to the largest value of a contiguous 3 x 3 matrix in grid.
//    Example 2:
//
//
//    Input: grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
//    Output: [[2,2,2],[2,2,2],[2,2,2]]
//    Explanation: Notice that the 2 is contained within every contiguous 3 x 3 matrix in grid.
//
//
//    Constraints:
//
//    n == grid.length == grid[i].length
//    3 <= n <= 100
//    1 <= grid[i][j] <= 100