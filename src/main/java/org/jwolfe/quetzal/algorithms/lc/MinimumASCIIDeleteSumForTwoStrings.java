package org.jwolfe.quetzal.algorithms.lc;

public class MinimumASCIIDeleteSumForTwoStrings {
    class Solution {
        public int minimumDeleteSum(String s1, String s2) {
            int m = s1.length();
            int n = s2.length();

            Integer[][] memo = new Integer[m][n];
            return minimumDeleteSum(s1, s2, m, n, 0, 0, memo);
        }

        private int minimumDeleteSum(String s1, String s2, int m, int n, int i, int j, Integer[][] memo) {
            if (i >= m && j >= n) {
                return 0;
            }

            if (i >= m) {
                return (int) s2.charAt(j) + minimumDeleteSum(s1, s2, m, n, i, j + 1, memo);
            }

            if (j >= n) {
                return (int) s1.charAt(i) + minimumDeleteSum(s1, s2, m, n, i + 1, j, memo);
            }

            if (memo[i][j] != null) {
                return memo[i][j];
            }

            char c1 = s1.charAt(i);
            char c2 = s2.charAt(j);

            if (c1 == c2) {
                return minimumDeleteSum(s1, s2, m, n, i + 1, j + 1, memo);
            }

            // Delete c1
            int deleteC1Sum = (int) c1 + minimumDeleteSum(s1, s2, m, n, i + 1, j, memo);

            // Delete c2
            int deleteC2Sum = (int) c2 + minimumDeleteSum(s1, s2, m, n, i, j + 1, memo);

            return memo[i][j] = Math.min(deleteC1Sum, deleteC2Sum);
        }
    }

    class Solution_Recursive_TLE {
        public int minimumDeleteSum(String s1, String s2) {
            int m = s1.length();
            int n = s2.length();

            return minimumDeleteSum(s1, s2, m, n, 0, 0);
        }

        private int minimumDeleteSum(String s1, String s2, int m, int n, int i, int j) {
            if (i >= m && j >= n) {
                return 0;
            }

            if (i >= m) {
                return (int) s2.charAt(j) + minimumDeleteSum(s1, s2, m, n, i, j + 1);
            }

            if (j >= n) {
                return (int) s1.charAt(i) + minimumDeleteSum(s1, s2, m, n, i + 1, j);
            }

            char c1 = s1.charAt(i);
            char c2 = s2.charAt(j);

            if (c1 == c2) {
                return minimumDeleteSum(s1, s2, m, n, i + 1, j + 1);
            }

            // Delete c1
            int deleteC1Sum = (int) c1 + minimumDeleteSum(s1, s2, m, n, i + 1, j);

            // Delete c2
            int deleteC2Sum = (int) c2 + minimumDeleteSum(s1, s2, m, n, i, j + 1);

            return Math.min(deleteC1Sum, deleteC2Sum);
        }
    }
}

//    712. Minimum ASCII Delete Sum for Two Strings
//    Medium
//    Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
//
//
//
//    Example 1:
//
//    Input: s1 = "sea", s2 = "eat"
//    Output: 231
//    Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
//    Deleting "t" from "eat" adds 116 to the sum.
//    At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
//    Example 2:
//
//    Input: s1 = "delete", s2 = "leet"
//    Output: 403
//    Explanation: Deleting "dee" from "delete" to turn the string into "let",
//    adds 100[d] + 101[e] + 101[e] to the sum.
//    Deleting "e" from "leet" adds 101[e] to the sum.
//    At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
//    If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
//
//
//    Constraints:
//
//    1 <= s1.length, s2.length <= 1000
//    s1 and s2 consist of lowercase English letters.