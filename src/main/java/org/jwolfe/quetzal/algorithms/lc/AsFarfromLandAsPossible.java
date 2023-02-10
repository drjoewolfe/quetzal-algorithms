package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class AsFarfromLandAsPossible {
    class Solution {
        public int maxDistance(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int maxPossibleDistance = m + n + 1;
            int maxDistance = 0;

            int[][] dp = new int[m][n];
            for(int[] row : dp) {
                Arrays.fill(row, maxPossibleDistance);
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] == 1) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = Math.min(dp[i][j],
                                Math.min(i > 0 ? dp[i - 1][j] + 1: maxPossibleDistance,
                                        j > 0 ? dp[i][j - 1] + 1: maxPossibleDistance));
                    }
                }
            }

            for(int i = m - 1; i >= 0; i--) {
                for(int j = n - 1; j >= 0; j--) {
                    dp[i][j] = Math.min(dp[i][j],
                            Math.min(i < m - 1 ? dp[i + 1][j] + 1: Integer.MAX_VALUE,
                                    j < n - 1 ? dp[i][j + 1] + 1: Integer.MAX_VALUE));

                    maxDistance = Math.max(maxDistance, dp[i][j]);
                }
            }

            return maxDistance == 0 || maxDistance == maxPossibleDistance ? -1 : maxDistance;
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Correct_2 {
        public int maxDistance(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(grid[i][j] == 1) {
                        queue.offer(new Pair<>(i, j));
                    }
                }
            }

            int[][] directions = new int[][] { {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
            boolean[][] visited = new boolean[m][n];

            int distance = -1;
            while(!queue.isEmpty()) {
                int size = queue.size();
                while(size-- > 0) {
                    var pair = queue.poll();
                    int r = pair.getKey();
                    int c = pair.getValue();

                    for(var direction : directions) {
                        int tr = r + direction[0];
                        int tc = c + direction[1];

                        if(tr >= 0 && tr < m && tc >= 0 && tc < n && grid[tr][tc] == 0 && !visited[tr][tc]) {
                            queue.offer(new Pair<>(tr, tc));
                            visited[tr][tc] = true;
                        }
                    }
                }

                distance++;
            }

            return distance == 0 ? -1 : distance;
        }
    }

    class Solution_Correct_1 {
        public int maxDistance(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            Queue<Coordinates> queue = new LinkedList<>();

            boolean[][] visited = new boolean[m][n];
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(grid[r][c] == 1) {
                        queue.offer(new Coordinates(r, c, 0));
                        visited[r][c] = true;
                    }
                }
            }

            int maxDistance = 0;
            while(!queue.isEmpty()) {
                Map<Coordinates, Coordinates> iterationOrdinates = new HashMap<>();
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    Coordinates ordinates = queue.poll();
                    int x = ordinates.x;
                    int y = ordinates.y;
                    int d = ordinates.d;

                    processOrdinates(grid, m, n, x - 1, y, d + 1, visited, iterationOrdinates);
                    processOrdinates(grid, m, n, x + 1, y, d + 1, visited, iterationOrdinates);
                    processOrdinates(grid, m, n, x, y - 1, d + 1, visited, iterationOrdinates);
                    processOrdinates(grid, m, n, x, y + 1, d + 1, visited, iterationOrdinates);
                    processOrdinates(grid, m, n, x - 1, y - 1, d + 2, visited, iterationOrdinates);
                    processOrdinates(grid, m, n, x - 1, y + 1, d + 2, visited, iterationOrdinates);
                    processOrdinates(grid, m, n, x + 1, y - 1, d + 2, visited, iterationOrdinates);
                    processOrdinates(grid, m, n, x + 1, y + 1, d + 2, visited, iterationOrdinates);
                }

                for(var ordinate : iterationOrdinates.keySet()) {
                    queue.offer(ordinate);
                    visited[ordinate.x][ordinate.y] = true;
                    maxDistance = Math.max(maxDistance, ordinate.d);
                }
            }

            return maxDistance == 0 ? -1 : maxDistance;
        }

        private void processOrdinates(int[][] grid, int m, int n, int x, int y, int d, boolean[][] visited, Map<Coordinates, Coordinates> iterationCoordinates) {
            if(x < 0 || x >= m || y < 0 || y >= n) {
                return;
            }

            if(grid[x][y] == 0 && !visited[x][y]) {
                var ordinates = new Coordinates(x, y, d);
                if(iterationCoordinates.containsKey(ordinates)) {
                    ordinates = iterationCoordinates.get(ordinates);
                    ordinates.d = Math.min(ordinates.d, d);
                } else {
                    iterationCoordinates.put (ordinates, ordinates);
                }
            }
        }

        private class Coordinates {
            int x;
            int y;
            int d;

            public Coordinates(int x, int y, int d) {
                this.x = x;
                this.y = y;
                this.d = d;
            }

            @Override
            public boolean equals(Object other) {
                if(other instanceof Coordinates) {
                    Coordinates o = (Coordinates) other;
                    return this.x == o.x && this.y == o.y;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;

                hash *= 31;
                hash += x;

                hash *= 31;
                hash += y;

                return hash;
            }

            @Override
            public String toString() {
                return "[(" + x + ", " + y +  ")" + " " + d + "]";
            }
        }
    }

    class Solution_BFS_Incorrect {
        public int maxDistance(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            Queue<Coordinates> queue = new LinkedList<>();

            boolean[][] visited = new boolean[m][n];
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(grid[r][c] == 1) {
                        queue.offer(new Coordinates(r, c, 0));
                        visited[r][c] = true;
                    }
                }
            }

            int maxDistance = 0;
            while(!queue.isEmpty()) {
                Coordinates ordinates = queue.poll();
                int x = ordinates.x;
                int y = ordinates.y;
                int d = ordinates.d;

                maxDistance = Math.max(maxDistance, d);

                if(x > 0 && grid[x - 1][y] == 0 && !visited[x - 1][y]) {
                    queue.offer(new Coordinates(x - 1, y, d + 1));
                    visited[x - 1][y] = true;
                }

                if(x < m - 1 && grid[x + 1][y] == 0 && !visited[x + 1][y]) {
                    queue.offer(new Coordinates(x + 1, y, d + 1));
                    visited[x + 1][y] = true;
                }

                if(y > 0 && grid[x][y - 1] == 0 && !visited[x][y - 1]) {
                    queue.offer(new Coordinates(x, y - 1, d + 1));
                    visited[x][y - 1] = true;
                }

                if(y < n - 1 && grid[x][y + 1] == 0 && !visited[x][y + 1]) {
                    queue.offer(new Coordinates(x, y + 1, d + 1));
                    visited[x][y + 1] = true;
                }

                if(x > 0 && y > 0 && grid[x - 1][y - 1] == 0 && !visited[x - 1][y - 1]) {
                    queue.offer(new Coordinates(x - 1, y - 1, d + 2));
                    visited[x - 1][y - 1] = true;
                }

                if(x < m - 1 && y > 0 && grid[x + 1][y - 1] == 0 && !visited[x + 1][y - 1]) {
                    queue.offer(new Coordinates(x + 1, y - 1, d + 2));
                    visited[x + 1][y - 1] = true;
                }

                if(x > 0 && y < n - 1 && grid[x - 1][y + 1] == 0 && !visited[x - 1][y + 1]) {
                    queue.offer(new Coordinates(x - 1, y + 1, d + 2));
                    visited[x - 1][y + 1] = true;
                }

                if(x < m - 1 && y < n - 1 && grid[x + 1][y + 1] == 0 && !visited[x + 1][y + 1]) {
                    queue.offer(new Coordinates(x + 1, y + 1, d + 2));
                    visited[x + 1][y + 1] = true;
                }
            }

            return maxDistance == 0 ? -1 : maxDistance;
        }

        private class Coordinates {
            int x;
            int y;
            int d;

            public Coordinates(int x, int y, int d) {
                this.x = x;
                this.y = y;
                this.d = d;
            }
        }
    }

