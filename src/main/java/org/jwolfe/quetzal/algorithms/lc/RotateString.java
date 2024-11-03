package org.jwolfe.quetzal.algorithms.lc;

public class RotateString {
    class Solution {
        public boolean rotateString(String A, String B) {
            if (A == null || B == null || A.length() != B.length()) {
                return false;
            }

            return (A + A).contains(B);
        }
    }

    class Solution_Correct_1 {
        public boolean rotateString(String A, String B) {
            if (A == null || B == null || A.length() != B.length()) {
                return false;
            }

            if (A.length() == 0) {
                return true;
            }

            return (A + A).contains(B);
        }
    }

    class Solution_Good {
        public boolean rotateString(String A, String B) {
            if (A == null || B == null || A.length() != B.length()) {
                return false;
            }

            if (A.length() == 0) {
                return true;
            }

            return (A + A).contains(B);
        }
    }

    class Solution_Brute {
        public boolean rotateString(String A, String B) {
            if (A == null || B == null || A.length() != B.length()) {
                return false;
            }

            if (A.length() == 0 && B.length() == 0) {
                return true;
            }

            for (int i = 0; i < A.length(); i++) {
                String s = shiftString(A, i);
                if (s.equals(B)) {
                    return true;
                }
            }

            return false;
        }

        private String shiftString(String s, int shiftLength) {
            int n = s.length();
            // shiftLength = n % shiftLength;

            StringBuilder builder = new StringBuilder(s);

            reverse(builder, 0, shiftLength - 1);
            reverse(builder, shiftLength, n - 1);
            reverse(builder, 0, n - 1);

            return builder.toString();
        }

        private void reverse(StringBuilder builder, int x, int y) {
            while (x < y) {
                swap(builder, x++, y--);
            }
        }

        private void swap(StringBuilder builder, int x, int y) {
            char c = builder.charAt(x);
            builder.setCharAt(x, builder.charAt(y));
            builder.setCharAt(y, c);
        }
    }
}

//    796. Rotate String
//    Easy
//    Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
//
//    A shift on s consists of moving the leftmost character of s to the rightmost position.
//
//    For example, if s = "abcde", then it will be "bcdea" after one shift.
//
//
//    Example 1:
//
//    Input: s = "abcde", goal = "cdeab"
//    Output: true
//    Example 2:
//
//    Input: s = "abcde", goal = "abced"
//    Output: false
//
//
//    Constraints:
//
//    1 <= s.length, goal.length <= 100
//    s and goal consist of lowercase English letters.