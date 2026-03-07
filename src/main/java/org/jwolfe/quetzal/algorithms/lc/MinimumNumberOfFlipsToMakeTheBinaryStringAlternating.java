package org.jwolfe.quetzal.algorithms.lc;

public class MinimumNumberOfFlipsToMakeTheBinaryStringAlternating {
    class Solution {
        public int minFlips(String s) {
            if (s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();

            String ns = s + s;

            StringBuilder s1Builder = new StringBuilder();
            StringBuilder s2Builder = new StringBuilder();

            for (int i = 0; i < 2 * n; i++) {
                s1Builder.append((i % 2 == 0) ? '0' : '1');
                s2Builder.append((i % 2 == 0) ? '1' : '0');
            }

            String s1 = s1Builder.toString();
            String s2 = s2Builder.toString();

            int minFlips = Integer.MAX_VALUE;
            int flips1 = 0;
            int flips2 = 0;

            int i = 0;
            int j = 0;

            while (j < 2 * n) {
                char c = ns.charAt(j);

                if (c != s1.charAt(j)) {
                    flips1++;
                }

                if (c != s2.charAt(j)) {
                    flips2++;
                }

                if (j - i + 1 > n) {
                    // shrink window
                    char lc = ns.charAt(i);
                    if (lc != s1.charAt(i)) {
                        flips1--;
                    }

                    if (lc != s2.charAt(i)) {
                        flips2--;
                    }

                    i++;
                }

                if (j - i + 1 == n) {
                    minFlips = Math.min(minFlips, Math.min(flips1, flips2));
                }

                j++;
            }

            return minFlips;
        }
    }
}

//    1888. Minimum Number of Flips to Make the Binary String Alternating
//    Medium
//    You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
//
//    Type-1: Remove the character at the start of the string s and append it to the end of the string.
//    Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
//    Return the minimum number of type-2 operations you need to perform such that s becomes alternating.
//
//    The string is called alternating if no two adjacent characters are equal.
//
//    For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
//
//
//    Example 1:
//
//    Input: s = "111000"
//    Output: 2
//    Explanation: Use the first operation two times to make s = "100011".
//    Then, use the second operation on the third and sixth elements to make s = "101010".
//    Example 2:
//
//    Input: s = "010"
//    Output: 0
//    Explanation: The string is already alternating.
//    Example 3:
//
//    Input: s = "1110"
//    Output: 1
//    Explanation: Use the second operation on the second element to make s = "1010".
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s[i] is either '0' or '1'.