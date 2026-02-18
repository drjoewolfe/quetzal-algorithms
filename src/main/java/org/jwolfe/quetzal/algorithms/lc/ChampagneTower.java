package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class ChampagneTower {
    class Solution {
        public double champagneTower(int poured, int query_row, int query_glass) {
            if (poured == 0 || query_row < 0 || query_row > 100 || query_glass < 0 || query_glass > 100) {
                return 0d;
            }

            double[][] flows = new double[102][102];
            flows[0][0] = 1d * poured;

            for (int r = 0; r <= query_row; r++) {
                for (int c = 0; c <= r; c++) {
                    double overflow = flows[r][c] - 1.0;
                    double part = overflow / 2.0;

                    if (part > 0) {
                        flows[r + 1][c] += part;
                        flows[r + 1][c + 1] += part;
                    }
                }
            }

            return Math.min(1, flows[query_row][query_glass]);
        }
    }

    class Solution_Correct_2 {
        public double champagneTower(int poured, int query_row, int query_glass) {
            if (poured < 1) {
                return 0;
            }

            double[] curr = new double[101];
            double[] next = new double[101];
            double[] temp;

            curr[0] = poured;
            for (int row = 0; row <= query_row; row++) {
                Arrays.fill(next, 0);
                for (int glass = 0; glass <= row; glass++) {
                    double flow = curr[glass];
                    double halfSpill = (flow - 1) / 2.0;

                    if (halfSpill <= 0) {
                        continue;
                    }

                    next[glass] += halfSpill;
                    next[glass + 1] += halfSpill;
                }

                temp = curr;
                curr = next;
                next = temp;
            }

            return Math.min(1, next[query_glass]);
        }

        private void print(double[] arr) {
            for (double glass : arr) {
                System.out.print(glass + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public double champagneTower(int poured, int query_row, int query_glass) {
            if (poured < 1) {
                return 0;
            }

            double[][] tower = new double[101][101];
            tower[0][0] = poured;
            for (int row = 0; row <= query_row; row++) {
                for (int glass = 0; glass <= row; glass++) {
                    double flow = tower[row][glass];
                    double halfSpill = (flow - 1) / 2.0;

                    if (halfSpill <= 0) {
                        continue;
                    }

                    tower[row + 1][glass] += halfSpill;
                    tower[row + 1][glass + 1] += halfSpill;
                }
            }

            return Math.min(1, tower[query_row][query_glass]);
        }

        private void print(double[][] tower) {
            for (double[] row : tower) {
                for (double glass : row) {
                    System.out.print(glass + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }


// 1
// 1
// 1

// 2
// 1
// 1

// 25
// 6
// 1
}

//    799. Champagne Tower
//    Medium
//
//    3382
//
//    186
//
//    Add to List
//
//    Share
//    We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the 100th row.  Each glass holds one cup of champagne.
//
//    Then, some champagne is poured into the first glass at the top.  When the topmost glass is full, any excess liquid poured will fall equally to the glass immediately to the left and right of it.  When those glasses become full, any excess champagne will fall equally to the left and right of those glasses, and so on.  (A glass at the bottom row has its excess champagne fall on the floor.)
//
//    For example, after one cup of champagne is poured, the top most glass is full.  After two cups of champagne are poured, the two glasses on the second row are half full.  After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.  After four cups of champagne are poured, the third row has the middle glass half full, and the two outside glasses are a quarter full, as pictured below.
//
//
//
//    Now after pouring some non-negative integer cups of champagne, return how full the jth glass in the ith row is (both i and j are 0-indexed.)
//
//
//
//    Example 1:
//
//    Input: poured = 1, query_row = 1, query_glass = 1
//    Output: 0.00000
//    Explanation: We poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)). There will be no excess liquid so all the glasses under the top glass will remain empty.
//    Example 2:
//
//    Input: poured = 2, query_row = 1, query_glass = 1
//    Output: 0.50000
//    Explanation: We poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)). There is one cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share the excess liquid equally, and each will get half cup of champange.
//    Example 3:
//
//    Input: poured = 100000009, query_row = 33, query_glass = 17
//    Output: 1.00000
//
//
//    Constraints:
//
//    0 <= poured <= 109
//    0 <= query_glass <= query_row < 100