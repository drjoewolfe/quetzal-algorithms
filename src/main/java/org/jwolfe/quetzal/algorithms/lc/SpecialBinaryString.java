package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialBinaryString {
    class Solution {
        public String makeLargestSpecial(String s) {
            return solve(s);
        }

        private String solve(String s) {
            List<String> specialStrings = new ArrayList<>();

            int sum = 0;
            int prev = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                sum += ((c == '1') ? 1 : -1);

                if (sum == 0) {
                    // Special string
                    String str = s.substring(prev + 1, i);
                    String optimizedString = "1" + solve(str) + "0";
                    specialStrings.add(optimizedString);
                    prev = i + 1;
                }
            }

            Collections.sort(specialStrings);

            String result = "";
            for (int i = specialStrings.size() - 1; i >= 0; i--) {
                result += specialStrings.get(i);
            }

            return result;
        }
    }
}

//    761. Special Binary String
//    Hard
//    Special binary strings are binary strings with the following two properties:
//
//    The number of 0's is equal to the number of 1's.
//    Every prefix of the binary string has at least as many 1's as 0's.
//    You are given a special binary string s.
//
//    A move consists of choosing two consecutive, non-empty, special substrings of s, and swapping them. Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.
//
//    Return the lexicographically largest resulting string possible after applying the mentioned operations on the string.
//
//
//
//    Example 1:
//
//    Input: s = "11011000"
//    Output: "11100100"
//    Explanation: The strings "10" [occuring at s[1]] and "1100" [at s[3]] are swapped.
//    This is the lexicographically largest string possible after some number of swaps.
//    Example 2:
//
//    Input: s = "10"
//    Output: "10"
//
//
//    Constraints:
//
//    1 <= s.length <= 50
//    s[i] is either '0' or '1'.
//    s is a special binary string.