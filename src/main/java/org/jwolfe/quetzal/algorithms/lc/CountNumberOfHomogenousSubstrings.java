package org.jwolfe.quetzal.algorithms.lc;

public class CountNumberOfHomogenousSubstrings {
    class Solution {
        private int MOD = 1_000_000_007;

        public int countHomogenous(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            long count = 0;

            long streak = 1;
            int prev = s.charAt(0);

            for(int i = 1; i < s.length(); i++) {
                char curr = s.charAt(i);

                if(curr == prev) {
                    streak++;
                } else {
                    count += (streak * (streak + 1)) / 2;
                    count %= MOD;

                    prev = curr;
                    streak = 1;
                }
            }

            count += (streak * (streak + 1)) / 2;
            count %= MOD;

            return (int) count;
        }
    }
}

//    1759. Count Number of Homogenous Substrings
//    Medium
//    Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.
//
//    A string is homogenous if all the characters of the string are the same.
//
//    A substring is a contiguous sequence of characters within a string.
//
//
//
//    Example 1:
//
//    Input: s = "abbcccaa"
//    Output: 13
//    Explanation: The homogenous substrings are listed as below:
//    "a"   appears 3 times.
//    "aa"  appears 1 time.
//    "b"   appears 2 times.
//    "bb"  appears 1 time.
//    "c"   appears 3 times.
//    "cc"  appears 2 times.
//    "ccc" appears 1 time.
//    3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
//    Example 2:
//
//    Input: s = "xy"
//    Output: 2
//    Explanation: The homogenous substrings are "x" and "y".
//    Example 3:
//
//    Input: s = "zzzzz"
//    Output: 15
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of lowercase letters.