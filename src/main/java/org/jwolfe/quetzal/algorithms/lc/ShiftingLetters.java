package org.jwolfe.quetzal.algorithms.lc;

public class ShiftingLetters {
    class Solution {
        public String shiftingLetters(String s, int[] shifts) {
            if(s == null || shifts == null || shifts.length == 0) {
                return s;
            }

            int n = s.length();
            int[] stringShifts = new int[n];
            stringShifts[n - 1] = shifts[n - 1];
            for(int i = n - 2; i >= 0; i--) {
                stringShifts[i] += stringShifts[i + 1] + shifts[i];
                stringShifts[i] %= 26;
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < n; i++) {
                builder.append(getShiftedChar(s.charAt(i), stringShifts[i]));
            }

            return builder.toString();
        }

        private char getShiftedChar(char c, int shift) {
            int val = c - 'a';
            val = (val + shift) % 26;

            return (char) (val + 'a');
        }
    }

    class Solution_TLE {
        public String shiftingLetters(String s, int[] shifts) {
            if(s == null || shifts == null || shifts.length == 0) {
                return s;
            }

            int n = s.length();
            int[] stringShifts = new int[n];
            stringShifts[n - 1] = shifts[n - 1];
            for(int i = n - 2; i >= 0; i--) {
                stringShifts[i] += stringShifts[i + 1] + shifts[i];
                stringShifts[i] %= 26;
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < n; i++) {
                builder.append(getShiftedChar(s.charAt(i), stringShifts[i]));
            }

            return builder.toString();
        }

        private char getShiftedChar(char c, int shift) {
            for(int i = 0; i < shift; i++) {
                c++;

                if(c > 'z') {
                    c = 'a';
                }
            }

            return c;
        }
    }

    class Solution_Brute {
        public String shiftingLetters(String s, int[] shifts) {
            if(s == null || shifts == null || shifts.length == 0) {
                return s;
            }

            StringBuilder builder = new StringBuilder(s);
            for(int i = 0; i < shifts.length; i++) {
                int shift = shifts[i];
                shiftString(builder, i, shift);
            }

            return builder.toString();
        }

        private void shiftString(StringBuilder builder, int index, int shift) {
            shift %= 26;
            for(int i = 0; i <= index; i++) {
                builder.setCharAt(i, getShiftedChar(builder.charAt(i), shift));
            }
        }

        private char getShiftedChar(char c, int shift) {
            for(int i = 0; i < shift; i++) {
                c++;

                if(c > 'z') {
                    c = 'a';
                }
            }

            return c;
        }
    }

// "abc"
// [3,5,9]

// "mkgfzkkuxownxvfvxasy"
// [505870226,437526072,266740649,224336793,532917782,311122363,567754492,595798950,81520022,684110326,137742843,275267355,856903962,148291585,919054234,467541837,622939912,116899933,983296461,536563513]
}

//    848. Shifting Letters
//    Medium
//    You are given a string s of lowercase English letters and an integer array shifts of the same length.
//
//    Call the shift() of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
//
//    For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
//    Now for each shifts[i] = x, we want to shift the first i + 1 letters of s, x times.
//
//    Return the final string after all such shifts to s are applied.
//
//
//
//    Example 1:
//
//    Input: s = "abc", shifts = [3,5,9]
//    Output: "rpl"
//    Explanation: We start with "abc".
//    After shifting the first 1 letters of s by 3, we have "dbc".
//    After shifting the first 2 letters of s by 5, we have "igc".
//    After shifting the first 3 letters of s by 9, we have "rpl", the answer.
//    Example 2:
//
//    Input: s = "aaa", shifts = [1,2,3]
//    Output: "gfd"
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of lowercase English letters.
//    shifts.length == s.length
//    0 <= shifts[i] <= 109