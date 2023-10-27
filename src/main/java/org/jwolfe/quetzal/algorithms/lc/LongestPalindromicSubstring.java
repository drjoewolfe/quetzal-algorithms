package org.jwolfe.quetzal.algorithms.lc;

public class LongestPalindromicSubstring {
    class Solution {
        public String longestPalindrome(String s) {
            if(s == null || s.length() < 1) {
                return "";
            }

            int n = s.length();
            boolean[][] dp = new boolean[n][n];

            int maxLength = 1;
            int maxStartIndex = 0;

            for(int i = 0; i < n; i++) {
                dp[i][i] = true;
            }

            for(int i = 0; i < n - 1; i++) {
                if(s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;

                    maxLength = 2;
                    maxStartIndex = i;
                }
            }

            for(int l = 3; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    if(s.charAt(i) == s.charAt(j)
                            && dp[i + 1][j - 1]) {
                        dp[i][j] = true;

                        maxLength = l;
                        maxStartIndex = i;
                    }
                }
            }

            return s.substring(maxStartIndex, maxStartIndex + maxLength);
        }
    }

    class Solution_Correct_1 {
        public String longestPalindrome(String s) {
            if(s == null || s.length() == 0) {
                return "";
            }

            int n = s.length();
            int[][] dp = new int[n][n];

            int maxLength = 1;
            int maxStart = 0;

            // Every substring of length 1 is a palindrome
            for(int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }

            // Process for length 2
            for(int i = 0; i <= n - 2; i++) {
                char a = s.charAt(i);
                char b = s.charAt(i + 1);

                if(a == b) {
                    dp[i][i + 1] = 1;

                    if(maxLength == 1) {
                        maxLength = 2;
                        maxStart = i;
                    }

                }
            }

            for(int l = 3; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    char a = s.charAt(i);
                    char b = s.charAt(j);
                    if(a == b && dp[i + 1][j - 1] == 1) {
                        dp[i][j] = 1;

                        if(l > maxLength) {
                            maxLength = l;
                            maxStart = i;
                        }
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }

            return s.substring(maxStart, maxStart + maxLength);
        }

        private void printMatrix(int[][] grid) {
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println();
        }

        public int longestPalindromeLength(String s) {
            if(s == null || s.length() == 0) {
                return -1;
            }

            int n = s.length();
            int[][] dp = new int[n][n];

            for(int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }

            for(int l = 2; l <= n; l++) {
                for(int i = 0; i + l < n; i++) {
                    int j = i + l;

                    char a = s.charAt(i);
                    char b = s.charAt(j);

                    if(a == b) {
                        dp[i][j] = Math.max(1 + dp[i + 1][j - 1],
                                Math.max(dp[i + 1][j],
                                        dp[i][j - 1]));

                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j],
                                dp[i][j - 1]);
                    }
                }
            }

            int lengthOfLongestPalondrome = dp[0][n - 1];

            return lengthOfLongestPalondrome;
        }
    }

// "babad"
// "ccc"
}

//    5. Longest Palindromic Substring
//    Medium
//    Given a string s, return the longest palindromic substring in s.
//
//
//
//    Example 1:
//
//    Input: s = "babad"
//    Output: "bab"
//    Explanation: "aba" is also a valid answer.
//    Example 2:
//
//    Input: s = "cbbd"
//    Output: "bb"
//
//
//    Constraints:
//
//    1 <= s.length <= 1000
//    s consist of only digits and English letters.