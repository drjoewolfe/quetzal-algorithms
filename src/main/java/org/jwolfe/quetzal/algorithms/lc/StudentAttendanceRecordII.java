package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class StudentAttendanceRecordII {
    class Solution {
        private int MOD = 1_000_000_007;

        public int checkRecord(int n) {
            if(n < 1) {
                return 0;
            }

            int[][][] memo = new int[n + 1][2][3];
            for(int[][] grid : memo) {
                for(int[] row : grid) {
                    Arrays.fill(row, -1);
                }
            }

            return checkRecord(n, 0, 0, memo);
        }

        private int checkRecord(int n, int absences, int consecutiveLates, int[][][] memo) {
            if(absences == 2 || consecutiveLates == 3) {
                return 0;
            }

            if(n == 0) {
                return 1;
            }

            if(memo[n][absences][consecutiveLates] != -1) {
                return memo[n][absences][consecutiveLates];
            }

            int count = 0;

            // P
            count += checkRecord(n - 1, absences, 0, memo);
            count %= MOD;

            // A
            count += checkRecord(n - 1, absences + 1, 0, memo);
            count %= MOD;

            // L
            count += checkRecord(n - 1, absences, consecutiveLates + 1, memo);
            count %= MOD;

            memo[n][absences][consecutiveLates] = count;
            return count;
        }
    }

    class Solution_TLE {
        private int MOD = 1_000_000_007;

        public int checkRecord(int n) {
            if(n < 1) {
                return 0;
            }

            return checkRecord(n, 0, 0);
        }

        private int checkRecord(int n, int absences, int consecutiveLates) {
            if(absences == 2 || consecutiveLates == 3) {
                return 0;
            }

            if(n == 0) {
                return 1;
            }

            int count = 0;

            // P
            count += checkRecord(n - 1, absences, 0);
            count %= MOD;

            // A
            count += checkRecord(n - 1, absences + 1, 0);
            count %= MOD;

            // L
            count += checkRecord(n - 1, absences, consecutiveLates + 1);
            count %= MOD;

            return count;
        }
    }
}

//    552. Student Attendance Record II
//    Hard
//    An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
//
//    'A': Absent.
//    'L': Late.
//    'P': Present.
//    Any student is eligible for an attendance award if they meet both of the following criteria:
//
//    The student was absent ('A') for strictly fewer than 2 days total.
//    The student was never late ('L') for 3 or more consecutive days.
//    Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: n = 2
//    Output: 8
//    Explanation: There are 8 records with length 2 that are eligible for an award:
//    "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//    Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
//    Example 2:
//
//    Input: n = 1
//    Output: 3
//    Example 3:
//
//    Input: n = 10101
//    Output: 183236316
//
//
//    Constraints:
//
//    1 <= n <= 105