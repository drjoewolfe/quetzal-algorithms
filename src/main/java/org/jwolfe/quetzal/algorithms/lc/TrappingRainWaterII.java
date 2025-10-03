package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class TrappingRainWaterII {
    class Solution {
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int trapRainWater(int[][] heightMap) {
            if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
                return 0;
            }

            int m = heightMap.length;
            int n = heightMap[0].length;

            boolean[][] visited = new boolean[m][n];

            PriorityQueue<int[]> boundaryHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

            for (int r = 0; r < m; r++) {
                boundaryHeap.offer(new int[]{heightMap[r][0], r, 0});
                visited[r][0] = true;

                boundaryHeap.offer(new int[]{heightMap[r][n - 1], r, n - 1});
                visited[r][n - 1] = true;
            }

            for (int c = 0; c < n; c++) {
                boundaryHeap.offer(new int[]{heightMap[0][c], 0, c});
                visited[0][c] = true;

                boundaryHeap.offer(new int[]{heightMap[m - 1][c], m - 1, c});
                visited[m - 1][c] = true;
            }

            int trappedWater = 0;
            while (!boundaryHeap.isEmpty()) {
                int[] cell = boundaryHeap.poll();
                int h = cell[0];
                int r = cell[1];
                int c = cell[2];

                for (int[] direction : directions) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    if (nr < 0 || nr == m || nc < 0 || nc == n || visited[nr][nc]) {
                        continue;
                    }

                    int nh = heightMap[nr][nc];
                    if (nh < h) {
                        int water = h - nh;
                        trappedWater += water;
                    }

                    boundaryHeap.offer(new int[]{Math.max(h, nh), nr, nc});
                    visited[nr][nc] = true;
                }
            }

            return trappedWater;
        }
    }

    class Solution_Correct_1 {
        private final int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        public int trapRainWater(int[][] heightMap) {
            if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
                return 0;
            }

            int m = heightMap.length;
            int n = heightMap[0].length;
            boolean[][] visited = new boolean[m][n];

            int totalWater = 0;
            PriorityQueue<int[]> boundaryHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
            for (int r = 0; r < m; r++) {
                boundaryHeap.offer(new int[]{r, 0, heightMap[r][0]});
                boundaryHeap.offer(new int[]{r, n - 1, heightMap[r][n - 1]});

                visited[r][0] = true;
                visited[r][n - 1] = true;
            }

            for (int c = 0; c < n; c++) {
                boundaryHeap.offer(new int[]{0, c, heightMap[0][c]});
                boundaryHeap.offer(new int[]{m - 1, c, heightMap[m - 1][c]});

                visited[0][c] = true;
                visited[m - 1][c] = true;
            }

            while (!boundaryHeap.isEmpty()) {
                int[] cell = boundaryHeap.poll();
                int r = cell[0];
                int c = cell[1];
                int h = cell[2];

                for (int[] direction : directions) {
                    int dr = direction[0];
                    int dc = direction[1];

                    int nr = r + dr;
                    int nc = c + dc;

                    if (!isValid(m, n, nr, nc)
                            || visited[nr][nc]) {
                        continue;
                    }

                    int nh = heightMap[nr][nc];
                    if (nh < h) {
                        totalWater += (h - nh);
                        nh = h;
                    }

                    boundaryHeap.offer(new int[]{nr, nc, nh});
                    visited[nr][nc] = true;
                }
            }

            return totalWater;
        }

        private boolean isValid(int m, int n, int r, int c) {
            return (r >= 0)
                    && (r < m)
                    && (c >= 0)
                    && (c < n);
        }
    }
}

//    407. Trapping Rain Water II
//    Hard
//    Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
//
//
//
//    Example 1:
//
//
//    Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
//    Output: 4
//    Explanation: After the rain, water is trapped between the blocks.
//    We have two small ponds 1 and 3 units trapped.
//    The total volume of water trapped is 4.
//    Example 2:
//
//
//    Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
//    Output: 10
//
//
//    Constraints:
//
//    m == heightMap.length
//    n == heightMap[i].length
//    1 <= m, n <= 200
//    0 <= heightMap[i][j] <= 2 * 104