package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinimumCostPathWithTeleportations {
    class Solution {
        public int minCost(int[][] grid, int k) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            List<int[]> points = new ArrayList<>();
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int[] point = new int[]{r, c};
                    points.add(point);
                }
            }

            points.sort(Comparator.comparingInt(p -> {
                int x = p[0];
                int y = p[1];
                return grid[x][y];
            }));

            int[][] costs = new int[m][n];
            for (int[] row : costs) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            for (int t = 0; t <= k; t++) {
                int minCost = Integer.MAX_VALUE;
                for (int left = 0, right = 0; right < points.size(); right++) {
                    int[] point = points.get(right);
                    int r = point[0];
                    int c = point[1];

                    minCost = Math.min(minCost, costs[r][c]);

                    if (right + 1 < points.size()) {
                        int[] nextPoint = points.get(right + 1);
                        int nr = nextPoint[0];
                        int nc = nextPoint[1];

                        if (grid[r][c] == grid[nr][nc]) {
                            continue;
                        }
                    }

                    while (left <= right) {
                        int[] leftPoint = points.get(left);
                        int lr = leftPoint[0];
                        int lc = leftPoint[1];

                        costs[lr][lc] = minCost;
                        left++;
                    }
                }

                for (int r = m - 1; r >= 0; r--) {
                    for (int c = n - 1; c >= 0; c--) {
                        if (r == m - 1 && c == n - 1) {
                            costs[r][c] = 0;
                            continue;
                        }

                        if (r != m - 1) {
                            costs[r][c] = Math.min(
                                    costs[r][c],
                                    costs[r + 1][c] + grid[r + 1][c]);
                        }

                        if (c != n - 1) {
                            costs[r][c] = Math.min(
                                    costs[r][c],
                                    costs[r][c + 1] + grid[r][c + 1]);
                        }
                    }
                }
            }

            return costs[0][0];
        }
    }
}

//    3651. Minimum Cost Path with Teleportations
//    Hard
//    You are given a m x n 2D integer array grid and an integer k. You start at the top-left cell (0, 0) and your goal is to reach the bottom‐right cell (m - 1, n - 1).
//
//    There are two types of moves available:
//
//    Normal move: You can move right or down from your current cell (i, j), i.e. you can move to (i, j + 1) (right) or (i + 1, j) (down). The cost is the value of the destination cell.
//
//    Teleportation: You can teleport from any cell (i, j), to any cell (x, y) such that grid[x][y] <= grid[i][j]; the cost of this move is 0. You may teleport at most k times.
//
//    Return the minimum total cost to reach cell (m - 1, n - 1) from (0, 0).
//
//
//
//    Example 1:
//
//    Input: grid = [[1,3,3],[2,5,4],[4,3,5]], k = 2
//
//    Output: 7
//
//    Explanation:
//
//    Initially we are at (0, 0) and cost is 0.
//
//    Current Position	Move	New Position	Total Cost
//    (0, 0)	Move Down	(1, 0)	0 + 2 = 2
//    (1, 0)	Move Right	(1, 1)	2 + 5 = 7
//    (1, 1)	Teleport to (2, 2)	(2, 2)	7 + 0 = 7
//    The minimum cost to reach bottom-right cell is 7.
//
//    Example 2:
//
//    Input: grid = [[1,2],[2,3],[3,4]], k = 1
//
//    Output: 9
//
//    Explanation:
//
//    Initially we are at (0, 0) and cost is 0.
//
//    Current Position	Move	New Position	Total Cost
//    (0, 0)	Move Down	(1, 0)	0 + 2 = 2
//    (1, 0)	Move Right	(1, 1)	2 + 3 = 5
//    (1, 1)	Move Down	(2, 1)	5 + 4 = 9
//    The minimum cost to reach bottom-right cell is 9.
//
//
//
//    Constraints:
//
//    2 <= m, n <= 80
//    m == grid.length
//    n == grid[i].length
//    0 <= grid[i][j] <= 104
//    0 <= k <= 10