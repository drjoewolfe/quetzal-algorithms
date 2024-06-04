package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {
    class Solution {
        public int longestPalindrome(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int[] frequencies = new int[58];
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'A';
                frequencies[index]++;
            }

            int length = 0;
            boolean singleExists = false;

            for(int i = 0; i  < 58; i++) {
                int count = frequencies[i];

                if(count % 2 == 0) {
                    length += count;
                } else {
                    singleExists = true;
                    length += (count - 1);
                }
            }

            if(singleExists) {
                length++;
            }

            return length;
        }
    }

    class Solution_Correct_1 {
        public int longestPalindrome(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            Map<Character, Integer> frequencies = new HashMap<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }

            int length = 0;
            boolean singleExists = false;
            for(var entry : frequencies.entrySet()) {
                int count = entry.getValue();
                if(count % 2 != 0) {
                    singleExists = true;
                    length += count - 1;
                } else {
                    length += count;
                }
            }

            if(singleExists) {
                length++;
            }

            return length;
        }
    }
}

//    409. Longest Palindrome
//    Easy
//    Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
//
//    Letters are case sensitive, for example, "Aa" is not considered a palindrome.
//
//
//
//    Example 1:
//
//    Input: s = "abccccdd"
//    Output: 7
//    Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
//    Example 2:
//
//    Input: s = "a"
//    Output: 1
//    Explanation: The longest palindrome that can be built is "a", whose length is 1.
//
//
//    Constraints:
//
//    1 <= s.length <= 2000
//    s consists of lowercase and/or uppercase English letters only.