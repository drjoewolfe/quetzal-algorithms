package org.jwolfe.quetzal.algorithms.lc;

public class WildcardMatching {
    class Solution {
        public boolean isMatch(String s, String p) {
            Boolean[][] memo = new Boolean[s.length() + 1][p.length()];
            return isMatch(s, p, 0, 0, memo);
        }

        private boolean isMatch(String s, String p, int si, int pi, Boolean[][] memo) {
            if(pi == p.length()) {
                return si == s.length();
            }

            if(memo[si][pi] != null) {
                return memo[si][pi];
            }

            if(si == s.length()) {
                if(p.charAt(pi) == '*') {
                    while(pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
                        pi++;
                    }
                }

                if(pi == p.length() - 1 && p.charAt(pi) == '*') {
                    memo[si][pi] = true;
                } else {
                    memo[si][pi] = false;
                }
            } else {

                char sc = s.charAt(si);
                char pc = p.charAt(pi);

                if(sc == pc || pc == '?') {
                    memo[si][pi] = isMatch(s, p, si + 1, pi + 1, memo);
                } else if(pc == '*') {
                    while(pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
                        pi++;
                    }

                    memo[si][pi] = isMatch(s, p, si + 1, pi, memo)
                            || isMatch(s, p, si, pi + 1, memo);
                } else {
                    memo[si][pi] = false;
                }
            }

            return memo[si][pi];
        }
    }

    class Solution_Recursive_TLE {
        public boolean isMatch(String s, String p) {
            return isMatch(s, p, 0, 0);
        }

        private boolean isMatch(String s, String p, int si, int pi) {
            if(pi == p.length()) {
                return si == s.length();
            }

            if(si == s.length()) {
                if(p.charAt(pi) == '*') {
                    while(pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
                        pi++;
                    }
                }

                if(pi == p.length() - 1 && p.charAt(pi) == '*') {
                    return true;
                }

                return false;
            }

            char sc = s.charAt(si);
            char pc = p.charAt(pi);

            if(sc == pc || pc == '?') {
                return isMatch(s, p, si + 1, pi + 1);
            }

            if(pc == '*') {
                while(pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
                    pi++;
                }

                return isMatch(s, p, si + 1, pi)
                        || isMatch(s, p, si, pi + 1);
            }

            return false;
        }
    }

// "aa"
// "a"

// "aa"
// "*"

// "acdcb"
// "a*c?b"

// ""
// "******"

// "a"
// "ab*"

// "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb"
// "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"
}

//    44. Wildcard Matching
//    Hard
//    Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
//
//    '?' Matches any single character.
//    '*' Matches any sequence of characters (including the empty sequence).
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
//    Input: s = "aa", p = "*"
//    Output: true
//    Explanation: '*' matches any sequence.
//    Example 3:
//
//    Input: s = "cb", p = "?a"
//    Output: false
//    Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
//
//
//    Constraints:
//
//    0 <= s.length, p.length <= 2000
//    s contains only lowercase English letters.
//    p contains only lowercase English letters, '?' or '*'.