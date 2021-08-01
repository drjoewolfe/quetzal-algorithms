package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland {
    class Solution {
        public int largestIsland(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length != grid.length) {
                return 0;
            }

            int n = grid.length;
            int componentId = 2;

            Map<Integer, Integer> componentSizes = new HashMap<>();
            int maxSize = -1;
            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    if(grid[r][c] == 1) {
                        int size = markIsland(grid, n, r, c, componentId);
                        maxSize = Math.max(maxSize, size);
                        componentSizes.put(componentId, size);
                        componentId++;
                    }
                }
            }

            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    if(grid[r][c] == 0) {
                        Set<Integer> neighbouringComponents = new HashSet<>();

                        if(r > 0 && grid[r - 1][c] > 1) {
                            neighbouringComponents.add(grid[r - 1][c]);
                        }

                        if(c > 0 && grid[r][c - 1] > 1) {
                            neighbouringComponents.add(grid[r][c - 1]);
                        }

                        if(r < n - 1 && grid[r + 1][c] > 1) {
                            neighbouringComponents.add(grid[r + 1][c]);
                        }

                        if(c < n - 1 && grid[r][c + 1] > 1) {
                            neighbouringComponents.add(grid[r][c + 1]);
                        }

                        int size = 1;
                        for(int component : neighbouringComponents) {
                            size += componentSizes.get(component);
                        }

                        maxSize = Math.max(maxSize, size);
                    }
                }
            }

            return maxSize;
        }

        private int markIsland(int[][] grid, int n, int r, int c, int componentId) {
            return markIsland(grid, n, r, c, componentId, new boolean[n][n]);
        }

        private int markIsland(int[][] grid, int n, int r, int c, int componentId, boolean[][] visited) {
            if(r < 0 || r == n || c < 0 || c == n || visited[r][c] || grid[r][c] == 0) {
                return 0;
            }

            visited[r][c] = true;
            grid[r][c] = componentId;

            int size = 1;
            size += markIsland(grid, n, r - 1, c, componentId, visited);
            size += markIsland(grid, n, r, c - 1, componentId, visited);
            size += markIsland(grid, n, r + 1, c, componentId, visited);
            size += markIsland(grid, n, r, c + 1, componentId, visited);

            return size;
        }
    }

    class Solution_TLE {
        public int largestIsland(int[][] grid) {
            // Try replacing zeros to one, one at a time & check for the size of the island formed from there

            if(grid == null || grid.length == 0 || grid[0].length != grid.length) {
                return 0;
            }

            int n = grid.length;
            int maxSize = -1;
            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    if(grid[r][c] == 0) {
                        grid[r][c] = 1;
                        maxSize = Math.max(maxSize,
                                islandSize(grid, n, r, c));

                        grid[r][c] = 0;
                    }
                }
            }

            if(maxSize == -1) {
                return n * n;
            }

            return maxSize;
        }

        private int islandSize(int[][] grid, int n, int r, int c) {
            return islandSize(grid, n, r, c, new boolean[n][n]);
        }

        private int islandSize(int[][] grid, int n, int r, int c, boolean[][] visited) {
            if(r < 0 || r == n || c < 0 || c == n || visited[r][c] || grid[r][c] == 0) {
                return 0;
            }

            visited[r][c] = true;

            int size = 1;
            size += islandSize(grid, n, r - 1, c, visited);
            size += islandSize(grid, n, r, c - 1, visited);
            size += islandSize(grid, n, r + 1, c, visited);
            size += islandSize(grid, n, r, c + 1, visited);

            return size;
        }
    }

// [[1,0],[0,1]]
}

//    827. Making A Large Island
//    Hard
//    You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
//
//    Return the size of the largest island in grid after applying this operation.
//
//    An island is a 4-directionally connected group of 1s.
//
//
//
//    Example 1:
//
//    Input: grid = [[1,0],[0,1]]
//    Output: 3
//    Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
//    Example 2:
//
//    Input: grid = [[1,1],[1,0]]
//    Output: 4
//    Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
//    Example 3:
//
//    Input: grid = [[1,1],[1,1]]
//    Output: 4
//    Explanation: Can't change any 0 to 1, only one island with area = 4.
//
//
//    Constraints:
//
//    n == grid.length
//    n == grid[i].length
//    1 <= n <= 500
//    grid[i][j] is either 0 or 1.
