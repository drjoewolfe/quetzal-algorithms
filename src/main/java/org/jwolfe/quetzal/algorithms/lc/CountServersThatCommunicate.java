package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class CountServersThatCommunicate {
    class Solution {
        public int countServers(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[] rowSums = new int[m];
            int[] colSums = new int[n];

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    rowSums[r] += grid[r][c];
                    colSums[c] += grid[r][c];
                }
            }

            int connectedServers = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] == 1
                            && (rowSums[r] > 1 || colSums[c] > 1)) {
                        connectedServers++;
                    }
                }
            }

            return connectedServers;
        }

        private void print(int[][] arr) {
            for (int[] a : arr) {
                System.out.print("[");
                for (int b : a) {
                    System.out.print(b + " ");
                }
                System.out.print("] ");
            }

            System.out.println();
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public int countServers(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[] rowSums = new int[m];
            int[] colSums = new int[n];

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    rowSums[r] += grid[r][c];
                    colSums[c] += grid[r][c];
                }
            }

            int totalServers = 0;
            int disconnectedServers = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] == 0) {
                        continue;
                    }

                    totalServers++;
                    if (rowSums[r] == 1 && colSums[c] == 1) {
                        disconnectedServers++;
                    }
                }
            }

            return totalServers - disconnectedServers;
        }
    }

    class Solution_Set {
        public int countServers(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            Set<Integer> connectedSet = new HashSet<>();
            Set<Integer> tempSet = new HashSet<>();

            for (int r = 0; r < m; r++) {
                tempSet.clear();
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] == 1) {
                        int a = r * n + c;
                        tempSet.add(a);
                    }
                }

                if (tempSet.size() > 1) {
                    connectedSet.addAll(tempSet);
                }
            }

            for (int c = 0; c < n; c++) {
                tempSet.clear();
                for (int r = 0; r < m; r++) {
                    if (grid[r][c] == 1) {
                        int a = r * n + c;
                        tempSet.add(a);
                    }
                }

                if (tempSet.size() > 1) {
                    connectedSet.addAll(tempSet);
                }
            }

            return connectedSet.size();
        }
    }
}

//    1267. Count Servers that Communicate
//    Medium
//    You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.
//
//    Return the number of servers that communicate with any other server.
//
//
//
//    Example 1:
//
//
//
//    Input: grid = [[1,0],[0,1]]
//    Output: 0
//    Explanation: No servers can communicate with others.
//    Example 2:
//
//
//
//    Input: grid = [[1,0],[1,1]]
//    Output: 3
//    Explanation: All three servers can communicate with at least one other server.
//    Example 3:
//
//
//
//    Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
//    Output: 4
//    Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m <= 250
//    1 <= n <= 250
//    grid[i][j] == 0 or 1