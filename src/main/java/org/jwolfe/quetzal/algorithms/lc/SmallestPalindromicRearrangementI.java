package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class SmallestPalindromicRearrangementI {
    class Solution {
        public String smallestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }

            int n = s.length();
            int half = n / 2;

            char[] chars = s.toCharArray();
            Arrays.sort(chars, 0, half);
            for (int i = 0; i < half; i++) {
                int j = n - i - 1;
                ;

                chars[j] = chars[i];
            }

            return new String(chars);
        }
    }
}

//    3517. Smallest Palindromic Rearrangement I
//    Medium
//    You are given a palindromic string s.
//
//    Return the lexicographically smallest palindromic permutation of s.
//
//
//
//    Example 1:
//
//    Input: s = "z"
//
//    Output: "z"
//
//    Explanation:
//
//    A string of only one character is already the lexicographically smallest palindrome.
//
//    Example 2:
//
//    Input: s = "babab"
//
//    Output: "abbba"
//
//    Explanation:
//
//    Rearranging "babab" → "abbba" gives the smallest lexicographic palindrome.
//
//    Example 3:
//
//    Input: s = "daccad"
//
//    Output: "acddca"
//
//    Explanation:
//
//    Rearranging "daccad" → "acddca" gives the smallest lexicographic palindrome.
//
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of lowercase English letters.
//    s is guaranteed to be palindromic.