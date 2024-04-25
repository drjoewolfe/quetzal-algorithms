package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class LongestIdealSubsequence {
    class Solution {
        public int longestIdealString(String s, int k) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();

            int maxLength = 1;
            int[] dp = new int[26];
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                int j = c - 'a';

                int left = Math.max(j - k, 0);
                int right = Math.min(j + k, 25);

                int maxPrevLength = 0;
                for(int index = left; index <= right; index++) {
                    maxPrevLength = Math.max(maxPrevLength, dp[index]);
                }

                dp[j] = 1 + maxPrevLength;
            }

            for(int i = 0; i < 26; i++) {
                maxLength = Math.max(maxLength, dp[i]);
            }

            return maxLength;
        }
    }

    class Solution_TLE {
        public int longestIdealString(String s, int k) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();

            int maxLength = 1;
            int[] dp = new int[n];
            Arrays.fill(dp, 1);

            for(int i = 1; i < n; i++) {
                char a = s.charAt(i);

                for(int j = 0; j < i; j++) {
                    char b = s.charAt(j);
                    if(Math.abs(a - b) <= k) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        maxLength = Math.max(maxLength, dp[i]);
                    }
                }
            }

            return maxLength;
        }
    }

    class Solution_Correct_1 {
        public int longestIdealString(String s, int k) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();

            // For every alphabet; the length of the largest subsequence ending at a character
            int[] dp = new int[26];
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int j = c - 'a';

                int maxLength = 0;
                for(int index = Math.max(j - k, 0); index < Math.min(j + k + 1, 26); index++) {
                    maxLength = Math.max(maxLength, dp[index]);
                }

                dp[j] = 1 + maxLength;
            }

            int max = 1;
            for(int i = 0; i < 26; i++) {
                max = Math.max(max, dp[i]);
            }

            return max;
        }
    }

    class Solution_LIS_N2 {
        public int longestIdealString(String s, int k) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            int maxLength = 1;
            for(int i = 1; i < n; i++) {
                char c = s.charAt(i);
                for(int j = i - 1; j >= 0; j--) {
                    char d = s.charAt(j);
                    if(Math.abs(d - c) <= k) {
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                        maxLength = Math.max(maxLength, dp[i]);
                    }
                }
            }

            return maxLength;
        }
    }


// "acfgbd"
// 2

// "pvjcci"
// 4
}

//    2370. Longest Ideal Subsequence
//    Medium
//    You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:
//
//    t is a subsequence of the string s.
//    The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
//    Return the length of the longest ideal string.
//
//    A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
//
//    Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.
//
//
//
//    Example 1:
//
//    Input: s = "acfgbd", k = 2
//    Output: 4
//    Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
//    Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.
//    Example 2:
//
//    Input: s = "abcd", k = 3
//    Output: 4
//    Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    0 <= k <= 25
//    s consists of lowercase English letters.
