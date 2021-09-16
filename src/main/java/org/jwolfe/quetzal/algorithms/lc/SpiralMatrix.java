package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> spiral = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return spiral;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            char direction = 'R';
            int left = 0;
            int right = n - 1;
            int top = 0;
            int bottom = m - 1;

            int i = 0;
            int j = 0;
            while(top <= bottom && left <= right) {
                spiral.add(matrix[i][j]);

                switch(direction) {
                    case 'R': j++;
                        if(j > right) {
                            direction = 'D';
                            j = right;
                            i++;
                            top++;
                        }
                        break;
                    case 'D': i++;
                        if(i > bottom) {
                            direction = 'L';
                            i = bottom;
                            j--;
                            right--;
                        }
                        break;
                    case 'L':  j--;
                        if(j < left) {
                            direction = 'U';
                            j = left;
                            i--;
                            bottom--;
                        }
                        break;
                    case 'U': i--;
                        if(i < top) {
                            direction = 'R';
                            i = top;
                            j++;
                            left++;
                        }
                        break;
                }
            }

            // spiral.add(matrix[i][j]);
            return spiral;
        }
    }

// [[1,2,3],[4,5,6],[7,8,9]]

    class Solution_Correct_2 {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> order = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return order;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            int top = 0;
            int left = 0;
            int bottom = m - 1;
            int right = n - 1;

            int i = 0;
            int j = 0;
            Direction direction = Direction.Right;
            int cells = m * n;

            while(cells > 0) {
                order.add(matrix[i][j]);

                switch(direction) {
                    case Left:
                        j--;
                        if(j < left) {
                            direction = Direction.Up;
                            j = left;
                            i--;
                            bottom--;
                        }

                        break;
                    case Right:
                        j++;
                        if(j > right) {
                            direction = Direction.Down;
                            j = right;
                            i++;
                            top++;
                        }

                        break;
                    case Up:
                        i--;
                        if(i < top) {
                            direction = Direction.Right;
                            i = top;
                            j++;
                            left++;
                        }

                        break;
                    case Down:
                        i++;
                        if(i > bottom) {
                            direction = Direction.Left;
                            i = bottom;
                            j--;
                            right--;
                        }

                        break;
                }

                cells--;
            }

            return order;
        }
    }

    private enum Direction {
        Up,
        Down,
        Left,
        Right
    }
}

//    54. Spiral Matrix
//    Medium
//    Given an m x n matrix, return all elements of the matrix in spiral order.
//
//
//
//    Example 1:
//
//
//    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
//    Output: [1,2,3,6,9,8,7,4,5]
//    Example 2:
//
//
//    Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//    Output: [1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//    Constraints:
//
//    m == matrix.length
//    n == matrix[i].length
//    1 <= m, n <= 10
//    -100 <= matrix[i][j] <= 100