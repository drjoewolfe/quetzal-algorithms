package org.jwolfe.quetzal.algorithms.lc;

public class InterleavingString {
    class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            if(s1 == null && s2 == null && s3 == null) {
                return true;
            }

            if(s1 == null || s2 == null || s3 == null) {
                return false;
            }

            if(s1.length() + s2.length() != s3.length()) {
                return false;
            }

            Boolean[][] memo = new Boolean[s1.length()][s2.length()];
            return isInterleave(s1, s2, s3, 0, 0, 0, memo);
        }

        private boolean isInterleave(String s1, String s2, String s3, int i1, int i2, int i3, Boolean[][] memo) {
            if(i1 == s1.length()) {
                return s2.substring(i2).equals(s3.substring(i3));
            }

            if(i2 == s2.length()) {
                return s1.substring(i1).equals(s3.substring(i3));
            }

            if(memo[i1][i2] != null) {
                return memo[i1][i2];
            }

            char c = s3.charAt(i3);

            if(i1 < s1.length() && s1.charAt(i1) == c) {
                boolean rv = isInterleave(s1, s2, s3, i1 + 1, i2, i3 + 1, memo);
                if(rv) {
                    memo[i1][i2] = true;
                    return true;
                }
            }

            if(i2 < s2.length() && s2.charAt(i2) == c) {
                boolean rv = isInterleave(s1, s2, s3, i1, i2 + 1, i3 + 1, memo);
                if(rv) {
                    memo[i1][i2] = true;
                    return true;
                }
            }

            memo[i1][i2] = false;
            return false;
        }
    }

    class Solution_Recursive {
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1 == null && s2 == null && s3 == null) {
                return true;
            }

            if (s1 == null || s2 == null || s3 == null) {
                return false;
            }

            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }

            return isInterleave(s1, s2, s3, -1, -1, 0);
        }

        private boolean isInterleave(String s1, String s2, String s3, int i1, int i2, int i3) {
            if (i3 == s3.length()) {
                if (i1 == s1.length() - 1 && i2 == s2.length() - 1) {
                    return true;
                }

                return false;
            }

            char c = s3.charAt(i3);

            if (i1 < s1.length() - 1 && s1.charAt(i1 + 1) == c) {
                boolean rv = isInterleave(s1, s2, s3, i1 + 1, i2, i3 + 1);
                if (rv) {
                    return true;
                }
            }

            if (i2 < s2.length() - 1 && s2.charAt(i2 + 1) == c) {
                boolean rv = isInterleave(s1, s2, s3, i1, i2 + 1, i3 + 1);
                if (rv) {
                    return true;
                }
            }

            return false;
        }
    }

// "aabcc"
// "dbbca"
// "aadbbcbcac"
}

//    97. Interleaving String
//    Medium
//    Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
//
//    An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
//
//    s = s1 + s2 + ... + sn
//    t = t1 + t2 + ... + tm
//    |n - m| <= 1
//    The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
//    Note: a + b is the concatenation of strings a and b.
//
//
//
//    Example 1:
//
//
//    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//    Output: true
//    Example 2:
//
//    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//    Output: false
//    Example 3:
//
//    Input: s1 = "", s2 = "", s3 = ""
//    Output: true
//
//
//    Constraints:
//
//    0 <= s1.length, s2.length <= 100
//    0 <= s3.length <= 200
//    s1, s2, and s3 consist of lowercase English letters.
//
//
//    Follow up: Could you solve it using only O(s2.length) additional memory space?
