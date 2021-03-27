package org.jwolfe.quetzal.algorithms.lc;

public class PalindromicSubstrings {
    class Solution {
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();

            int[][] dp = new int[n][n];
            int count = 0;

            // Length 1
            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
                count++;
            }

            // Length 2
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = 1;
                    count++;
                }
            }

            // Length 3 & more
            for (int l = 3; l <= n; l++) {
                for (int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) {
                        dp[i][j] = 1;
                        count++;
                    }
                }
            }

            return count;
        }
    }
}

//    647. Palindromic Substrings
//    Medium
//    Given a string, your task is to count how many palindromic substrings in this string.
//
//    The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
//
//    Example 1:
//
//    Input: "abc"
//    Output: 3
//    Explanation: Three palindromic strings: "a", "b", "c".
//
//
//    Example 2:
//
//    Input: "aaa"
//    Output: 6
//    Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
//
//
//    Note:
//
//    The input string length won't exceed 1000.
