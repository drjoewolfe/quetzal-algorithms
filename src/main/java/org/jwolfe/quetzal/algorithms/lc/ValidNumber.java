package org.jwolfe.quetzal.algorithms.lc;

public class ValidNumber {
    class Solution {
        public boolean isNumber(String s) {
            if(s == null || s.length() == 0) {
                return false;
            }

            int ei = s.indexOf('e');
            if(ei == -1) {
                ei = s.indexOf('E');
            }

            if(ei != -1) {
                // Check for exponential number
                if(!isDecimalOrInteger(s, 0, ei - 1)) {
                    return false;
                }

                if(ei == s.length() - 1) {
                    return false;
                }

                if(!isInteger(s, ei + 1, s.length() - 1)) {
                    return false;
                }

                return true;
            } else {
                // Check for decimal or integer
                return isDecimalOrInteger(s, 0, s.length() - 1);
            }
        }

        private boolean isDecimalOrInteger(String s, int start, int end) {
            int di = s.indexOf(".");
            if(di == -1) {
                return isInteger(s, start, end);
            } else {
                return isDecimal(s, start, di, end);
            }
        }

        private boolean isDecimal(String s, int start, int di, int end) {
            if(end - start + 1 < 2) {
                return false;
            }

            char fc = s.charAt(start);
            if(fc == '+' || fc == '-') {
                start++;
            }

            if(end - start + 1 == 1) {
                return false;
            }

            if(di != start && !areDigits(s, start, di - 1)) {
                return false;
            }

            if(di != end && !areDigits(s, di + 1, end)) {
                return false;
            }

            return true;
        }

        private boolean isInteger(String s, int start, int end) {
            // First character
            char fc = s.charAt(start);
            if((fc < '0' || fc > '9') && (fc != '+' && fc != '-')) {
                return false;
            }

            if((fc == '+' || fc == '-') && end - start + 1 == 1) {
                return false;
            }

            // Remaining characters
            return areDigits(s, start + 1, end);
        }

        private boolean areDigits(String s, int start, int end) {
            for(int i = start; i <= end; i++) {
                char c = s.charAt(i);
                if(c < '0' || c > '9') {
                    return false;
                }
            }

            return true;
        }
    }

// "0"
// "."
// ".1"
// "0e"
// "-1."
// "+.8"
}

//    65. Valid Number
//    Hard
//    A valid number can be split up into these components (in order):
//
//    A decimal number or an integer.
//    (Optional) An 'e' or 'E', followed by an integer.
//    A decimal number can be split up into these components (in order):
//
//    (Optional) A sign character (either '+' or '-').
//    One of the following formats:
//    At least one digit, followed by a dot '.'.
//    At least one digit, followed by a dot '.', followed by at least one digit.
//    A dot '.', followed by at least one digit.
//    An integer can be split up into these components (in order):
//
//    (Optional) A sign character (either '+' or '-').
//    At least one digit.
//    For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
//
//    Given a string s, return true if s is a valid number.
//
//
//
//    Example 1:
//
//    Input: s = "0"
//    Output: true
//    Example 2:
//
//    Input: s = "e"
//    Output: false
//    Example 3:
//
//    Input: s = "."
//    Output: false
//    Example 4:
//
//    Input: s = ".1"
//    Output: true
//
//
//    Constraints:
//
//    1 <= s.length <= 20
//    s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.