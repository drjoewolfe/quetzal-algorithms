package org.jwolfe.quetzal.algorithms.lc;

public class KnightProbabilityInChessboard {
    class Solution {
        public double knightProbability(int n, int k, int row, int column) {
            if(n < 1 || k < 0 || row < 0 || row >= n || column < 0 || column >= n) {
                return 0;
            }

            int[][] directions = new int[][] {{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, {2, -1}, {1, -2}, {2, 1}, {1, 2}};

            double[][][] dp = new double[k + 1][n][n];
            dp[0][row][column] = 1.0;

            for(int m = 1; m <= k; m++) {
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < n; j++) {
                        for(int[] direction : directions) {
                            int fromI = i - direction[0];
                            int fromJ = j - direction[1];

                            if(fromI >= 0 && fromI < n && fromJ >= 0 && fromJ < n) {
                                dp[m][i][j] += dp[m - 1][fromI][fromJ] / 8.0;
                            }
                        }
                    }
                }
            }

            double probability = 0d;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    probability += dp[k][i][j];
                }
            }

            return probability;
        }
    }

    class Solution_InCorrect {
        int inBoard;
        int offBoard;

        public double knightProbability(int n, int k, int row, int column) {
            if(n < 1 || k < 0 || row < 0 || row >= n || column < 0 || column >= n) {
                return 0;
            }

            inBoard = 0;
            offBoard = 0;
            moveKnight(n, k, row, column);

            System.out.println(inBoard + ", " + offBoard + ", " + k);
            return (1d * inBoard / (inBoard + offBoard));
        }

        private void moveKnight(int n, int k, int row, int column) {
            if(row < 0 || row >= n || column < 0 || column >= n) {
                offBoard++;
                return;
            }

            if(k == 0) {
                inBoard++;
                return;
            }

            moveKnight(n, k - 1, row - 2, column - 1);
            moveKnight(n, k - 1, row - 1, column - 2);
            moveKnight(n, k - 1, row - 2, column + 1);
            moveKnight(n, k - 1, row - 1, column + 2);
            moveKnight(n, k - 1, row + 2, column - 1);
            moveKnight(n, k - 1, row + 1, column - 2);
            moveKnight(n, k - 1, row + 2, column + 1);
            moveKnight(n, k - 1, row + 1, column + 2);
        }
    }
}

//    688. Knight Probability in Chessboard
//    Medium
//    On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves. The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
//
//    A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.
//
//
//    Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
//
//    The knight continues moving until it has made exactly k moves or has moved off the chessboard.
//
//    Return the probability that the knight remains on the board after it has stopped moving.
//
//
//
//    Example 1:
//
//    Input: n = 3, k = 2, row = 0, column = 0
//    Output: 0.06250
//    Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
//    From each of those positions, there are also two moves that will keep the knight on the board.
//    The total probability the knight stays on the board is 0.0625.
//    Example 2:
//
//    Input: n = 1, k = 0, row = 0, column = 0
//    Output: 1.00000
//
//
//    Constraints:
//
//    1 <= n <= 25
//    0 <= k <= 100
//    0 <= row, column <= n - 1