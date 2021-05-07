package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class DeleteOperationForTwoStrings {
    class Solution {
        public int minDistance(String word1, String word2) {
            if(word1 == null && word2 == null) {
                return 0;
            }

            if(word1.length() == 0 && word2.length() == 0) {
                return 0;
            }

            if(word1 == null || word1.length() == 0) {
                return word2.length();
            }

            if(word2 == null || word2.length() == 0) {
                return word1.length();
            }

            int m = word1.length();
            int n = word2.length();
            int[][] memo = new int[m][n];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return (m + n) - 2 * lcs(word1, word2, m - 1, n - 1, memo);
        }

        private int lcs(String word1, String word2, int i, int j, int[][] memo) {
            if(i < 0 || j < 0) {
                return 0;
            }

            if(memo[i][j] != -1) {
                return memo[i][j];
            }

            int val = 0;
            if(word1.charAt(i) == word2.charAt(j)) {
                val = 1 + lcs(word1, word2, i - 1, j - 1, memo);
            } else {
                val = Math.max(
                        lcs(word1, word2, i - 1, j, memo),
                        lcs(word1, word2, i, j - 1, memo)
                );
            }

            memo[i][j] = val;
            return val;
        }
    }

    class Solution_LCS_Recursive {
        public int minDistance(String word1, String word2) {
            if(word1 == null && word2 == null) {
                return 0;
            }

            if(word1.length() == 0 && word2.length() == 0) {
                return 0;
            }

            if(word1 == null || word1.length() == 0) {
                return word2.length();
            }

            if(word2 == null || word2.length() == 0) {
                return word1.length();
            }

            int m = word1.length();
            int n = word2.length();

            return (m + n) - 2 * lcs(word1, word2, m - 1, n - 1);
        }

        private int lcs(String word1, String word2, int i, int j) {
            if(i < 0 || j < 0) {
                return 0;
            }

            if(word1.charAt(i) == word2.charAt(j)) {
                return 1 + lcs(word1, word2, i - 1, j - 1);
            } else {
                return Math.max(
                        lcs(word1, word2, i - 1, j),
                        lcs(word1, word2, i, j - 1)
                );
            }
        }
    }

    class Solution_Brute {
        int min;

        public int minDistance(String word1, String word2) {
            if(word1 == null && word2 == null) {
                return 0;
            }

            if(word1.length() == 0 && word2.length() == 0) {
                return 0;
            }

            if(word1 == null || word1.length() == 0) {
                return word2.length();
            }

            if(word2 == null || word2.length() == 0) {
                return word1.length();
            }

            StringBuilder builder1 = new StringBuilder(word1);
            StringBuilder builder2 = new StringBuilder(word2);

            min = word1.length() + word2.length();
            minDistance(builder1, builder2, 0);

            return min;
        }

        private void minDistance(StringBuilder builder1, StringBuilder builder2, int steps) {
            if(builder1.toString().equals(builder2.toString())) {
                min = Math.min(min, steps);
                return;
            }

            if(builder1.length() == builder2.length()) {
                int n = builder1.length();
                for(int i = 0; i < n; i++) {
                    char ci = builder1.charAt(i);
                    builder1.deleteCharAt(i);
                    for(int j = 0; j < n; j++) {
                        char cj = builder2.charAt(j);
                        builder2.deleteCharAt(j);

                        minDistance(builder1, builder2, steps + 2);
                        builder2.insert(j, cj);
                    }

                    builder1.insert(i, ci);
                }
            } else {
                StringBuilder larger = builder1.length() > builder2.length() ? builder1 : builder2;
                StringBuilder smaller = builder1.length() > builder2.length() ? builder2 : builder1;

                for(int i = 0; i < larger.length(); i++) {
                    char ci = larger.charAt(i);
                    larger.deleteCharAt(i);
                    minDistance(larger, smaller, steps + 1);

                    larger.insert(i, ci);
                }
            }
        }
    }

// "sea"
// "eat"
}

//    583. Delete Operation for Two Strings
//    Medium
//    Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
//
//    In one step, you can delete exactly one character in either string.
//
//
//
//    Example 1:
//
//    Input: word1 = "sea", word2 = "eat"
//    Output: 2
//    Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
//    Example 2:
//
//    Input: word1 = "leetcode", word2 = "etco"
//    Output: 4
//
//
//    Constraints:
//
//    1 <= word1.length, word2.length <= 500
//    word1 and word2 consist of only lowercase English letters.