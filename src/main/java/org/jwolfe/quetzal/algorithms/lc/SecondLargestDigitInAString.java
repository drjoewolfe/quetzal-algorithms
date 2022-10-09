package org.jwolfe.quetzal.algorithms.lc;

public class SecondLargestDigitInAString {
    class Solution {
        public int secondHighest(String s) {
            if(s == null || s.length() < 2) {
                return -1;
            }

            int max = -1;
            int secondMax = -1;

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(!(c >= '0' && c <= '9')) {
                    continue;
                }

                int digit = c - '0';
                if(digit > max) {
                    secondMax = max;
                    max = digit;
                } else if(digit < max && digit > secondMax) {
                    secondMax = digit;
                }
            }

            return secondMax;
        }
    }

// "dfa12321afd"
// "abc1111"
}

//    1796. Second Largest Digit in a String
//    Easy
//    Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does not exist.
//
//    An alphanumeric string is a string consisting of lowercase English letters and digits.
//
//
//
//    Example 1:
//
//    Input: s = "dfa12321afd"
//    Output: 2
//    Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
//    Example 2:
//
//    Input: s = "abc1111"
//    Output: -1
//    Explanation: The digits that appear in s are [1]. There is no second largest digit.
//
//
//    Constraints:
//
//    1 <= s.length <= 500
//    s consists of only lowercase English letters and/or digits.
