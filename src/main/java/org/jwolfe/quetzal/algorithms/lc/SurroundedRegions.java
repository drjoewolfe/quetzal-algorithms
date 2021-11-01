package org.jwolfe.quetzal.algorithms.lc;

public class SurroundedRegions {
    class Solution {
        public void solve(char[][] board) {
            if(board == null || board.length == 0 || board[0].length == 0) {
                return;
            }

            int m = board.length;
            int n = board[0].length;

            // Mark border regions
            for(int r = 0; r < m; r++) {
                if(board[r][0] == 'O') {
                    markRegion(board, m, n, r, 0);
                }

                if(board[r][n - 1] == 'O') {
                    markRegion(board, m, n, r, n - 1);
                }
            }

            for(int c = 0; c < n; c++) {
                if(board[0][c] == 'O') {
                    markRegion(board, m, n, 0, c);
                }

                if(board[m - 1][c] == 'O') {
                    markRegion(board, m, n, m - 1, c);
                }
            }

            // Process the grid
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(board[r][c] == 'O') {
                        board[r][c] = 'X';
                    } else if(board[r][c] == 'M') {
                        board[r][c] = 'O';
                    }
                }
            }
        }

        private void markRegion(char[][] board, int m, int n, int row, int column) {
            if(row < 0 || row >= m || column < 0 || column >= n
                    || board[row][column] != 'O') {
                return;
            }

            board[row][column] = 'M';

            markRegion(board, m, n, row - 1, column);
            markRegion(board, m, n, row + 1, column);
            markRegion(board, m, n, row, column - 1);
            markRegion(board, m, n, row, column + 1);
        }
    }

    class Solution_Correct_1 {
        public void solve(char[][] board) {
            if(board == null || board.length == 0 || board[0].length == 0) {
                return;
            }

            int rows = board.length;
            int columns = board[0].length;

            for(int r = 1; r < rows; r++) {
                if(board[r].length != columns) {
                    return;
                }
            }

            for(int r = 0; r < rows; r++) {
                if(board[r][0] == 'O') {
                    markBoundaryRegion(board, r, 0, rows, columns);
                }

                if(board[r][columns - 1] == 'O') {
                    markBoundaryRegion(board, r, columns - 1, rows, columns);
                }
            }

            for(int c = 0; c < columns; c++) {
                if(board[0][c] == 'O') {
                    markBoundaryRegion(board, 0, c, rows, columns);
                }

                if(board[rows - 1][c] == 'O') {
                    markBoundaryRegion(board, rows - 1, c, rows, columns);
                }
            }

            for(int r = 0; r < rows; r++) {
                for(int c = 0; c < columns; c++) {
                    if(board[r][c] == 'O') {
                        board[r][c] = 'X';
                    } else if(board[r][c] == '*') {
                        board[r][c] = 'O';
                    }
                }
            }
        }

        private void markBoundaryRegion(char[][] board, int r, int c, int rows, int columns) {
            if(r < 0 || r >= rows || c < 0 || c >= columns) {
                return;
            }

            if(board[r][c] != 'O') {
                return;
            }

            board[r][c] = '*';

            markBoundaryRegion(board, r, c + 1, rows, columns);
            markBoundaryRegion(board, r, c - 1, rows, columns);
            markBoundaryRegion(board, r + 1, c, rows, columns);
            markBoundaryRegion(board, r - 1, c, rows, columns);
        }

        private void printArray(char[][] board, int rows, int columns) {
            for(int r = 0; r < rows; r++) {
                for(int c = 0; c < columns; c++) {
                    System.out.print(board[r][c] + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }
}

//    130. Surrounded Regions
//    Medium
//    Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
//
//    A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//
//
//    Example 1:
//
//
//    Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
//    Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//    Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
//    Example 2:
//
//    Input: board = [["X"]]
//    Output: [["X"]]
//
//
//    Constraints:
//
//    m == board.length
//    n == board[i].length
//    1 <= m, n <= 200
//    board[i][j] is 'X' or 'O'.
