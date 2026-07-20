package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class Shift2DGrid {
    class Solution {
        public List<List<Integer>> shiftGrid(int[][] grid, int k) {
            List<List<Integer>> gridList = new ArrayList<>();
            if (grid == null || grid.length == 0 || grid[0].length == 0 || k < 0) {
                return gridList;
            }

            int m = grid.length;
            int n = grid[0].length;

            for (int r = 0; r < m; r++) {
                List<Integer> list = new ArrayList<>();
                gridList.add(list);

                for (int c = 0; c < n; c++) {
                    list.add(grid[r][c]);
                }
            }

            int size = m * n;
            k %= size;

            reverse(gridList, m, n, 0, size - 1);
            reverse(gridList, m, n, 0, k - 1);
            reverse(gridList, m, n, k, size - 1);

            return gridList;
        }

        private void reverse(List<List<Integer>> grid, int m, int n, int i, int j) {
            while (i < j) {
                int temp = grid.get(i / n).get(i % n);
                grid.get(i / n).set(i % n, grid.get(j / n).get(j % n));
                grid.get(j / n).set(j % n, temp);

                i++;
                j--;
            }
        }
    }
}

//    1260. Shift 2D Grid
//    Easy
//    Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
//
//    In one shift operation:
//
//    Element at grid[i][j] moves to grid[i][j + 1].
//    Element at grid[i][n - 1] moves to grid[i + 1][0].
//    Element at grid[m - 1][n - 1] moves to grid[0][0].
//    Return the 2D grid after applying shift operation k times.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
//    Output: [[9,1,2],[3,4,5],[6,7,8]]
//    Example 2:
//
//
//    Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
//    Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
//    Example 3:
//
//    Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
//    Output: [[1,2,3],[4,5,6],[7,8,9]]
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m <= 50
//    1 <= n <= 50
//    -1000 <= grid[i][j] <= 1000
//    0 <= k <= 100