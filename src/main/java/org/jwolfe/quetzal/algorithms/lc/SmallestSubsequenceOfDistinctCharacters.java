package org.jwolfe.quetzal.algorithms.lc;

public class SmallestSubsequenceOfDistinctCharacters {
    class Solution {
        public String smallestSubsequence(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();

            // "How many occurrences of this character are still to be processed ?"
            int[] frequency = new int[26];

            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                frequency[index]++;
            }

            boolean[] visited = new boolean[26];

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                int index = c - 'a';

                if (!visited[index]) {
                    while (builder.length() > 0) {
                        char lastChar = builder.charAt(builder.length() - 1);
                        if (lastChar <= c) {
                            break;
                        }

                        int lastCharIndex = lastChar - 'a';
                        if (frequency[lastCharIndex] == 0) {
                            break;
                        }

                        visited[lastCharIndex] = false;
                        builder.deleteCharAt(builder.length() - 1);
                    }

                    visited[index] = true;
                    builder.append(c);
                }

                frequency[index]--;
            }

            return builder.toString();
        }
    }
}

//    1081. Smallest Subsequence of Distinct Characters
//    Medium
//    Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.
//
//
//
//    Example 1:
//
//    Input: s = "bcabc"
//    Output: "abc"
//    Example 2:
//
//    Input: s = "cbacdcbc"
//    Output: "acdb"
//
//
//    Constraints:
//
//    1 <= s.length <= 1000
//    s consists of lowercase English letters.
//
//
//    Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/