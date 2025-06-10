package org.jwolfe.quetzal.algorithms.lc;

public class MaximumDifferenceBetweenEvenAndOddFrequencyI {
    class Solution {
        public int maxDifference(String s) {
            int[] frequency = new int[26];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                frequency[c - 'a']++;
            }

            int oddMax = 0;
            int evenMin = Integer.MAX_VALUE;

            for (int i = 0; i < 26; i++) {
                int c = frequency[i];
                if (c == 0) {
                    continue;
                }

                if (c % 2 == 0) {
                    evenMin = Math.min(evenMin, c);
                } else {
                    oddMax = Math.max(oddMax, c);
                }
            }

            System.out.println(oddMax + ", " + evenMin);
            return oddMax - evenMin;
        }
    }
}

//    3442. Maximum Difference Between Even and Odd Frequency I
//    Easy
//    You are given a string s consisting of lowercase English letters.
//
//    Your task is to find the maximum difference diff = freq(a1) - freq(a2) between the frequency of characters a1 and a2 in the string such that:
//
//    a1 has an odd frequency in the string.
//    a2 has an even frequency in the string.
//    Return this maximum difference.
//
//
//
//    Example 1:
//
//    Input: s = "aaaaabbc"
//
//    Output: 3
//
//    Explanation:
//
//    The character 'a' has an odd frequency of 5, and 'b' has an even frequency of 2.
//    The maximum difference is 5 - 2 = 3.
//    Example 2:
//
//    Input: s = "abcabcab"
//
//    Output: 1
//
//    Explanation:
//
//    The character 'a' has an odd frequency of 3, and 'c' has an even frequency of 2.
//    The maximum difference is 3 - 2 = 1.
//
//
//    Constraints:
//
//    3 <= s.length <= 100
//    s consists only of lowercase English letters.
//    s contains at least one character with an odd frequency and one with an even frequency