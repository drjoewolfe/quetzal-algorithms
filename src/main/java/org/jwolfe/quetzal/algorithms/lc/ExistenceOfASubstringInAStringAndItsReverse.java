package org.jwolfe.quetzal.algorithms.lc;

public class ExistenceOfASubstringInAStringAndItsReverse {
    class Solution {
        public boolean isSubstringPresent(String s) {
            if(s == null || s.length() < 2) {
                return false;
            }

            int n = s.length();
            String r = reverse(s);
            for(int i = 0; i < n - 1; i++) {
                String substr = s.substring(i, i + 2);
                if(r.indexOf(substr) >= 0) {
                    return true;
                }
            }

            return false;
        }

        private String reverse(String s) {
            StringBuilder builder = new StringBuilder();
            for(int i = s.length() - 1; i >= 0; i--) {
                builder.append(s.charAt(i));
            }

            return builder.toString();
        }
    }
}

//    3083. Existence of a Substring in a String and Its Reverse
//    Easy
//    Given a string s, find any substring of length 2 which is also present in the reverse of s.
//
//    Return true if such a substring exists, and false otherwise.
//
//
//
//    Example 1:
//
//    Input: s = "leetcode"
//
//    Output: true
//
//    Explanation: Substring "ee" is of length 2 which is also present in reverse(s) == "edocteel".
//
//    Example 2:
//
//    Input: s = "abcba"
//
//    Output: true
//
//    Explanation: All of the substrings of length 2 "ab", "bc", "cb", "ba" are also present in reverse(s) == "abcba".
//
//    Example 3:
//
//    Input: s = "abcd"
//
//    Output: false
//
//    Explanation: There is no substring of length 2 in s, which is also present in the reverse of s.
//
//
//
//    Constraints:
//
//    1 <= s.length <= 100
//    s consists only of lowercase English letters.