package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class PacificAtlanticWaterFlow {
    class Solution {
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            List<List<Integer>> result = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return result;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            boolean[][] pacific = new boolean[m][n];
            boolean[][] atlantic = new boolean[m][n];

            // Pacific - row
            for(int i = 0; i < m; i++) {
                dfs(matrix, m, n, i, 0, pacific, Integer.MIN_VALUE);
            }

            // Pacific - col
            for(int j = 0; j < n; j++) {
                dfs(matrix, m, n, 0, j, pacific, Integer.MIN_VALUE);
            }

            // Atlantic - row
            for(int i = m - 1; i >= 0; i--) {
                dfs(matrix, m, n, i, n - 1, atlantic, Integer.MIN_VALUE);
            }

            // Atlantic - col
            for(int j = n - 1; j >= 0; j--) {
                dfs(matrix, m, n, m - 1, j, atlantic, Integer.MIN_VALUE);
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(pacific[i][j] && atlantic[i][j]) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(j);
                        result.add(pair);
                    }
                }
            }

            return result;
        }

        private void dfs(int[][] matrix, int m, int n, int i, int j, boolean[][] ocean, int previous) {
            if(i < 0 || i > m - 1 || j < 0 || j > n - 1) {
                return;
            }

            if(matrix[i][j] < previous || ocean[i][j]) {
                return;
            }

            ocean[i][j] = true;

            dfs(matrix, m, n, i + 1, j, ocean, matrix[i][j]);
            dfs(matrix, m, n, i - 1, j, ocean, matrix[i][j]);
            dfs(matrix, m, n, i, j + 1, ocean, matrix[i][j]);
            dfs(matrix, m, n, i, j - 1, ocean, matrix[i][j]);
        }
    }

    class Solution_Incorrect {
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            List<List<Integer>> result = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return result;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            boolean[][] pacific = new boolean[m][n];
            boolean[][] atlantic = new boolean[m][n];

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(i == 0 || j == 0) {
                        pacific[i][j] = true;
                    } else {
                        if((matrix[i][j] >= matrix[i - 1][j] && pacific[i - 1][j])
                                || (matrix[i][j] >= matrix[i][j - 1] && pacific[i][j - 1])) {
                            pacific[i][j] = true;
                        }
                    }
                }
            }

            for(int i = m - 1; i >= 0; i--) {
                for(int j = n - 1; j >= 0; j--) {
                    if(i == m - 1 || j == n - 1) {
                        atlantic[i][j] = true;
                    } else {
                        if((matrix[i][j] >= matrix[i + 1][j] && atlantic[i + 1][j])
                                || (matrix[i][j] >= matrix[i][j + 1] && atlantic[i][j + 1])) {
                            atlantic[i][j] = true;
                        }
                    }
                }
            }

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(pacific[i][j] && atlantic[i][j]) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(j);
                        result.add(pair);
                    }
                }
            }

            return result;
        }
    }

// [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]

    class Solution_Classic {
        public List<List<Integer>> pacificAtlantic(int[][] matrix) {
            List<List<Integer>> result = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return result;
            }

            int m = matrix.length;
            int n = matrix[0].length;

            for(int i =0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(canReachPacificAndAtlantic(matrix, m, n, i, j)) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(j);
                        result.add(pair);
                    }
                }
            }

            return result;
        }

        private boolean canReachPacificAndAtlantic(int[][] matrix, int m, int n, int i, int j) {
            boolean reachPacific = false;
            boolean reachAtlantic = false;

            Point point = new Point(i, j);

            Queue<Point> queue = new LinkedList<>();
            queue.offer(point);

            Set<Point> visited = new HashSet<>();

            while(!queue.isEmpty()) {
                point = queue.poll();
                visited.add(point);

                int u = point.i;
                int v = point.j;

                if(u == 0 || v == 0) {
                    reachPacific = true;
                }

                if(u == m - 1 || v == n - 1) {
                    reachAtlantic = true;
                }

                if(reachPacific && reachAtlantic) {
                    return true;
                }

                if(u > 0) {
                    Point top = new Point(u - 1, v);
                    if(!visited.contains(top) && matrix[top.i][top.j] <= matrix[u][v]) {
                        queue.offer(top);
                    }
                }

                if(v > 0) {
                    Point left = new Point(u, v - 1);
                    if(!visited.contains(left) && matrix[left.i][left.j] <= matrix[u][v]) {
                        queue.offer(left);
                    }
                }

                if(u < m - 1) {
                    Point bottom = new Point(u + 1, v);
                    if(!visited.contains(bottom) && matrix[bottom.i][bottom.j] <= matrix[u][v]) {
                        queue.offer(bottom);
                    }
                }


                if(v < n - 1) {
                    Point right = new Point(u, v + 1);
                    if(!visited.contains(right) && matrix[right.i][right.j] <= matrix[u][v]) {
                        queue.offer(right);
                    }
                }
            }

            return false;
        }

        class Point {
            int i;
            int j;

            public Point(int i, int j) {
                this.i = i;
                this.j = j;
            }

            @Override
            public boolean equals(Object o) {
                if(o instanceof Point) {
                    Point other = (Point) o;
                    return this.i == other.i && this.j == other.j;
                }

                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;

                hash *= 31;
                hash += this.i;

                hash *= 31;
                hash += this.j;

                return hash;
            }
        }
    }

// [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
}

//    417. Pacific Atlantic Water Flow
//    Medium
//    Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
//
//    Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
//
//    Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
//
//    Note:
//
//    The order of returned grid coordinates does not matter.
//    Both m and n are less than 150.
//
//
//    Example:
//
//    Given the following 5x5 matrix:
//
//    Pacific ~   ~   ~   ~   ~
//    ~  1   2   2   3  (5) *
//    ~  3   2   3  (4) (4) *
//    ~  2   4  (5)  3   1  *
//    ~ (6) (7)  1   4   5  *
//    ~ (5)  1   1   2   4  *
//    *   *   *   *   * Atlantic
//
//    Return:
//
//    [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

