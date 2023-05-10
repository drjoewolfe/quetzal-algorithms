package org.jwolfe.quetzal.algorithms.lc;

public class SpiralMatrixII {
    class Solution {
        public int[][] generateMatrix(int n) {
            if(n < 1) {
                return new int[0][0];
            }

            int[][] matrix = new int[n][n];
            int totalLayers = (n + 1) / 2;
            int val = 1;

            for(int layer = 0; layer < totalLayers; layer++) {
                // top-left to top-right
                int row = layer;
                int col = layer;

                for(; col < n - layer; col++) {
                    matrix[row][col] = val++;
                }

                // top-right to bottom-right
                for(row++, col--; row < n - layer; row++) {
                    matrix[row][col] = val++;
                }

                // bottom-right to bottom-left
                for(row--, col--; col >= layer; col--) {
                    matrix[row][col] = val++;
                }

                // bottom-left to top-left
                for(row--, col++; row > layer; row--) {
                    matrix[row][col] = val++;
                }
            }

            return matrix;
        }

        private void print(int[][] mat) {
            for(int[] row : mat) {
                for(int cell : row) {
                    System.out.print(cell + " " );
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int[][] generateMatrix(int n) {
            if(n < 1) {
                return new int[0][0];
            }

            int[][] matrix = new int[n][n];
            int r = 0;
            int c = 0;

            int left = 0;
            int right = n - 1;
            int top = 0;
            int bottom = n - 1;
            int direction = 0;

            for(int a = 1; a <= n * n; a++) {
                matrix[r][c] = a;

                switch(direction) {
                    case 0:
                        if(c == right) {
                            top++;
                            direction = 1;
                            r++;
                        } else {
                            c++;
                        }
                        break;
                    case 1:
                        if(r == bottom) {
                            right--;
                            direction = 2;
                            c--;
                        } else {
                            r++;
                        }
                        break;
                    case 2:
                        if(c == left) {
                            bottom--;
                            direction = 3;
                            r--;
                        } else {
                            c--;
                        }
                        break;
                    case 3:
                        if(r == top) {
                            left++;
                            direction = 0;
                            c++;
                        } else {
                            r--;
                        }
                        break;
                }
            }

            return matrix;
        }
    }
}

//    59. Spiral Matrix II
//    Medium
//    Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
//
//
//
//    Example 1:
//
//
//    Input: n = 3
//    Output: [[1,2,3],[8,9,4],[7,6,5]]
//    Example 2:
//
//    Input: n = 1
//    Output: [[1]]
//
//
//    Constraints:
//
//    1 <= n <= 20