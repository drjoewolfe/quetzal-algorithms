package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ShortestPathInAGridWithObstaclesElimination {
    class Solution {
        public int shortestPath(int[][] grid, int k) {
            if(grid == null || grid.length == 0 || grid[0].length == 0 || k < 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            Ordinate start = new Ordinate(0, 0, 0, k);
            Queue<Ordinate> queue = new LinkedList<>();
            queue.offer(start);

            Set<Ordinate> visited = new HashSet<>();
            visited.add(start);

            int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            while(!queue.isEmpty()) {
                Ordinate ordinate = queue.poll();

                if(ordinate.row == m - 1 && ordinate.col == n - 1) {
                    return ordinate.steps;
                }

                for(int[] dir : directions) {
                    int nrow = ordinate.row + dir[0];
                    int ncol = ordinate.col + dir[1];

                    if(nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && ordinate.tools >= grid[nrow][ncol]) {
                        Ordinate nOrdinate = new Ordinate(nrow, ncol, ordinate.steps + 1, ordinate.tools - grid[nrow][ncol]);

                        if(!visited.contains(nOrdinate)) {
                            queue.offer(nOrdinate);
                            visited.add(nOrdinate);
                        }
                    }
                }
            }

            return -1;
        }

        private class Ordinate {
            int row;
            int col;
            int steps;
            int tools;

            public Ordinate(int row, int col, int steps, int tools) {
                this.row = row;
                this.col = col;
                this.steps = steps;
                this.tools = tools;
            }

            @Override
            public boolean equals(Object other) {
                if(other instanceof Ordinate) {
                    Ordinate o = (Ordinate) other;
                    return this.row == o.row
                            && this.col == o.col
                            && this.tools == o.tools;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;

                hash *= 31;
                hash += row;

                hash *= 31;
                hash += col;

                hash *= 31;
                hash += tools;

                return hash;
            }

            @Override
            public String toString() {
                return "(" + row + ", " + col + ", " + steps + ", " + tools + ")";
            }
        }
    }

    class Solution_Brute_TLE {
        public int shortestPath(int[][] grid, int k) {
            if(grid == null || grid.length == 0 || grid[0].length == 0 || k < 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            boolean[][] visited = new boolean[m][n];
            int cost = shortestPath(grid, m, n, 0, 0, k, visited);
            return cost == Integer.MAX_VALUE ? -1 : cost;
        }

        private int shortestPath(int[][] grid, int m, int n, int row, int col, int k, boolean[][] visited) {
            if(row == m - 1 && col == n - 1) {
                return 0;
            }

            int cost = Integer.MAX_VALUE;
            if(visited[row][col]) {
                return cost;
            }

            visited[row][col] = true;
            // up
            if(row > 0) {
                if(grid[row - 1][col] == 1) {
                    // blocked
                    if(k > 0) {
                        int pathCost = shortestPath(grid, m, n, row - 1, col, k - 1, visited);
                        if(pathCost != Integer.MAX_VALUE) {
                            cost = Math.min(cost, 1 + pathCost);
                        }
                    }
                } else {
                    // clear
                    int pathCost = shortestPath(grid, m, n, row - 1, col, k, visited);
                    if(pathCost != Integer.MAX_VALUE) {
                        cost = Math.min(cost, 1 + pathCost);
                    }
                }
            }

            // down
            if(row < m - 1) {
                if(grid[row + 1][col] == 1) {
                    // blocked
                    if(k > 0) {
                        int pathCost = shortestPath(grid, m, n, row + 1, col, k - 1, visited);
                        if(pathCost != Integer.MAX_VALUE) {
                            cost = Math.min(cost, 1 + pathCost);
                        }
                    }
                } else {
                    // clear
                    int pathCost = shortestPath(grid, m, n, row + 1, col, k, visited);
                    if(pathCost != Integer.MAX_VALUE) {
                        cost = Math.min(cost, 1 + pathCost);
                    }
                }
            }

            // left
            if(col > 0) {
                if(grid[row][col - 1] == 1) {
                    // blocked
                    if(k > 0) {
                        int pathCost = shortestPath(grid, m, n, row, col - 1, k - 1, visited);
                        if(pathCost != Integer.MAX_VALUE) {
                            cost = Math.min(cost, 1 + pathCost);
                        }
                    }
                } else {
                    // clear
                    int pathCost = shortestPath(grid, m, n, row, col - 1, k, visited);
                    if(pathCost != Integer.MAX_VALUE) {
                        cost = Math.min(cost, 1 + pathCost);
                    }
                }
            }

            // right
            if(col < n - 1) {
                if(grid[row][col + 1] == 1) {
                    // blocked
                    if(k > 0) {
                        int pathCost = shortestPath(grid, m, n, row, col + 1, k - 1, visited);
                        if(pathCost != Integer.MAX_VALUE) {
                            cost = Math.min(cost, 1 + pathCost);
                        }
                    }
                } else {
                    // clear
                    int pathCost = shortestPath(grid, m, n, row, col + 1, k, visited);
                    if(pathCost != Integer.MAX_VALUE) {
                        cost = Math.min(cost, 1 + pathCost);
                    }
                }
            }

            visited[row][col] = false;
            return cost;
        }
    }


// [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]]
// 1

// [[0,1,0,1],[0,1,0,0],[0,0,1,0],[1,0,0,1],[0,1,0,0]]
// 18
}

//    1293. Shortest Path in a Grid with Obstacles Elimination
//    Hard
//    You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.
//
//    Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
//    Output: 6
//    Explanation:
//    The shortest path without eliminating any obstacle is 10.
//    The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
//    Example 2:
//
//
//    Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
//    Output: -1
//    Explanation: We need to eliminate at least two obstacles to find such a walk.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 40
//    1 <= k <= m * n
//    grid[i][j] is either 0 or 1.
//    grid[0][0] == grid[m - 1][n - 1] == 0