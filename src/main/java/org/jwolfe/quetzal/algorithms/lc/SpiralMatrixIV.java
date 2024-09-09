package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class SpiralMatrixIV {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public int[][] spiralMatrix(int m, int n, ListNode head) {
            if (m < 1 || n < 1) {
                return new int[0][0];
            }

            int[][] matrix = new int[m][n];
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }

            int i = 0;
            int j = 0;
            int d = 0;
            int[][] movements = new int[][]{
                    {0, 1},
                    {1, 0},
                    {0, -1},
                    {-1, 0}
            };

            while (head != null) {
                matrix[i][j] = head.val;

                int[] movement = movements[d];
                int ni = i + movement[0];
                int nj = j + movement[1];

                if (ni < 0 || nj < 0 || ni >= m || nj >= n || matrix[ni][nj] != -1) {
                    d = (d + 1) % 4;
                }

                movement = movements[d];
                i += movement[0];
                j += movement[1];

                head = head.next;
            }

            return matrix;
        }
    }

    class Solution_Correct_2 {
        public int[][] spiralMatrix(int m, int n, ListNode head) {
            if (m < 1 || n < 1) {
                return new int[0][0];
            }

            int[][] matrix = new int[m][n];
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }

            int i = 0;
            int j = 0;

            int top = 0;
            int left = 0;
            int right = n - 1;
            int bottom = m - 1;

            int direction = 0;

            while (head != null) {
                matrix[i][j] = head.val;

                if (direction == 0) {
                    j++;
                    if (j > right) {
                        i++;
                        j = right;
                        direction = 1;
                        top++;
                    }
                } else if (direction == 1) {
                    i++;

                    if (i > bottom) {
                        i = bottom;
                        j--;
                        direction = 2;
                        right--;
                    }
                } else if (direction == 2) {
                    j--;

                    if (j < left) {
                        i--;
                        j = left;
                        direction = 3;
                        bottom--;
                    }

                } else {
                    i--;

                    if (i < top) {
                        i = top;
                        j++;
                        direction = 0;
                        left++;
                    }
                }

                head = head.next;
            }


            return matrix;
        }
    }

    class Solution_Correct_1 {
        public int[][] spiralMatrix(int m, int n, ListNode head) {
            if (m < 1 || n < 1) {
                return new int[0][0];
            }

            int[][] matrix = new int[m][n];
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }

            int top = 0;
            int left = 0;
            int bottom = m - 1;
            int right = n - 1;
            int direction = 0; // 0 -> LR, 1 -> TB, 2 -> RL, 4 -> BT

            int i = 0;
            int j = 0;
            while (head != null) {
                matrix[i][j] = head.val;

                if (direction == 0) {
                    j++;
                    if (j > right) {
                        j = right;
                        i++;
                        top++;
                        direction = 1;
                    }
                } else if (direction == 1) {
                    i++;
                    if (i > bottom) {
                        i = bottom;
                        j--;
                        right--;
                        direction = 2;
                    }

                } else if (direction == 2) {
                    j--;
                    if (j < left) {
                        j = left;
                        i--;
                        bottom--;
                        direction = 3;
                    }
                } else {
                    i--;
                    if (i < top) {
                        i = top;
                        j++;
                        left++;
                        direction = 0;
                    }
                }

                head = head.next;
            }

            return matrix;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

// 3
// 5
// [3,0,2,6,8,1,7,9,4,2,5,5,0]
}

//    2326. Spiral Matrix IV
//    Medium
//    You are given two integers m and n, which represent the dimensions of a matrix.
//
//    You are also given the head of a linked list of integers.
//
//    Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.
//
//    Return the generated matrix.
//
//
//
//    Example 1:
//
//
//    Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
//    Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
//    Explanation: The diagram above shows how the values are printed in the matrix.
//    Note that the remaining spaces in the matrix are filled with -1.
//    Example 2:
//
//
//    Input: m = 1, n = 4, head = [0,1,2]
//    Output: [[0,1,2,-1]]
//    Explanation: The diagram above shows how the values are printed from left to right in the matrix.
//    The last space in the matrix is set to -1.
//
//
//    Constraints:
//
//    1 <= m, n <= 105
//    1 <= m * n <= 105
//    The number of nodes in the list is in the range [1, m * n].
//    0 <= Node.val <= 1000
