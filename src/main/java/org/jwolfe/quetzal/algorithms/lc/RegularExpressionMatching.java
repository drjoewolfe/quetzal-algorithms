package org.jwolfe.quetzal.algorithms.lc;

public class RegularExpressionMatching {
    class Solution {
        public boolean isMatch(String s, String p) {
            if(s == null && p == null) {
                return true;
            }

            if(s == null || p == null) {
                return false;
            }

            return isMatch(s, p, 0, 0);
        }

        private boolean isMatch(String s, String p, int si, int pi) {
            if(pi == p.length()) {
                return si == s.length();
            }

            boolean firstMatched = (si < s.length()) && ((s.charAt(si) == p.charAt(pi)) || p.charAt(pi) == '.');

            if(pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
                return (firstMatched && isMatch(s, p, si + 1, pi))
                        || isMatch(s, p, si, pi + 2);
            } else {
                return (firstMatched && isMatch(s, p, si + 1, pi + 1));
            }
        }
    }

// "aa"
// "a"

// "aa"
// "a*"
}

//    10. Regular Expression Matching
//    Hard
//    Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
//
//    '.' Matches any single character.​​​​
//    '*' Matches zero or more of the preceding element.
//    The matching should cover the entire input string (not partial).
//
//
//
//    Example 1:
//
//    Input: s = "aa", p = "a"
//    Output: false
//    Explanation: "a" does not match the entire string "aa".
//    Example 2:
//
//    Input: s = "aa", p = "a*"
//    Output: true
//    Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
//    Example 3:
//
//    Input: s = "ab", p = ".*"
//    Output: true
//    Explanation: ".*" means "zero or more (*) of any character (.)".
//
//
//    Constraints:
//
//    1 <= s.length <= 20
//    1 <= p.length <= 20
//    s contains only lowercase English letters.
//    p contains only lowercase English letters, '.', and '*'.
//    It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.