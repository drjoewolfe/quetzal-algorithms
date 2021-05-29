package org.jwolfe.quetzal.algorithms.lc;

public class NQueensII {
    class Solution {
        int solutions;

        public int totalNQueens(int n) {
            if(n < 0) {
                return 0;
            }

            if(n < 2) {
                return n;
            }

            boolean[][] board = new boolean[n][n];
            solutions = 0;
            nQueens(board, n, 0);

            return solutions;
        }

        private void nQueens(boolean[][] board, int n, int r) {
            if(r == n) {
                solutions++;
                return;
            }

            for(int j = 0; j < n; j++) {
                if(isSafeForQueen(board, n, r, j)) {
                    board[r][j] = true;
                    nQueens(board, n, r + 1);

                    board[r][j] = false;
                }
            }
        }

        private boolean isSafeForQueen(boolean[][] board, int n, int r, int c) {
            for(int x = 0; x < n; x++) {
                if(board[r][x] || board[x][c]) {
                    return false;
                }
            }

            for(int i = r, j = c; i >= 0 && j >= 0; i--, j--) {
                if(board[i][j]) {
                    return false;
                }
            }

            for(int i = r, j = c; i >= 0 && j < n; i--, j++) {
                if(board[i][j]) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_A1 {
        public int totalNQueens(int n) {
            if(n < 1) {
                return 0;
            }

            if(n == 1) {
                return 1;
            }

            boolean[][] board = new boolean[n][n];
            return totalNQueens(n, 0, board);
        }

        private int totalNQueens(int n, int row, boolean[][] board) {
            if(row == n) {
                return 1;
            }

            int count = 0;
            for(int column = 0; column < n; column++) {
                if(isSafeForQueen(board, n, row, column)) {
                    board[row][column] = true;
                    count += totalNQueens(n, row + 1, board);

                    board[row][column] = false;
                }
            }

            return count;
        }

        private boolean isSafeForQueen(boolean[][] board, int n, int row, int column) {
            for(int i = 0; i < row; i++) {
                if(board[i][column]) {
                    return false;
                }
            }

            for(int j = 0; j < column; j++) {
                if(board[row][j]) {
                    return false;
                }
            }

            for(int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
                if(board[i][j]) {
                    return false;
                }
            }

            for(int i = row - 1, j = column + 1; i >= 0 && j < n; i--, j++) {
                if(board[i][j]) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    52. N-Queens II
//    Hard
//    The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
//
//    Given an integer n, return the number of distinct solutions to the n-queens puzzle.
//
//
//
//    Example 1:
//
//
//    Input: n = 4
//    Output: 2
//    Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
//    Example 2:
//
//    Input: n = 1
//    Output: 1
//
//
//    Constraints:
//
//    1 <= n <= 9