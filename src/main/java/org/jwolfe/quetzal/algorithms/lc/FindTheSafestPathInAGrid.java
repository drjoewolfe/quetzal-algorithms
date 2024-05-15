package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindTheSafestPathInAGrid {
    class Solution {
        private int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int maximumSafenessFactor(List<List<Integer>> grid) {
            if(grid == null || grid.size() == 0) {
                return 0;
            }

            int n = grid.size();
            if(grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
                return 0;
            }

            int[][] distances = computeDistances(grid);
            return maximumSafenessFactor(distances);
        }

        private int maximumSafenessFactor(int[][] distances) {
            int left = 0;
            int right = getMaxDistance(distances);
            int maxSafeness = 0;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                if(allPathsGreaterThanOrEqualToSafeness(distances, mid)) {
                    maxSafeness = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return maxSafeness;
        }

        private boolean allPathsGreaterThanOrEqualToSafeness(int[][] distances, int safeness) {
            int n = distances.length;

            if(distances[0][0] < safeness || distances[n - 1][n - 1] < safeness) {
                return false;
            }

            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n][n];

            queue.offer(new int[] {0, 0});
            visited[0][0] = true;

            while(!queue.isEmpty()) {
                int[] element = queue.poll();
                int i = element[0];
                int j = element[1];

                for(int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    if(isValid(n, ni, nj, visited)
                            && distances[ni][nj] >= safeness) {
                        if(ni == n - 1 && nj == n - 1) {
                            return true;
                        }

                        visited[ni][nj] = true;
                        queue.offer(new int[] {ni, nj});
                    }
                }
            }

            return false;
        }

        private int[][] computeDistances(List<List<Integer>> grid) {
            int n = grid.size();
            int[][] distances = new int[n][n];
            boolean[][] visited = new boolean[n][n];
            Queue<int[]> queue = new LinkedList<>();
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    int val = grid.get(i).get(j);
                    if(val == 1) {
                        distances[i][j] = 0;
                        visited[i][j] = true;
                        queue.offer(new int[] {i, j});
                    } else {
                        distances[i][j] = -1;
                    }
                }
            }

            while(!queue.isEmpty()) {
                int[] ordinates = queue.poll();
                int i = ordinates[0];
                int j = ordinates[1];
                int currentDistance = distances[i][j];

                for(int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    if(isValid(n, ni, nj, visited)) {
                        distances[ni][nj] = currentDistance + 1;
                        visited[ni][nj] = true;
                        queue.offer(new int[] {ni, nj});
                    }
                }
            }

            return distances;
        }

        private int getMaxDistance(int[][] distances) {
            int max = 0;
            for(int[] row : distances) {
                for(int cell : row) {
                    max = Math.max(max, cell);
                }
            }

            return max;
        }

        private boolean isValid(int n, int i, int j, boolean[][] visited) {
            if(i < 0 || i >= n || j < 0 || j >= n || visited[i][j]) {
                return false;
            }

            return true;
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                for(int cell : row) {
                    System.out.print(cell +  " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        private int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int maximumSafenessFactor(List<List<Integer>> grid) {
            if(grid == null || grid.size() == 0) {
                return 0;
            }

            int n = grid.size();
            if(grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
                return 0;
            }

            int[][] distances = computeDistances(grid);

            boolean[][] visited = new boolean[n][n];
            visited[0][0] = true;

            return maximumSafenessFactor(distances, n, 0, 0, distances[0][0], visited);
        }

        private int maximumSafenessFactor(int[][] distances, int n, int i, int j, int safenessFactor, boolean[][] visited) {
            if(i == n - 1 && j == n - 1) {
                return safenessFactor;
            }

            int maxSafenessFactor = 0;
            for(int[] dir : directions) {
                int ni = i + dir[0];
                int nj = j + dir[1];

                if(isValid(n, ni, nj, visited)) {
                    visited[ni][nj] = true;
                    maxSafenessFactor = Math.max(maxSafenessFactor,
                            maximumSafenessFactor(distances, n, ni, nj, Math.min(safenessFactor, distances[ni][nj]), visited));
                    visited[ni][nj] = false;
                }
            }

            return maxSafenessFactor;
        }

        private int[][] computeDistances(List<List<Integer>> grid) {
            int n = grid.size();
            int[][] distances = new int[n][n];
            boolean[][] visited = new boolean[n][n];
            Queue<int[]> queue = new LinkedList<>();
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    int val = grid.get(i).get(j);
                    if(val == 1) {
                        distances[i][j] = 0;
                        visited[i][j] = true;
                        queue.offer(new int[] {i, j});
                    } else {
                        distances[i][j] = -1;
                    }
                }
            }

            while(!queue.isEmpty()) {
                int[] ordinates = queue.poll();
                int i = ordinates[0];
                int j = ordinates[1];
                int currentDistance = distances[i][j];

                for(int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    if(isValid(n, ni, nj, visited)) {
                        distances[ni][nj] = currentDistance + 1;
                        visited[ni][nj] = true;
                        queue.offer(new int[] {ni, nj});
                    }
                }
            }

            return distances;
        }

        private int getMaxDistance(int[][] distances) {
            int max = 0;
            for(int[] row : distances) {
                for(int cell : row) {
                    max = Math.max(max, cell);
                }
            }

            return max;
        }

        private boolean isValid(int n, int i, int j, boolean[][] visited) {
            if(i < 0 || i >= n || j < 0 || j >= n || visited[i][j]) {
                return false;
            }

            return true;
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                for(int cell : row) {
                    System.out.print(cell +  " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

// [[1,0,0],[0,0,0],[0,0,1]]
// [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
// [[1]]
// [[1,1,1],[0,1,1],[0,0,0]]
// [[0,1,1],[0,0,0],[0,0,0]]
}

//    2812. Find the Safest Path in a Grid
//    Medium
//    You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
//
//    A cell containing a thief if grid[r][c] = 1
//    An empty cell if grid[r][c] = 0
//    You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.
//
//    The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.
//
//    Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).
//
//    An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.
//
//    The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
//    Output: 0
//    Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
//    Example 2:
//
//
//    Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
//    Output: 2
//    Explanation: The path depicted in the picture above has a safeness factor of 2 since:
//    - The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
//    It can be shown that there are no other paths with a higher safeness factor.
//    Example 3:
//
//
//    Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
//    Output: 2
//    Explanation: The path depicted in the picture above has a safeness factor of 2 since:
//    - The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
//    - The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
//    It can be shown that there are no other paths with a higher safeness factor.
//
//
//    Constraints:
//
//    1 <= grid.length == n <= 400
//    grid[i].length == n
//    grid[i][j] is either 0 or 1.
//    There is at least one thief in the grid.