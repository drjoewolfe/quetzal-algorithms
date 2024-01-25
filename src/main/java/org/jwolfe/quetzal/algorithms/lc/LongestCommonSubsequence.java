package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class LongestCommonSubsequence {
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
                return 0;
            }

            int m = text1.length();
            int n = text2.length();

            int[][] dp = new int[m + 1][n + 1];
            for(int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {
                    if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(
                                dp[i - 1][j],
                                dp[i][j - 1]
                        );
                    }
                }
            }

            return dp[m][n];
        }
    }

    class Solution_Correct_1 {
        public int longestCommonSubsequence(String text1, String text2) {
            if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
                return 0;
            }

            int m = text1.length();
            int n = text2.length();

            int[][] dp = new int[m + 1][n + 1];
            for(int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {
                    if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(
                                dp[i - 1][j],
                                dp[i][j - 1]
                        );
                    }
                }
            }

            return dp[m][n];
        }
    }

    class Solution_Memoized {
        public int longestCommonSubsequence(String text1, String text2) {
            if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
                return 0;
            }

            int m = text1.length();
            int n = text2.length();

            int[][] memo = new int[m][n];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return longestCommonSubsequence(text1, text2, m - 1, n - 1, memo);
        }

        private int longestCommonSubsequence(String text1, String text2, int index1, int index2, int[][] memo) {
            if(index1 < 0 || index2 < 0) {
                return 0;
            }

            if(memo[index1][index2] != -1) {
                return memo[index1][index2];
            }

            int result = 0;
            if(text1.charAt(index1) == text2.charAt(index2)) {
                result = 1 + longestCommonSubsequence(text1, text2, index1 - 1, index2 - 1, memo);
            } else {
                result = Math.max(
                        longestCommonSubsequence(text1, text2, index1, index2 - 1, memo),
                        longestCommonSubsequence(text1, text2, index1 - 1, index2, memo)
                );
            }

            memo[index1][index2] = result;
            return result;
        }
    }

    class Solution_Recursive {
        public int longestCommonSubsequence(String text1, String text2) {
            if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
                return 0;
            }

            return longestCommonSubsequence(text1, text2, text1.length() - 1, text2.length() - 1);
        }

        private int longestCommonSubsequence(String text1, String text2, int index1, int index2) {
            if(index1 < 0 || index2 < 0) {
                return 0;
            }

            if(text1.charAt(index1) == text2.charAt(index2)) {
                return 1 + longestCommonSubsequence(text1, text2, index1 - 1, index2 - 1);
            } else {
                return Math.max(
                        longestCommonSubsequence(text1, text2, index1, index2 - 1),
                        longestCommonSubsequence(text1, text2, index1 - 1, index2)
                );
            }
        }
    }

    class Solution_DP_Correct_1 {
        public int longestCommonSubsequence(String text1, String text2) {
            if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
                return 0;
            }

            int length1 = text1.length();
            int length2 = text2.length();

            int[][] dp = new int[length1 + 1][length2 + 1];
            for(int i = 1; i <= length1; i++) {
                for(int j = 1; j <= length2; j++) {
                    if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = Math.max(1 + dp[i - 1][j - 1],
                                Math.max(dp[i - 1][j], dp[i][j - 1]));
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            return dp[length1][length2];
        }
    }

    class Solution_Memoized_Correct_1 {
        public int longestCommonSubsequence(String text1, String text2) {
            if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
                return 0;
            }

            int[][] memo = new int[text1.length()][text2.length()];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return longestCommonSubsequence(text1, text2, text1.length() - 1, text2.length() - 1, memo);
        }

        public int longestCommonSubsequence(String text1, String text2, int index1, int index2, int[][] memo) {
            if(index1 < 0 || index2 < 0) {
                return 0;
            }

            if(memo[index1][index2] != -1) {
                return memo[index1][index2];
            }

            if(text1.charAt(index1) == text2.charAt(index2)) {
                memo[index1][index2] = Math.max(
                        1 + longestCommonSubsequence(text1, text2, index1 - 1, index2 - 1, memo),
                        Math.max(
                                longestCommonSubsequence(text1, text2, index1, index2 - 1, memo),
                                longestCommonSubsequence(text1, text2, index1 - 1, index2, memo)
                        ));

            } else {
                memo[index1][index2] = Math.max(
                        longestCommonSubsequence(text1, text2, index1, index2 - 1, memo),
                        longestCommonSubsequence(text1, text2, index1 - 1, index2, memo)
                );
            }

            return memo[index1][index2];
        }
    }

    class Solution_Brute_Correct_1 {
        public int longestCommonSubsequence(String text1, String text2) {
            if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
                return 0;
            }

            return longestCommonSubsequence(text1, text2, text1.length() - 1, text2.length() - 1);
        }

        public int longestCommonSubsequence(String text1, String text2, int index1, int index2) {
            if(index1 < 0 || index2 < 0) {
                return 0;
            }

            if(text1.charAt(index1) == text2.charAt(index2)) {
                return Math.max(
                        1 + longestCommonSubsequence(text1, text2, index1 - 1, index2 - 1),
                        Math.max(
                                longestCommonSubsequence(text1, text2, index1, index2 - 1),
                                longestCommonSubsequence(text1, text2, index1 - 1, index2)
                        ));

            } else {
                return Math.max(
                        longestCommonSubsequence(text1, text2, index1, index2 - 1),
                        longestCommonSubsequence(text1, text2, index1 - 1, index2)
                );
            }
        }
    }

// "abcde"
// "ace"

// "pmjghexybyrgzczy"
// "hafcdqbgncrcbihkd"
}

//    1143. Longest Common Subsequence
//    Medium
//    Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
//
//    A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
//
//    For example, "ace" is a subsequence of "abcde".
//    A common subsequence of two strings is a subsequence that is common to both strings.
//
//
//
//    Example 1:
//
//    Input: text1 = "abcde", text2 = "ace"
//    Output: 3
//    Explanation: The longest common subsequence is "ace" and its length is 3.
//    Example 2:
//
//    Input: text1 = "abc", text2 = "abc"
//    Output: 3
//    Explanation: The longest common subsequence is "abc" and its length is 3.
//    Example 3:
//
//    Input: text1 = "abc", text2 = "def"
//    Output: 0
//    Explanation: There is no such common subsequence, so the result is 0.
//
//
//    Constraints:
//
//    1 <= text1.length, text2.length <= 1000
//    text1 and text2 consist of only lowercase English characters.