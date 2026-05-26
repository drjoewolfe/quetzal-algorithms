package org.jwolfe.quetzal.algorithms.lc;

public class RotatingTheBox {
    class Solution {
        public char[][] rotateTheBox(char[][] boxGrid) {
            if (boxGrid == null || boxGrid.length == 0 || boxGrid[0].length == 0) {
                return boxGrid;
            }

            char[][] rotatedGrid = rotate90(boxGrid);
            applyGravity(rotatedGrid);

            return rotatedGrid;
        }

        private char[][] rotate90(char[][] grid) {
            char[][] newGrid = transpose(grid);
            reverseColumns(newGrid);

            return newGrid;
        }

        private char[][] transpose(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            char[][] transposedGrid = new char[n][m];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    transposedGrid[c][r] = grid[r][c];
                }
            }

            return transposedGrid;
        }

        private void reverseColumns(char[][] grid) {
            int n = grid[0].length;
            for (char[] row : grid) {
                for (int i = 0; i < n / 2; i++) {
                    swap(row, i, n - i - 1);
                }
            }
        }

        private void swap(char[] arr, int left, int right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

        private void applyGravity(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            for (int c = 0; c < n; c++) {
                int lowestFreeRow = m - 1;
                for (int r = m - 1; r >= 0; r--) {
                    if (grid[r][c] == '#') {
                        // Stone
                        // Fall to lowest free cell
                        if (lowestFreeRow != r) {
                            grid[lowestFreeRow][c] = '#';
                            grid[r][c] = '.';
                        }

                        lowestFreeRow--;
                    } else if (grid[r][c] == '*') {
                        // Obstacle
                        lowestFreeRow = r - 1;
                    }
                }
            }
        }
    }

    class Solution_Correct_1 {
        public char[][] rotateTheBox(char[][] box) {
            if (box == null || box.length == 0 || box[0].length == 0) {
                return new char[0][0];
            }

            char[][] rotatedBox = rotateBox(box);
            int m = rotatedBox.length;
            int n = rotatedBox[0].length;

            for (int r = m - 2; r >= 0; r--) {
                for (int c = 0; c < n; c++) {
                    if (rotatedBox[r][c] == '#') {
                        fall(rotatedBox, r, c);
                    }
                }
            }

            return rotatedBox;
        }

        private void fall(char[][] box, int row, int col) {
            int curr = row;
            int next = curr + 1;
            while (next < box.length && box[next][col] == '.') {
                box[curr][col] = '.';
                box[next][col] = '#';

                curr++;
                next++;
            }
        }

        private char[][] rotateBox(char[][] box) {
            int m = box.length;
            int n = box[0].length;

            char[][] rotatedBox = new char[n][m];

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    rotatedBox[c][m - r - 1] = box[r][c];
                }
            }

            return rotatedBox;
        }

        private void print(char[][] arr) {
            for (char[] a : arr) {
                System.out.print("[");
                for (char b : a) {
                    System.out.print(b + " ");
                }
                System.out.print("] ");
            }

            System.out.println();
        }
    }
}

//    1861. Rotating the Box
//    Medium
//    You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
//
//    A stone '#'
//    A stationary obstacle '*'
//    Empty '.'
//    The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
//
//    It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
//
//    Return an n x m matrix representing the box after the rotation described above.
//
//
//
//    Example 1:
//
//
//
//    Input: box = [["#",".","#"]]
//    Output: [["."],
//    ["#"],
//    ["#"]]
//    Example 2:
//
//
//
//    Input: box = [["#",".","*","."],
//    ["#","#","*","."]]
//    Output: [["#","."],
//    ["#","#"],
//    ["*","*"],
//    [".","."]]
//    Example 3:
//
//
//
//    Input: box = [["#","#","*",".","*","."],
//    ["#","#","#","*",".","."],
//    ["#","#","#",".","#","."]]
//    Output: [[".","#","#"],
//    [".","#","#"],
//    ["#","#","*"],
//    ["#","*","."],
//    ["#",".","*"],
//    ["#",".","."]]
//
//
//    Constraints:
//
//    m == box.length
//    n == box[i].length
//    1 <= m, n <= 500
//    box[i][j] is either '#', '*', or '.'.