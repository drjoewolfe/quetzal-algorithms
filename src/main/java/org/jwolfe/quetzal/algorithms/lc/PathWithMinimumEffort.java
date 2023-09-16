package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PathWithMinimumEffort {
    class Solution {
        int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public int minimumEffortPath(int[][] heights) {
            if(heights == null || heights.length == 0 || heights[0].length == 0) {
                return 0;
            }

            int m = heights.length;
            int n = heights[0].length;

            boolean[][] visited = new boolean[m][n];

            int left = 0;
            int right = 1_000_000;
            while(left < right) {
                int mid = left + (right - left) / 2;

                for(var row : visited) {
                    Arrays.fill(row, false);
                }

                dfs(heights, m, n, 0, 0, mid, visited);
                if(visited[m - 1][n - 1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        private void dfs(int[][] heights, int m, int n, int x, int y, int limit, boolean[][] visited) {
            visited[x][y] = true;
            if(x == m - 1 && y == n - 1) {
                return;
            }

            for(int[] direction : directions) {
                int dx = direction[0];
                int dy = direction[1];

                int nx = x + dx;
                int ny = y + dy;

                if(nx < 0 || nx == m || ny < 0 || ny == n || visited[nx][ny]) {
                    continue;
                }

                int effort = Math.abs(heights[x][y] - heights[nx][ny]);
                if(effort <= limit) {
                    dfs(heights, m, n, nx, ny, limit, visited);
                }
            }
        }
    }

    class Solution_Correct_1 {
        private int minEffort;

        public int minimumEffortPath(int[][] heights) {
            if(heights == null || heights.length == 0 || heights[0].length == 0) {
                return 0;
            }

            int m = heights.length;
            int n = heights[0].length;

            int[][] cost = new int[m][n];
            for(var row : cost) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
            queue.offer(new int[] {0, 0, 0});

            int[][] dir = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

            boolean[][] visited = new boolean[m][n];

            int maxSoFar = 0;
            while(!queue.isEmpty()) {
                int[] curr = queue.poll();
                int i = curr[0];
                int j = curr[1];
                int c = curr[2];

                visited[i][j] = true;

                if(i == m - 1 && j == n - 1) {
                    return c;
                }

                for(int k = 0; k < dir.length; k++) {
                    int x = i + dir[k][0];
                    int y = j + dir[k][1];

                    if(x >= 0 && x < m && y >= 0 && y < n
                            && !visited[x][y]) {
                        int diff = Math.abs(heights[x][y] - heights[i][j]);
                        int maxDiff = Math.max(diff, c);

                        if(maxDiff < cost[x][y]) {
                            cost[x][y] = maxDiff;
                            queue.offer(new int[] {x, y, cost[x][y]});
                        }
                    }
                }
            }

            return cost[m - 1][n - 1];
        }
    }

    class Solution_Brute {
        private int minEffort;

        public int minimumEffortPath(int[][] heights) {
            if(heights == null || heights.length == 0 || heights[0].length == 0) {
                return 0;
            }


            Set<Coordinates> visited = new HashSet<>();
            minEffort = Integer.MAX_VALUE;
            minimumEffortPath(heights, heights.length, heights[0].length , new Coordinates(0, 0), null, 0, visited);

            return minEffort;
        }

        public void minimumEffortPath(int[][] heights, int m, int n, Coordinates c, Coordinates p, int maxAbsoluteDifference, Set<Coordinates> visited) {

            if(c.i < 0 || c.i >= m || c.j < 0 || c.j >= n) {
                return;
            }

            if(p != null) {
                int absDifference = Math.abs(heights[c.i][c.j] - heights[p.i][p.j]);
                maxAbsoluteDifference = Math.max(maxAbsoluteDifference, absDifference);

                // System.out.println(c + " - " + p + " : " + absDifference + ", " + maxAbsoluteDifference + ", " + minEffort);
            }

            if(c.i == m - 1 && c.j == n - 1) {
                minEffort = Math.min(minEffort, maxAbsoluteDifference);
                return;
            }

            if(visited.contains(c)) {
                return;
            }

            visited.add(c);

            minimumEffortPath(heights, m, n, new Coordinates(c.i + 1, c.j), c, maxAbsoluteDifference, visited);
            minimumEffortPath(heights, m, n, new Coordinates(c.i - 1, c.j), c, maxAbsoluteDifference, visited);
            minimumEffortPath(heights, m, n, new Coordinates(c.i, c.j + 1), c, maxAbsoluteDifference, visited);
            minimumEffortPath(heights, m, n, new Coordinates(c.i, c.j - 1), c, maxAbsoluteDifference, visited);

            visited.remove(c);
        }

        class Coordinates {
            int i;
            int j;

            public Coordinates(int i, int j) {
                this.i = i;
                this.j = j;
            }

            public boolean equals(Object o) {
                if(o instanceof Coordinates) {
                    Coordinates other = (Coordinates) o;
                    return this.i == other.i && this.j == other.j;
                }

                return false;
            }

            public int hashCode() {
                int hash = 7;

                hash *= 31;
                hash += i;

                hash *= 31;
                hash += j;

                return hash;
            }

            public String toString() {
                return "(" + i + ", " + j + ")";
            }
        }
    }

// [[1,2,2],[3,8,2],[5,3,5]]
}

//    1631. Path With Minimum Effort
//    You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
//
//    A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
//
//    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
//
//
//
//    Example 1:
//
//    Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
//    Output: 2
//    Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
//    This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
//
//    Example 2:
//    Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
//    Output: 1
//    Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
//
//    Example 3:
//    Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//    Output: 0
//    Explanation: This route does not require any effort.
//
//
//    Constraints:
//
//    rows == heights.length
//    columns == heights[i].length
//    1 <= rows, columns <= 100
//    1 <= heights[i][j] <= 106