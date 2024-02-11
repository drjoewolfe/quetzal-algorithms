package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CherryPickupII {
    class Solution {
        public int cherryPickup(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][][] dp = new int[m][n][n];

            // Last Row
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    if(j == k) {
                        dp[m - 1][j][k] = grid[m - 1][j];
                    } else {
                        dp[m - 1][j][k] = grid[m - 1][j] + grid[m - 1][k];
                    }
                }
            }

            int[] directions = new int[] {-1, 0, 1};

            // Remaining Rows
            for(int i = m - 2; i >= 0; i--) {

                for(int j = 0; j < n; j++) {

                    for(int dj : directions) {
                        int nj = j + dj;
                        if(nj < 0 || nj == n) {
                            continue;
                        }

                        for(int k = 0; k < n; k++) {
                            for(int dk : directions) {
                                int nk = k + dk;
                                if(nk < 0 || nk == n) {
                                    continue;
                                }

                                if(j == k) {
                                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i + 1][nj][nk] + grid[i][j]);
                                } else {
                                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i + 1][nj][nk] + grid[i][j] + grid[i][k]);
                                }
                            }
                        }

                    }
                }
            }

            return dp[0][0][n - 1];
        }

        private void print(int[][][] arr) {
            for(int[][] a : arr) {
                System.out.print("[");
                for(int[] b : a) {
                    System.out.print("[");
                    for(int c : b) {
                        System.out.print(c + " ");
                    }
                    System.out.print("] ");
                }
                System.out.println("] ");
            }

            System.out.println();
        }
    }


    class Solution_Memoized_3 {
        public int cherryPickup(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            for(var row : grid) {
                if(row.length != n) {
                    return 0;
                }
            }

            return cherryPickup(grid, m, n, new Position(0, 0), new Position(0, n - 1), new HashMap<>());
        }

        private int cherryPickup(int[][] grid, int m, int n, Position robot1Position, Position robot2Position, Map<String, Integer> memo) {
            int row = robot1Position.row;
            if(row == m) {
                // Completed the last row
                return 0;
            }

            String memento = getMemento(robot1Position, robot2Position);
            if(memo.containsKey(memento)) {
                return memo.get(memento);
            }

            int maxCherries = 0;
            for(int p1 = -1; p1 <= 1; p1++) {
                int j1 = robot1Position.col + p1;
                if(j1 < 0 || j1 >= n) {
                    continue;
                }

                for(int p2 = -1; p2 <= 1; p2++) {
                    int j2 = robot2Position.col + p2;
                    if(j2 < 0 || j2 >= n) {
                        continue;
                    }

                    if(j1 == j2) {
                        continue;
                    }

                    maxCherries = Math.max(maxCherries,
                            cherryPickup(grid, m, n, new Position(row + 1, j1), new Position(row + 1, j2), memo));
                }
            }

            maxCherries += (grid[row][robot1Position.col] + grid[row][robot2Position.col]);
            memo.put(memento, maxCherries);
            return maxCherries;
        }

        private String getMemento(Position p1, Position p2) {
            return p1.row + " - " + p1.col + " : " + p2.row + " - " + p2.col;
        }

        class Position {
            int row;
            int col;

            public Position(int i, int j) {
                this.row = i;
                this.col = j;
            }
        }
    }

    class Solution_Memoized_1 {
        public int cherryPickup(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            for(var row : grid) {
                if(row.length != n) {
                    return 0;
                }
            }

            return cherryPickup(grid, m, n, new Position(0, 0), new Position(0, n - 1), grid[0][0] + grid[0][n - 1], new HashMap<>());
        }

        private int cherryPickup(int[][] grid, int m, int n, Position robot1Position, Position robot2Position, int cherries, Map<String, Integer> memo) {
            int row = robot1Position.row;
            if(row == m - 1) {
                // Completed the last row
                return cherries;
            }

            String memento = getMemento(robot1Position, robot2Position, cherries);
            if(memo.containsKey(memento)) {
                return memo.get(memento);
            }

            int maxCherries = 0;
            for(int p1 = -1; p1 <= 1; p1++) {
                int j1 = robot1Position.col + p1;
                if(j1 < 0 || j1 >= n) {
                    continue;
                }

                for(int p2 = -1; p2 <= 1; p2++) {
                    int j2 = robot2Position.col + p2;
                    if(j2 < 0 || j2 >= n) {
                        continue;
                    }

                    if(j1 == j2) {
                        continue;
                    }

                    maxCherries = Math.max(maxCherries,
                            cherryPickup(grid, m, n, new Position(row + 1, j1), new Position(row + 1, j2), cherries + grid[row + 1][j1] + grid[row + 1][j2], memo));
                }
            }

            memo.put(memento, maxCherries);
            return maxCherries;
        }

        private String getMemento(Position p1, Position p2, int seed) {
            return p1.row + " - " + p1.col + " : " + p2.row + " - " + p2.col + " & " + seed;
        }

        class Position {
            int row;
            int col;

            public Position(int i, int j) {
                this.row = i;
                this.col = j;
            }
        }
    }


    class Solution_Recursive {
        public int cherryPickup(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            for(var row : grid) {
                if(row.length != n) {
                    return 0;
                }
            }

            return cherryPickup(grid, m, n, new Position(0, 0), new Position(0, n - 1), grid[0][0] + grid[0][n - 1]);
        }

        private int cherryPickup(int[][] grid, int m, int n, Position robot1Position, Position robot2Position, int cherries) {
            int row = robot1Position.row;
            if(row == m - 1) {
                // Completed the last row
                return cherries;
            }

            int maxCherries = 0;
            for(int p1 = -1; p1 <= 1; p1++) {
                int j1 = robot1Position.col + p1;
                if(j1 < 0 || j1 >= n) {
                    continue;
                }

                for(int p2 = -1; p2 <= 1; p2++) {
                    int j2 = robot2Position.col + p2;
                    if(j2 < 0 || j2 >= n) {
                        continue;
                    }

                    if(j1 == j2) {
                        continue;
                    }

                    maxCherries = Math.max(maxCherries,
                            cherryPickup(grid, m, n, new Position(row + 1, j1), new Position(row + 1, j2), cherries + grid[row + 1][j1] + grid[row + 1][j2]));
                }
            }

            return maxCherries;
        }

        class Position {
            int row;
            int col;

            public Position(int i, int j) {
                this.row = i;
                this.col = j;
            }
        }
    }



