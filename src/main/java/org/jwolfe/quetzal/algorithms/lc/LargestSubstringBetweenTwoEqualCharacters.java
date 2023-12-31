package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestSubstringBetweenTwoEqualCharacters {
    class Solution {
        public int maxLengthBetweenEqualCharacters(String s) {
            if(s == null || s.length() < 2) {
                return -1;
            }

            int[] firstIndices = new int[26];
            Arrays.fill(firstIndices, -1);

            int maxLength = -1;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                if(firstIndices[index] != -1) {
                    maxLength = Math.max(maxLength, i - firstIndices[index] - 1);
                } else {
                    firstIndices[index] = i;
                }
            }

            return maxLength;
        }
    }

    class Solution_Correct_1 {
        public int maxLengthBetweenEqualCharacters(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            Map<Character, Integer> startIndexes = new HashMap<>();
            Map<Character, Integer> endIndexes = new HashMap<>();

            int maxLength = -1;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(!startIndexes.containsKey(c)) {
                    startIndexes.put(c, i);
                } else {
                    endIndexes.put(c, i);

                    maxLength = Math.max(maxLength,
                            endIndexes.get(c) - startIndexes.get(c) - 1);
                }
            }

            return maxLength;
        }
    }
}

//    1624. Largest Substring Between Two Equal Characters
//    Easy
//    Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.
//
//    A substring is a contiguous sequence of characters within a string.
//
//
//
//    Example 1:
//
//    Input: s = "aa"
//    Output: 0
//    Explanation: The optimal substring here is an empty substring between the two 'a's.
//    Example 2:
//
//    Input: s = "abca"
//    Output: 2
//    Explanation: The optimal substring here is "bc".
//    Example 3:
//
//    Input: s = "cbzxy"
//    Output: -1
//    Explanation: There are no characters that appear twice in s.
//
//
//    Constraints:
//
//    1 <= s.length <= 300
//    s contains only lowercase English letters.