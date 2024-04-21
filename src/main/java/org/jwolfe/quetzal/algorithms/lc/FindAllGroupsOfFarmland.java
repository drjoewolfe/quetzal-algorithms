package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindAllGroupsOfFarmland {
    class Solution {
        public int[][] findFarmland(int[][] land) {
            if(land == null || land.length == 0 || land[0].length == 0) {
                return new int[0][0];
            }

            int m = land.length;
            int n = land[0].length;

            List<int[]> results = new ArrayList<>();
            for(int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if(land[r][c] == 1) {
                        // land;
                        int i = r;
                        int j = c;
                        for(i = r; i < m && land[i][c] == 1; i++) {
                            for(j = c; j < n && land[i][j] == 1; j++) {
                                land[i][j] = 0;
                            }
                        }

                        results.add(new int[] {r, c, i - 1, j - 1});
                    }
                }
            }

            int[][] answer = new int[results.size()][2];
            int index = 0;
            for(var entry : results) {
                answer[index++] = new int[] {entry[0], entry[1], entry[2], entry[3]};
            }

            return answer;
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

    class Solution_TLE {
        public int[][] findFarmland(int[][] land) {
            if(land == null || land.length == 0 || land[0].length == 0) {
                return new int[0][0];
            }

            int m = land.length;
            int n = land[0].length;

            List<int[]> results = new ArrayList<>();
            boolean[][] visited = new boolean[m][n];

            for(int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if(land[r][c] == 1 && !visited[r][c]) {
                        // land;
                        int[] last = visitLand(land, r, c, visited);

                        results.add(new int[] {r, c, last[0], last[1]});
                    }
                }
            }

            int[][] answer = new int[results.size()][2];
            int index = 0;
            for(var entry : results) {
                answer[index++] = new int[] {entry[0], entry[1], entry[2], entry[3]};
            }

            return answer;
        }

        private int[] visitLand(int[][] land, int row, int col, boolean[][] visited) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[] {row, col});

            int m = land.length;
            int n = land[0].length;

            int[] curr = null;
            while(!queue.isEmpty()) {
                curr = queue.poll();

                int r = curr[0];
                int c = curr[1];

                visited[r][c] = true;

                if(r > 0 && land[r - 1][c] == 1 && !visited[r - 1][c]) {
                    queue.offer(new int[] {r - 1, c});
                }

                if(r < m - 1 && land[r + 1][c] == 1 && !visited[r + 1][c]) {
                    queue.offer(new int[] {r + 1, c});
                }

                if(c > 0 && land[r][c - 1] == 1 && !visited[r][c - 1]) {
                    queue.offer(new int[] {r, c - 1});
                }

                if(c < n - 1 && land[r][c + 1] == 1 && !visited[r][c + 1]) {
                    queue.offer(new int[] {r, c + 1});
                }
            }

            return curr;
        }
    }
}

//    1992. Find All Groups of Farmland
//    Medium
//    You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.
//
//    To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.
//
//    land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
//
//    Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no groups of farmland, return an empty array. You may return the answer in any order.
//
//
//
//    Example 1:
//
//
//    Input: land = [[1,0,0],[0,1,1],[0,1,1]]
//    Output: [[0,0,0,0],[1,1,2,2]]
//    Explanation:
//    The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
//    The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].
//    Example 2:
//
//
//    Input: land = [[1,1],[1,1]]
//    Output: [[0,0,1,1]]
//    Explanation:
//    The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].
//    Example 3:
//
//
//    Input: land = [[0]]
//    Output: []
//    Explanation:
//    There are no groups of farmland.
//
//
//    Constraints:
//
//    m == land.length
//    n == land[i].length
//    1 <= m, n <= 300
//    land consists of only 0's and 1's.
//    Groups of farmland are rectangular in shape.
