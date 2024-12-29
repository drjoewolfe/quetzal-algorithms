package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class NumberOfWaysToFormATargetStringGivenADictionary {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numWays(String[] words, String target) {
            if (words == null || words.length == 0 || target == null || target.length() == 0) {
                return -1;
            }

            int wordLength = words[0].length();
            int targetLength = target.length();

            int[][] frequency = new int[wordLength][26];
            for (String word : words) {
                for (int i = 0; i < wordLength; i++) {
                    char c = word.charAt(i);
                    frequency[i][c - 'a']++;
                }
            }

            int[][] dp = new int[wordLength][targetLength];
            for (int[] row : dp) {
                Arrays.fill(row, -1);
            }

            return (int) numWays(words, target, wordLength, targetLength, 0, 0, dp, frequency);
        }

        private long numWays(String[] words, String target, int wordLength, int targetLength, int wordIndex, int targetIndex, int[][] dp, int[][] frequency) {
            if (targetIndex == targetLength) {
                return 1;
            }

            if (wordIndex == wordLength || wordLength - wordIndex < targetLength - targetIndex) {
                return 0;
            }

            if (dp[wordIndex][targetIndex] != -1) {
                return dp[wordIndex][targetIndex];
            }

            long ways = 0;

            // Do not match current
            ways += numWays(words, target, wordLength, targetLength, wordIndex + 1, targetIndex, dp, frequency);

            // Match current
            char c = target.charAt(targetIndex);
            int pos = c - 'a';
            ways += frequency[wordIndex][pos]
                    * numWays(words, target, wordLength, targetLength, wordIndex + 1, targetIndex + 1, dp, frequency);

            ways %= MOD;
            dp[wordIndex][targetIndex] = (int) ways;

            return ways;
        }
    }
}

//    1639. Number of Ways to Form a Target String Given a Dictionary
//    Hard
//    You are given a list of strings of the same length words and a string target.
//
//    Your task is to form target using the given words under the following rules:
//
//    target should be formed from left to right.
//    To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
//    Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
//    Repeat the process until you form the string target.
//    Notice that you can use multiple characters from the same string in words provided the conditions above are met.
//
//    Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: words = ["acca","bbbb","caca"], target = "aba"
//    Output: 6
//    Explanation: There are 6 ways to form target.
//    "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
//    "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
//    "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
//    "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
//    "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
//    "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
//    Example 2:
//
//    Input: words = ["abba","baab"], target = "bab"
//    Output: 4
//    Explanation: There are 4 ways to form target.
//    "bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
//    "bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
//    "bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
//    "bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
//
//
//    Constraints:
//
//    1 <= words.length <= 1000
//    1 <= words[i].length <= 1000
//    All strings in words have the same length.
//    1 <= target.length <= 1000
//    words[i] and target contain only lowercase English letters.