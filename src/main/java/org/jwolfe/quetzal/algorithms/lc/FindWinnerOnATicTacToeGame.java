package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class FindWinnerOnATicTacToeGame {
    class Solution {
        public String tictactoe(int[][] moves) {
            if(moves == null || moves.length == 0) {
                return "Pending";
            }

            String[][] board = new String[3][3];
            for(String[] row : board) {
                Arrays.fill(row, "-");
            }

            String player = "A";
            for(int[] move : moves) {
                board[move[0]][move[1]] = player;
                String state = getBoardState(board);
                if(!state.equals("Pending")) {
                    return state;
                }

                player = (player == "A") ? "B" : "A";
            }

            return "Pending";
        }

        private String getBoardState(String[][] board) {
            if(board[0][0] != "-"
                    && equals(board[0][0], board[0][1], board[0][2])) {
                return board[0][0];
            }

            if(board[1][0] != "-"
                    && equals(board[1][0], board[1][1], board[1][2])) {
                return board[1][0];
            }

            if(board[2][0] != "-"
                    && equals(board[2][0], board[2][1], board[2][2])) {
                return board[2][0];
            }

            if(board[0][0] != "-"
                    && equals(board[0][0], board[1][0], board[2][0])) {
                return board[0][0];
            }

            if(board[0][1] != "-"
                    && equals(board[0][1], board[1][1], board[2][1])) {
                return board[0][1];
            }

            if(board[0][2] != "-"
                    && equals(board[0][2], board[1][2], board[2][2])) {
                return board[0][2];
            }

            if(board[0][0] != "-"
                    && equals(board[0][0], board[1][1], board[2][2])) {
                return board[0][0];
            }

            if(board[0][2] != "-"
                    && equals(board[0][2], board[1][1], board[2][0])) {
                return board[0][2];
            }

            for(int r = 0; r < 3; r++) {
                for(int c = 0; c < 3; c++) {
                    if(board[r][c] == "-") {
                        return "Pending";
                    }
                }
            }

            return "Draw";
        }

        private boolean equals(String a, String b, String c) {
            if(a.equals(b) && b.equals(c)) {
                return true;
            }

            return false;
        }
    }

    class Solution_Correct_2 {
        public String tictactoe(int[][] moves) {
            if(moves == null || moves.length == 0) {
                return "Pending";
            }

            char[][] board = new char[3][3];
            boolean turnA = true;
            for(int[] move : moves) {
                int x = move[0];
                int y = move[1];

                if(turnA) {
                    board[x][y] = 'X';
                } else {
                    board[x][y] = 'O';
                }

                turnA = !turnA;
            }

            if(hasWon(board, 'X')) {
                return "A";
            } else if(hasWon(board, 'O')) {
                return "B";
            } else if(moves.length == 9) {
                return "Draw";
            }

            return "Pending";
        }

        private boolean hasWon(char[][] board, char symbol) {
            for(int i = 0; i < 3; i++) {
                if(board[i][0] == symbol
                        && board[i][1] == symbol
                        && board[i][2] == symbol) {
                    return true;
                }

                if(board[0][i] == symbol
                        && board[1][i] == symbol
                        && board[2][i] == symbol) {
                    return true;
                }
            }

            if(board[0][0] == symbol
                    && board[1][1] == symbol
                    && board[2][2] == symbol) {
                return true;
            }

            if(board[0][2] == symbol
                    && board[1][1] == symbol
                    && board[2][0] == symbol) {
                return true;
            }

            return false;
        }
    }
}

//    1275. Find Winner on a Tic Tac Toe Game
//    Easy
//    Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
//
//    Here are the rules of Tic-Tac-Toe:
//
//    Players take turns placing characters into empty squares (" ").
//    The first player A always places "X" characters, while the second player B always places "O" characters.
//    "X" and "O" characters are always placed into empty squares, never on filled ones.
//    The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
//    The game also ends if all squares are non-empty.
//    No more moves can be played if the game is over.
//    Given an array moves where each element is another array of size 2 corresponding to the row and column of the grid where they mark their respective character in the order in which A and B play.
//
//    Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are still movements to play return "Pending".
//
//    You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.
//
//
//
//    Example 1:
//
//    Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
//    Output: "A"
//    Explanation: "A" wins, he always plays first.
//    "X  "    "X  "    "X  "    "X  "    "X  "
//    "   " -> "   " -> " X " -> " X " -> " X "
//    "   "    "O  "    "O  "    "OO "    "OOX"
//    Example 2:
//
//    Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
//    Output: "B"
//    Explanation: "B" wins.
//    "X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
//    "   " -> " O " -> " O " -> " O " -> "XO " -> "XO "
//    "   "    "   "    "   "    "   "    "   "    "O  "
//    Example 3:
//
//    Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
//    Output: "Draw"
//    Explanation: The game ends in a draw since there are no moves to make.
//    "XXO"
//    "OOX"
//    "XOX"
//    Example 4:
//
//    Input: moves = [[0,0],[1,1]]
//    Output: "Pending"
//    Explanation: The game has not finished yet.
//    "X  "
//    " O "
//    "   "
//
//
//    Constraints:
//
//    1 <= moves.length <= 9
//    moves[i].length == 2
//    0 <= moves[i][j] <= 2
//    There are no repeated elements on moves.
//    moves follow the rules of tic tac toe.