// [[1,0,1],[0,0,0],[1,0,1]]
// [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
// [[1,0,0],[0,0,0],[0,0,0]]
// [[0,0,1,1,1],[0,1,1,0,0],[0,0,1,1,0],[1,0,0,0,0],[1,1,0,0,1]]
// [[1,0,0,0,0,1,0,0,0,1],[1,1,0,1,1,1,0,1,1,0],[0,1,1,0,1,0,0,1,0,0],[1,0,1,0,1,0,0,0,0,0],[0,1,0,0,0,1,1,0,1,1],[0,0,1,0,0,1,0,1,0,1],[0,0,0,1,1,1,1,0,0,1],[0,1,0,0,1,0,0,1,0,0],[0,0,0,0,0,1,1,1,0,0],[1,1,0,1,1,1,1,1,0,0]]
// [[0,0,1,1,1],[0,1,1,0,0],[0,0,1,1,0],[1,0,0,0,0],[1,1,0,0,1]]
}

//    1162. As Far from Land as Possible
//    Medium
//    Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
//
//    The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
//    Output: 2
//    Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
//    Example 2:
//
//
//    Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
//    Output: 4
//    Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
//
//
//    Constraints:
//
//    n == grid.length
//    n == grid[i].length
//    1 <= n <= 100
//    grid[i][j] is 0 or 1