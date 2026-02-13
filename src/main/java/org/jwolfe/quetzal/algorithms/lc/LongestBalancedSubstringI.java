package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class LongestBalancedSubstringI {
    class Solution {
        public int longestBalanced(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int[] frequencies = new int[26];
            int maxLength = 0;
            for (int i = 0; i < n; i++) {
                Arrays.fill(frequencies, 0);
                for (int j = i; j < n; j++) {
                    char c = s.charAt(j);
                    frequencies[c - 'a']++;
                    if (isBalanced(frequencies)) {
                        int length = j - i + 1;
                        maxLength = Math.max(maxLength, length);
                    }
                }
            }

            return maxLength;
        }

        private boolean isBalanced(int[] frequencies) {
            int frequency = 0;
            for (int i = 0; i < 26; i++) {
                if (frequencies[i] != 0) {
                    if (frequency == 0) {
                        frequency = frequencies[i];
                    } else if (frequency != frequencies[i]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}

//    3713. Longest Balanced Substring I
//    Medium
//    You are given a string s consisting of lowercase English letters.
//
//    A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
//
//    Return the length of the longest balanced substring of s.
//
//
//
//    Example 1:
//
//    Input: s = "abbac"
//
//    Output: 4
//
//    Explanation:
//
//    The longest balanced substring is "abba" because both distinct characters 'a' and 'b' each appear exactly 2 times.
//
//    Example 2:
//
//    Input: s = "zzabccy"
//
//    Output: 4
//
//    Explanation:
//
//    The longest balanced substring is "zabc" because the distinct characters 'z', 'a', 'b', and 'c' each appear exactly 1 time.​​​​​​​
//
//    Example 3:
//
//    Input: s = "aba"
//
//    Output: 2
//
//    Explanation:
//
//    ​​​​​​​One of the longest balanced substrings is "ab" because both distinct characters 'a' and 'b' each appear exactly 1 time. Another longest balanced substring is "ba".
//
//
//
//    Constraints:
//
//    1 <= s.length <= 1000
//    s consists of lowercase English letters.