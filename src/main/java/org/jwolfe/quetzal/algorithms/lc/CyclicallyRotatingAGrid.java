package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class CyclicallyRotatingAGrid {
    class Solution {
        public int[][] rotateGrid(int[][] grid, int k) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return grid;
            }

            int m = grid.length;
            int n = grid[0].length;

            int numLayers = Math.min(m, n) / 2;
            for (int layer = 0; layer < numLayers; layer++) {
                int top = layer;
                int bottom = m - layer - 1;
                int left = layer;
                int right = n - layer - 1;

                List<Integer> elements = new ArrayList<>();

                // Top Row (left to right)
                for (int c = left; c <= right; c++) {
                    elements.add(grid[top][c]);
                }

                // Right Column (top to down)
                for (int r = top + 1; r <= bottom - 1; r++) {
                    elements.add(grid[r][right]);
                }

                // Bottom Row (right to left)
                for (int c = right; c >= left; c--) {
                    elements.add(grid[bottom][c]);
                }

                // Left Column (bottom to top)
                for (int r = bottom - 1; r >= top + 1; r--) {
                    elements.add(grid[r][left]);
                }

                int count = elements.size();
                int numRotations = k % count;
                rotate(elements, numRotations);

                int index = 0;

                // Top Row (left to right)
                for (int c = left; c <= right; c++) {
                    grid[top][c] = elements.get(index++);
                }

                // Right Column (top to down)
                for (int r = top + 1; r <= bottom - 1; r++) {
                    grid[r][right] = elements.get(index++);
                }

                // Bottom Row (right to left)
                for (int c = right; c >= left; c--) {
                    grid[bottom][c] = elements.get(index++);
                }

                // Left Column (bottom to top)
                for (int r = bottom - 1; r >= top + 1; r--) {
                    grid[r][left] = elements.get(index++);
                }
            }

            return grid;
        }

        private void rotate(List<Integer> elements, int k) {
            int n = elements.size();

            reverse(elements, 0, k - 1);
            reverse(elements, k, n - 1);
            reverse(elements, 0, n - 1);
        }

        private void reverse(List<Integer> elements, int left, int right) {
            while (left < right) {
                swap(elements, left++, right--);
            }
        }

        private void swap(List<Integer> elements, int left, int right) {
            int temp = elements.get(left);
            elements.set(left, elements.get(right));
            elements.set(right, temp);
        }
    }
}

//    1914. Cyclically Rotating a Grid
//    Medium
//    You are given an m x n integer matrix grid​​​, where m and n are both even integers, and an integer k.
//
//    The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:
//
//
//
//    A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:
//
//
//    Return the matrix after applying k cyclic rotations to it.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[40,10],[30,20]], k = 1
//    Output: [[10,20],[40,30]]
//    Explanation: The figures above represent the grid at every state.
//    Example 2:
//
//
//    Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
//    Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
//    Explanation: The figures above represent the grid at every state.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    2 <= m, n <= 50
//    Both m and n are even integers.
//    1 <= grid[i][j] <= 5000
//    1 <= k <= 109