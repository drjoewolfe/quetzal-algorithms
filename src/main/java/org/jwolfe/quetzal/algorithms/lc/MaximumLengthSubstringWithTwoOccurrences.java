package org.jwolfe.quetzal.algorithms.lc;

public class MaximumLengthSubstringWithTwoOccurrences {
    class Solution {
        public int maximumLengthSubstring(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int[] frequencies = new int[26];

            int n = s.length();
            int maxLength = 0;

            int left = 0;
            int right = 0;

            for(; right < n; right++) {
                char c = s.charAt(right);
                int ci = c - 'a';
                frequencies[ci]++;

                while(frequencies[ci] > 2) {
                    char lc = s.charAt(left);
                    int lci = lc - 'a';
                    frequencies[lci]--;

                    left++;
                }

                int length = right - left + 1;
                maxLength = Math.max(maxLength, length);
            }

            return maxLength;
        }
    }
}

//    3090. Maximum Length Substring With Two Occurrences
//    Easy
//    Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each character.
//
//
//    Example 1:
//
//    Input: s = "bcbbbcba"
//
//    Output: 4
//
//    Explanation:
//
//    The following substring has a length of 4 and contains at most two occurrences of each character: "bcbbbcba".
//    Example 2:
//
//    Input: s = "aaaa"
//
//    Output: 2
//
//    Explanation:
//
//    The following substring has a length of 2 and contains at most two occurrences of each character: "aaaa".
//
//
//    Constraints:
//
//    2 <= s.length <= 100
//    s consists only of lowercase English letters.