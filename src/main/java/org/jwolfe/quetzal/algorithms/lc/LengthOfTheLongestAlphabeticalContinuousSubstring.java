package org.jwolfe.quetzal.algorithms.lc;

public class LengthOfTheLongestAlphabeticalContinuousSubstring {
    class Solution {
        public int longestContinuousSubstring(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            char start = s.charAt(0);
            char prev = start;
            int startIndex = 0;
            int maxLength = 1;
            for(int i = 1; i < n; i++) {
                char curr = s.charAt(i);
                if(curr - prev != 1) {
                    int length = i - startIndex;
                    maxLength = Math.max(maxLength, length);

                    start = curr;
                    startIndex = i;
                }

                prev = curr;
            }

            maxLength = Math.max(maxLength, n - startIndex);
            return maxLength;
        }
    }

// "abacaba"
// "abcde"
}

//    2414. Length of the Longest Alphabetical Continuous Substring
//    Medium
//    An alphabetical continuous string is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string "abcdefghijklmnopqrstuvwxyz".
//
//    For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
//    Given a string s consisting of lowercase letters only, return the length of the longest alphabetical continuous substring.
//
//
//
//    Example 1:
//
//    Input: s = "abacaba"
//    Output: 2
//    Explanation: There are 4 distinct continuous substrings: "a", "b", "c" and "ab".
//    "ab" is the longest continuous substring.
//    Example 2:
//
//    Input: s = "abcde"
//    Output: 5
//    Explanation: "abcde" is the longest continuous substring.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of only English lowercase letters.