// [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
// [[16,1,9,16,5,16,3,16,6,3,1,7],[10,0,0,12,9,12,19,3,9,3,18,9],[18,6,6,13,2,1,9,8,12,2,10,6],[16,7,6,10,5,17,16,0,12,7,5,15],[20,11,17,15,2,8,12,2,17,13,12,0],[11,1,10,11,13,9,16,7,1,12,13,8],[12,19,19,3,13,0,7,1,1,3,1,16],[4,9,1,4,16,19,11,11,3,9,2,7],[10,13,1,4,3,7,14,3,20,7,6,11],[8,17,14,13,2,5,9,11,11,18,19,15],[16,4,3,13,18,17,14,16,15,12,20,13],[20,0,19,16,0,3,16,16,1,15,2,20],[20,18,8,11,0,13,8,7,13,6,18,19],[9,2,9,18,10,16,0,5,9,11,4,14],[11,4,18,8,18,18,10,12,11,0,10,13],[0,7,9,2,1,17,4,1,6,9,7,9]]
}

//    1463. Cherry Pickup II
//    Hard
//    You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.
//
//    You have two robots that can collect cherries for you:
//
//    Robot #1 is located at the top-left corner (0, 0), and
//    Robot #2 is located at the top-right corner (0, cols - 1).
//    Return the maximum number of cherries collection using both robots by following the rules below:
//
//    From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
//    When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
//    When both robots stay in the same cell, only one takes the cherries.
//    Both robots cannot move outside of the grid at any moment.
//    Both robots should reach the bottom row in grid.
//
//
//    Example 1:
//
//
//    Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
//    Output: 24
//    Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
//    Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
//    Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
//    Total of cherries: 12 + 12 = 24.
//    Example 2:
//
//
//    Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
//    Output: 28
//    Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
//    Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
//    Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
//    Total of cherries: 17 + 11 = 28.
//
//
//    Constraints:
//
//    rows == grid.length
//    cols == grid[i].length
//    2 <= rows, cols <= 70
//    0 <= grid[i][j] <= 100
