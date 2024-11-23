package org.jwolfe.quetzal.algorithms.lc;

public class RotatingTheBox {
    class Solution {
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