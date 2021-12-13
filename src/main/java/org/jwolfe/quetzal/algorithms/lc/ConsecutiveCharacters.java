package org.jwolfe.quetzal.algorithms.lc;

public class ConsecutiveCharacters {
    class Solution {
        public int maxPower(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int maxPower = 0;

            int left = 0;
            int right = 0;
            char current = s.charAt(0);
            int n = s.length();
            while(right < n - 1) {
                right++;
                char c = s.charAt(right);

                if(c != current) {
                    maxPower = Math.max(maxPower, right - left);
                    left = right;
                    current = c;
                }
            }

            maxPower = Math.max(maxPower, right - left + 1);
            return maxPower;
        }
    }


    class Solution_Correct_1 {
        public int maxPower(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            if(s.length() == 1) {
                return 1;
            }

            int maxPower = 1;
            int currentPower = 1;
            char lastChar = s.charAt(0);
            for(int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == lastChar) {
                    currentPower++;
                    maxPower = Math.max(maxPower, currentPower);
                } else {
                    lastChar = c;
                    currentPower = 1;
                }
            }

            return maxPower;
        }
    }
}

//    1446. Consecutive Characters
//    Easy
//    The power of the string is the maximum length of a non-empty substring that contains only one unique character.
//
//    Given a string s, return the power of s.
//
//
//
//    Example 1:
//
//    Input: s = "leetcode"
//    Output: 2
//    Explanation: The substring "ee" is of length 2 with the character 'e' only.
//    Example 2:
//
//    Input: s = "abbcccddddeeeeedcba"
//    Output: 5
//    Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
//    Example 3:
//
//    Input: s = "triplepillooooow"
//    Output: 5
//    Example 4:
//
//    Input: s = "hooraaaaaaaaaaay"
//    Output: 11
//    Example 5:
//
//    Input: s = "tourist"
//    Output: 1
//
//
//    Constraints:
//
//    1 <= s.length <= 500
//    s consists of only lowercase English letters.
