package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfSubstringsWithOnly1s {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numSub(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            int count = 0;
            int oneStreak = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1') {
                    oneStreak++;
                    count += oneStreak;
                    count %= MOD;
                } else {
                    oneStreak = 0;
                }
            }

            return count;
        }
    }
}

//    1513. Number of Substrings With Only 1s
//    Medium
//    Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: s = "0110111"
//    Output: 9
//    Explanation: There are 9 substring in total with only 1's characters.
//    "1" -> 5 times.
//    "11" -> 3 times.
//    "111" -> 1 time.
//    Example 2:
//
//    Input: s = "101"
//    Output: 2
//    Explanation: Substring "1" is shown 2 times in s.
//    Example 3:
//
//    Input: s = "111111"
//    Output: 21
//    Explanation: Each substring contains only 1's characters.
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s[i] is either '0' or '1'.