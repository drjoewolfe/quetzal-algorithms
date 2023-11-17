package org.jwolfe.quetzal.algorithms.lc;

public class ScrambleString {
    class Solution {
        public boolean isScramble(String s1, String s2) {
            if(s1 == null && s2 == null) {
                return true;
            }

            if(s1 == null || s2 == null || s1.length() != s2.length()) {
                return false;
            }

            int length = s1.length();

            Boolean[][][] memo = new Boolean[length + 1][length][length];
            return isScramble(s1, s2, 0, 0, length, memo);
        }

        private boolean isScramble(String s1, String s2, int i, int j, int length, Boolean[][][] memo) {
            if(memo[length][i][j] != null) {
                return memo[length][i][j];
            }

            if(length == 1) {
                return s1.charAt(i) == s2.charAt(j);
            }

            for(int k = 1; k < length; k++) {
                if(isScramble(s1, s2, i, j, k, memo) && isScramble(s1, s2, i + k, j + k, length - k, memo)) {
                    return memo[length][i][j] = true;
                }

                if(isScramble(s1, s2, i, j + (length - k), k, memo) && isScramble(s1, s2, i + k, j, length - k, memo)) {
                    return memo[length][i][j] = true;
                }
            }

            return memo[length][i][j] = false;
        }
    }

    class Solution_Recursive_TLE {
        public boolean isScramble(String s1, String s2) {
            if(s1 == null && s2 == null) {
                return true;
            }

            if(s1 == null || s2 == null || s1.length() != s2.length()) {
                return false;
            }

            return isScramble(s1, s2, 0, 0, s1.length());
        }

        private boolean isScramble(String s1, String s2, int i, int j, int length) {
            if(length == 1) {
                return s1.charAt(i) == s2.charAt(j);
            }

            for(int k = 1; k < length; k++) {
                if(isScramble(s1, s2, i, j, k) && isScramble(s1, s2, i + k, j + k, length - k)) {
                    return true;
                }

                if(isScramble(s1, s2, i, j + (length - k), k) && isScramble(s1, s2, i + k, j, length - k)) {
                    return true;
                }
            }

            return false;
        }
    }


// "great"
// "rgeat"

// "ccabcbabcbabbbbcbb"
// "bbbbabccccbbbabcba"
}

//    87. Scramble String
//    Hard
//    We can scramble a string s to get a string t using the following algorithm:
//
//    If the length of the string is 1, stop.
//    If the length of the string is > 1, do the following:
//    Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
//    Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
//    Apply step 1 recursively on each of the two substrings x and y.
//    Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
//
//
//
//    Example 1:
//
//    Input: s1 = "great", s2 = "rgeat"
//    Output: true
//    Explanation: One possible scenario applied on s1 is:
//    "great" --> "gr/eat" // divide at random index.
//    "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
//    "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
//    "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
//    "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
//    "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
//    The algorithm stops now, and the result string is "rgeat" which is s2.
//    As one possible scenario led s1 to be scrambled to s2, we return true.
//    Example 2:
//
//    Input: s1 = "abcde", s2 = "caebd"
//    Output: false
//    Example 3:
//
//    Input: s1 = "a", s2 = "a"
//    Output: true
//
//
//    Constraints:
//
//    s1.length == s2.length
//    1 <= s1.length <= 30
//    s1 and s2 consist of lowercase English letters.