package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DistinctSubsequencesII {
    class Solution {
        private int MOD = 1_000_000_007;

        public int distinctSubseqII(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int[] lastIndex = new int[26];
            Arrays.fill(lastIndex, -1);

            int n = s.length();
            int[] dp = new int[n + 1];

            dp[0] = 1;

            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                int cIndex = c - 'a';

                dp[i + 1] = dp[i] * 2;
                dp[i + 1] %= MOD;

                if (lastIndex[cIndex] >= 0) {
                    dp[i + 1] -= dp[lastIndex[cIndex]];
                    dp[i + 1] %= MOD;
                }

                lastIndex[cIndex] = i;
            }

            dp[n]--;
            if (dp[n] < 0) {
                dp[n] += MOD;
            }

            return dp[n];
        }
    }

    class Solution_TLE {
        private int MOD = 1_000_000_007;

        public int distinctSubseqII(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            return distinctSubseqII(s, 0, new StringBuilder(), new HashSet<>());
        }

        private int distinctSubseqII(String s, int index, StringBuilder curr, Set<String> subsequences) {
            if (index == s.length()) {
                String subsequence = curr.toString();
                if (subsequence != null && subsequence != "" && !subsequences.contains(subsequence)) {
                    subsequences.add(subsequence);
                    return 1;
                } else {
                    return 0;
                }
            }

            int count = 0;

            // Include index
            char c = s.charAt(index);
            curr.append(c);
            count += distinctSubseqII(s, index + 1, curr, subsequences);
            count %= MOD;
            curr.deleteCharAt(curr.length() - 1);

            // Exclude index
            count += distinctSubseqII(s, index + 1, curr, subsequences);
            count %= MOD;

            return count;
        }
    }
}

//    940. Distinct Subsequences II
//    Hard
//    Given a string s, return the number of distinct non-empty subsequences of s. Since the answer may be very large, return it modulo 109 + 7.
//
//    A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not.
//
//
//    Example 1:
//
//    Input: s = "abc"
//    Output: 7
//    Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
//    Example 2:
//
//    Input: s = "aba"
//    Output: 6
//    Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "aba".
//    Example 3:
//
//    Input: s = "aaa"
//    Output: 3
//    Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
//
//
//    Constraints:
//
//    1 <= s.length <= 2000
//    s consists of lowercase English letters.