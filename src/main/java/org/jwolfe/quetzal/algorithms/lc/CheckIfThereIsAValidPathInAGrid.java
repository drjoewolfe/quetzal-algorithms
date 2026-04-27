package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfThereIsAValidPathInAGrid {
    class Solution {
        public boolean hasValidPath(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return false;
            }

            int m = grid.length;
            int n = grid[0].length;

            int size = m * n;
            DisjointSets sets = new DisjointSets(size);

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int street = grid[r][c];

                    if (street == 1 || street == 3 || street == 5) {
                        if (c > 0) {
                            int leftStreet = grid[r][c - 1];
                            if (leftStreet == 1 || leftStreet == 4 || leftStreet == 6) {
                                // Connected to left
                                sets.union(getMemento(n, r, c), getMemento(n, r, c - 1));
                            }
                        }
                    }

                    if (street == 1 || street == 4 || street == 6) {
                        if (c < n - 1) {
                            int rightStreet = grid[r][c + 1];
                            if (rightStreet == 1 || rightStreet == 3 || rightStreet == 5) {
                                // Connected to right
                                sets.union(getMemento(n, r, c), getMemento(n, r, c + 1));
                            }
                        }
                    }

                    if (street == 2 || street == 5 || street == 6) {
                        if (r > 0) {
                            int upStreet = grid[r - 1][c];
                            if (upStreet == 2 || upStreet == 3 || upStreet == 4) {
                                // Connected to top
                                sets.union(getMemento(n, r, c), getMemento(n, r - 1, c));
                            }
                        }
                    }

                    if (street == 2 || street == 3 || street == 4) {
                        if (r < m - 1) {
                            int downStreet = grid[r + 1][c];
                            if (downStreet == 2 || downStreet == 5 || downStreet == 6) {
                                // Connected to down
                                sets.union(getMemento(n, r, c), getMemento(n, r + 1, c));
                            }
                        }
                    }
                }
            }

            int start = getMemento(n, 0, 0);
            int end = getMemento(n, m - 1, n - 1);

            return sets.find(start) == sets.find(end);
        }

        private int getMemento(int n, int x, int y) {
            return x * n + y;
        }

        private class DisjointSets {
            int[] parent;
            int[] rank;

            public DisjointSets(int n) {
                parent = new int[n];
                rank = new int[n];

                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int x) {
                if (x != parent[x]) {
                    parent[x] = find(parent[x]);
                }

                return parent[x];
            }

            public void union(int x, int y) {
                int xr = find(x);
                int yr = find(y);

                if (rank[xr] > rank[yr]) {
                    parent[yr] = xr;
                } else if (rank[xr] < rank[yr]) {
                    parent[xr] = yr;
                } else {
                    parent[yr] = xr;
                    rank[xr]++;
                }
            }
        }
    }
}

//    1391. Check if There is a Valid Path in a Grid
//    Medium
//    You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:
//
//    1 which means a street connecting the left cell and the right cell.
//    2 which means a street connecting the upper cell and the lower cell.
//    3 which means a street connecting the left cell and the lower cell.
//    4 which means a street connecting the right cell and the lower cell.
//    5 which means a street connecting the left cell and the upper cell.
//    6 which means a street connecting the right cell and the upper cell.
//
//    You will initially start at the street of the upper-left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.
//
//    Notice that you are not allowed to change any street.
//
//    Return true if there is a valid path in the grid or false otherwise.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[2,4,3],[6,5,2]]
//    Output: true
//    Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
//    Example 2:
//
//
//    Input: grid = [[1,2,1],[1,2,1]]
//    Output: false
//    Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
//    Example 3:
//
//    Input: grid = [[1,1,2]]
//    Output: false
//    Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 300
//    1 <= grid[i][j] <= 6