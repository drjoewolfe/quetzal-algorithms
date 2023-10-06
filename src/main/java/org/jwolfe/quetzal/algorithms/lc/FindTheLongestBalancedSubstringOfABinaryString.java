package org.jwolfe.quetzal.algorithms.lc;

public class FindTheLongestBalancedSubstringOfABinaryString {
    class Solution {
        public int findTheLongestBalancedSubstring(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int maxLength = 0;

            int currZeroCount = 0;
            int currOneCount = 0;
            boolean oneStreak = false;

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == '0') {
                    if(!oneStreak) {
                        currZeroCount++;
                    } else {
                        oneStreak = false;
                        currZeroCount = 1;
                        currOneCount = 0;
                    }
                } else {
                    oneStreak = true;
                    currOneCount++;

                    int currLength = Math.min(currZeroCount, currOneCount) * 2;
                    maxLength = Math.max(maxLength, currLength);
                }
            }

            return maxLength;
        }
    }
}

//    2609. Find the Longest Balanced Substring of a Binary String
//    Easy
//    You are given a binary string s consisting only of zeroes and ones.
//
//    A substring of s is considered balanced if all zeroes are before ones and the number of zeroes is equal to the number of ones inside the substring. Notice that the empty substring is considered a balanced substring.
//
//    Return the length of the longest balanced substring of s.
//
//    A substring is a contiguous sequence of characters within a string.
//
//
//
//    Example 1:
//
//    Input: s = "01000111"
//    Output: 6
//    Explanation: The longest balanced substring is "000111", which has length 6.
//    Example 2:
//
//    Input: s = "00111"
//    Output: 4
//    Explanation: The longest balanced substring is "0011", which has length 4.
//    Example 3:
//
//    Input: s = "111"
//    Output: 0
//    Explanation: There is no balanced substring except the empty substring, so the answer is 0.
//
//
//    Constraints:
//
//    1 <= s.length <= 50
//    '0' <= s[i] <= '1'