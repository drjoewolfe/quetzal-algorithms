package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class LongestPalindromicSubsequence {
    class Solution {
        public int longestPalindromeSubseq(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int[][] dp = new int[n][n];

            for(int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }

            for(int i = 0; i < n - 1; i++) {
                int j = i + 1;

                dp[i][j] = (s.charAt(i) == s.charAt(j)) ? 2 : 1;
            }

            for(int l = 3; l <= n; l++) {
                for(int i = 0; i <= n - l; i++) {
                    int j = i + l - 1;

                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }

            return dp[0][n - 1];
        }
    }

    class Solution_Memoized {
        public int longestPalindromeSubseq(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int[][] cache = new int[n][n];
            for(int[] row : cache) {
                Arrays.fill(row, -1);
            }

            return longestPalindromeSubseq(s, 0, n - 1, cache);
        }

        private int longestPalindromeSubseq(String s, int left, int right, int[][] cache) {
            if(left > right) {
                return 0;
            }

            if(left == right) {
                return 1;
            }

            if(cache[left][right] != -1) {
                return cache[left][right];
            }

            char l = s.charAt(left);
            char r = s.charAt(right);

            int maxLength = 0;
            if(l == r) {
                maxLength = 2 + longestPalindromeSubseq(s, left + 1, right - 1, cache);
            } else {
                maxLength = Math.max(
                        longestPalindromeSubseq(s, left + 1, right, cache),
                        longestPalindromeSubseq(s, left, right - 1, cache)
                );
            }

            cache[left][right] = maxLength;
            return maxLength;
        }
    }

    class Solution_Recursive_TLE {
        public int longestPalindromeSubseq(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            return longestPalindromeSubseq(s, 0, s.length() - 1);
        }

        private int longestPalindromeSubseq(String s, int left, int right) {
            if(left > right) {
                return 0;
            }

            if(left == right) {
                return 1;
            }

            char l = s.charAt(left);
            char r = s.charAt(right);

            int maxLength = 0;
            if(l == r) {
                maxLength = 2 + longestPalindromeSubseq(s, left + 1, right - 1);
            } else {
                maxLength = Math.max(
                        longestPalindromeSubseq(s, left + 1, right),
                        longestPalindromeSubseq(s, left, right - 1)
                );
            }

            return maxLength;
        }
    }

// "bbbab"
// "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew"
}

//    516. Longest Palindromic Subsequence
//    Medium
//    Given a string s, find the longest palindromic subsequence's length in s.
//
//    A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
//
//
//
//    Example 1:
//
//    Input: s = "bbbab"
//    Output: 4
//    Explanation: One possible longest palindromic subsequence is "bbbb".
//    Example 2:
//
//    Input: s = "cbbd"
//    Output: 2
//    Explanation: One possible longest palindromic subsequence is "bb".
//
//
//    Constraints:
//
//    1 <= s.length <= 1000
//    s consists only of lowercase English letters.