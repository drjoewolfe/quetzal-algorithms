package org.jwolfe.quetzal.algorithms.lc;

public class ProcessStringWithSpecialOperationsII {
    class Solution {
        public char processStr(String s, long k) {
            if (s == null || s.length() == 0) {
                return '.';
            }

            long length = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '*') {
                    if (length > 0) {
                        length--;
                    }
                } else if (c == '#') {
                    length *= 2;
                } else if (c == '%') {
                    // No change
                } else {
                    length++;
                }
            }

            if (k + 1 > length) {
                return '.';
            }

            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);

                if (c == '*') {
                    length++;
                } else if (c == '#') {
                    if (k + 1 > (length + 1) / 2) {
                        k -= (length / 2);
                    }

                    length = (length + 1) / 2;
                } else if (c == '%') {
                    k = length - k - 1;
                } else {
                    if (k + 1 == length) {
                        return c;
                    }

                    length--;
                }
            }

            return '.';
        }
    }

    class Solution_MLE {
        public char processStr(String s, long k) {
            if (s == null || s.length() == 0) {
                return '.';
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '*') {
                    if (builder.length() > 0) {
                        builder.deleteCharAt(builder.length() - 1);
                    }
                } else if (c == '#') {
                    String currStr = builder.toString();
                    builder.append(currStr);
                } else if (c == '%') {
                    builder.reverse();
                } else {
                    builder.append(c);
                }
            }

            if (builder.length() == 0 || builder.length() <= k) {
                return '.';
            }

            return builder.charAt((int) (k));
        }
    }
}

//    3614. Process String with Special Operations II
//    Hard
//    You are given a string s consisting of lowercase English letters and the special characters: '*', '#', and '%'.
//
//    You are also given an integer k.
//
//    Build a new string result by processing s according to the following rules from left to right:
//
//    If the letter is a lowercase English letter append it to result.
//    A '*' removes the last character from result, if it exists.
//    A '#' duplicates the current result and appends it to itself.
//    A '%' reverses the current result.
//    Return the kth character of the final string result. If k is out of the bounds of result, return '.'.
//
//
//
//    Example 1:
//
//    Input: s = "a#b%*", k = 1
//
//    Output: "a"
//
//    Explanation:
//
//    i	s[i]	Operation	Current result
//    0	'a'	Append 'a'	"a"
//    1	'#'	Duplicate result	"aa"
//    2	'b'	Append 'b'	"aab"
//    3	'%'	Reverse result	"baa"
//    4	'*'	Remove the last character	"ba"
//    The final result is "ba". The character at index k = 1 is 'a'.
//
//    Example 2:
//
//    Input: s = "cd%#*#", k = 3
//
//    Output: "d"
//
//    Explanation:
//
//    i	s[i]	Operation	Current result
//    0	'c'	Append 'c'	"c"
//    1	'd'	Append 'd'	"cd"
//    2	'%'	Reverse result	"dc"
//    3	'#'	Duplicate result	"dcdc"
//    4	'*'	Remove the last character	"dcd"
//    5	'#'	Duplicate result	"dcddcd"
//    The final result is "dcddcd". The character at index k = 3 is 'd'.
//
//    Example 3:
//
//    Input: s = "z*#", k = 0
//
//    Output: "."
//
//    Explanation:
//
//    i	s[i]	Operation	Current result
//    0	'z'	Append 'z'	"z"
//    1	'*'	Remove the last character	""
//    2	'#'	Duplicate the string	""
//    The final result is "". Since index k = 0 is out of bounds, the output is '.'.
//
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of only lowercase English letters and special characters '*', '#', and '%'.
//    0 <= k <= 1015
//    The length of result after processing s will not exceed 1015.