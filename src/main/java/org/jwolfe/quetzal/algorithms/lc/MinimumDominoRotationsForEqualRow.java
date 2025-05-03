package org.jwolfe.quetzal.algorithms.lc;

public class MinimumDominoRotationsForEqualRow {
    class Solution {
        public int minDominoRotations(int[] A, int[] B) {
            if (A == null || A.length == 0 || B == null || A.length != B.length) {
                return -1;
            }

            int n = A.length;

            // Candidates
            int c1 = A[0];
            int c2 = B[0];

            int rotations = minDominoRotations(A, B, c1);
            if (rotations != -1) {
                return rotations;
            }

            return minDominoRotations(A, B, c2);
        }

        public int minDominoRotations(int[] A, int[] B, int val) {
            int rotationsA = 0;
            int rotationsB = 0;

            for (int i = 0; i < A.length; i++) {
                int x = A[i];
                int y = B[i];

                if (x != val && y != val) {
                    return -1;
                }

                if (x != val) {
                    rotationsA++;
                }

                if (y != val) {
                    rotationsB++;
                }
            }

            return Math.min(rotationsA, rotationsB);
        }
    }

    class Solution_Correct_1 {
        public int minDominoRotations(int[] A, int[] B) {
            if (A == null || B == null || A.length == 0 || B.length != A.length) {
                return -1;
            }

            int rotations = Integer.MAX_VALUE;
            for (int i = 1; i <= 6; i++) {
                int rotationsForA = rotationsForEqualRow(A, B, i);
                int rotationsForB = rotationsForEqualRow(B, A, i);

                if (rotationsForA != -1) {
                    rotations = Math.min(rotations, rotationsForA);
                }

                if (rotationsForB != -1) {
                    rotations = Math.min(rotations, rotationsForB);
                }
            }

            return rotations != Integer.MAX_VALUE ? rotations : -1;
        }

        private int rotationsForEqualRow(int[] A, int[] B, int val) {
            int rotations = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] == val) {
                    continue;
                } else if (B[i] == val) {
                    rotations++;
                } else {
                    return -1;
                }
            }

            return rotations;
        }
    }

    class Solution_Incorrect {
        public int minDominoRotations(int[] A, int[] B) {
            if (A == null || A.length == 0 || B == null || A.length != B.length) {
                return -1;
            }

            int n = A.length;
            int candidateA = A[0];
            int candidateB = B[0];

            int rotationsA1 = 0;
            int rotationsA2 = 0;
            int rotationsB1 = 0;
            int rotationsB2 = 0;

            boolean feasibleA = true;
            boolean feasibleB = true;

            for (int i = 1; i < n; i++) {
                int x = A[i];
                int y = B[i];

                if (x != candidateA && y != candidateA) {
                    feasibleA = false;
                }

                if (x != candidateB && y != candidateB) {
                    feasibleB = false;
                }

                if (!feasibleA && !feasibleB) {
                    return -1;
                }

                if (x != candidateA) {
                    rotationsA1++;
                } else {
                    rotationsA2++;
                }

                if (y != candidateB) {
                    rotationsB1++;
                } else {
                    rotationsB2++;
                }
            }

            if (feasibleA && feasibleB) {
                return Math.min(rotationsA1, Math.min(rotationsA2, Math.min(rotationsB1, rotationsB2)));
            }

            return feasibleA ? Math.min(rotationsA1, rotationsA2) : Math.min(rotationsB1, rotationsB2);
        }
    }
}

//    1007. Minimum Domino Rotations For Equal Row
//    Medium
//    In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
//
//    We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
//
//    Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.
//
//    If it cannot be done, return -1.
//
//
//
//    Example 1:
//
//
//    Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
//    Output: 2
//    Explanation:
//    The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
//    If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
//    Example 2:
//
//    Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
//    Output: -1
//    Explanation:
//    In this case, it is not possible to rotate the dominoes to make one row of values equal.
//
//
//    Constraints:
//
//    2 <= tops.length <= 2 * 104
//    bottoms.length == tops.length
//    1 <= tops[i], bottoms[i] <= 6