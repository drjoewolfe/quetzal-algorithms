package org.jwolfe.quetzal.algorithms.lc;

public class DiagonalTraverse {
    class Solution {
        public int[] findDiagonalOrder(int[][] mat) {
            if (mat == null || mat.length == 0 || mat[0].length == 0) {
                return new int[0];
            }

            int m = mat.length;
            int n = mat[0].length;

            int size = m * n;
            int[] traversal = new int[size];

            int index = 0;
            int row = 0;
            int col = 0;

            // direction - 0 {right}, 1 {left}
            int direction = 0;

            while (index < size) {
                System.out.println(index + " : " + row + ", " + col);
                traversal[index++] = mat[row][col];

                if (direction == 0) {
                    if (row == 0 && col == n - 1) {
                        direction = 1;
                        row++;
                    } else if (row == 0) {
                        direction = 1;
                        col++;
                    } else if (col == n - 1) {
                        direction = 1;
                        row++;
                    } else {
                        row--;
                        col++;
                    }
                } else {
                    if (row == m - 1 && col == 0) {
                        direction = 0;
                        col++;
                    } else if (row == m - 1) {
                        direction = 0;
                        col++;
                    } else if (col == 0) {
                        direction = 0;
                        row++;
                    } else {
                        row++;
                        col--;
                    }
                }
            }

            return traversal;
        }
    }
}

//    498. Diagonal Traverse
//    Medium
//    Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
//
//
//
//    Example 1:
//
//
//    Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
//    Output: [1,2,4,7,5,3,6,8,9]
//    Example 2:
//
//    Input: mat = [[1,2],[3,4]]
//    Output: [1,2,3,4]
//
//
//    Constraints:
//
//    m == mat.length
//    n == mat[i].length
//    1 <= m, n <= 104
//    1 <= m * n <= 104
//    -105 <= mat[i][j] <= 105