package org.jwolfe.quetzal.algorithms.lc;

public class ClearDigits {
    class Solution {
        public String clearDigits(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);

                if (Character.isDigit(c)) {
                    if (sb.length() > 0) {
                        sb.setLength(sb.length() - 1);
                    }
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }
}

//    3174. Clear Digits
//    Easy
//    You are given a string s.
//
//    Your task is to remove all digits by doing this operation repeatedly:
//
//    Delete the first digit and the closest non-digit character to its left.
//    Return the resulting string after removing all digits.
//
//
//
//    Example 1:
//
//    Input: s = "abc"
//
//    Output: "abc"
//
//    Explanation:
//
//    There is no digit in the string.
//
//    Example 2:
//
//    Input: s = "cb34"
//
//    Output: ""
//
//    Explanation:
//
//    First, we apply the operation on s[2], and s becomes "c4".
//
//    Then we apply the operation on s[1], and s becomes "".
//
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s consists only of lowercase English letters and digits.
//    The input is generated such that it is possible to delete all digits.