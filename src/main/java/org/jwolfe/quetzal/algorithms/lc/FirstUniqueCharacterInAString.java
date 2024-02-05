package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterInAString {
    class Solution {
        public int firstUniqChar(String s) {
            if(s == null || s.length() == 0) {
                return -1;
            }

            int n = s.length();
            int[] counts = new int[26];
            int[] indexes = new int[26];

            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                int ci = c - 'a';

                counts[ci]++;
                indexes[ci] = i;
            }

            int minIndex = n;
            for(int i = 0; i < 26; i++) {
                if(counts[i] == 1) {
                    minIndex = Math.min(minIndex, indexes[i]);
                }
            }

            return minIndex == n ? -1 : minIndex;
        }
    }

    class Solution_Correct_2 {
        public int firstUniqChar(String s) {
            if(s == null || s.length() == 0) {
                return -1;
            }

            Map<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(map.get(c) == 1) {
                    return i;
                }
            }

            return -1;
        }
    }

    class Solution_Correct_1 {
        public int firstUniqChar(String s) {
            if(s == null || s.length() == 0) {
                return -1;
            }

            Map<Character, Integer> frequencies = new HashMap<>();

            for(int i = 0; i < s.length(); i++) {
                char c= s.charAt(i);
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }

            for(int i = 0; i < s.length(); i++) {
                char c= s.charAt(i);

                if(frequencies.get(c) == 1) {
                    return i;
                }
            }

            return -1;
        }
    }

    class Solution_Approach1 {
        public int firstUniqChar(String s) {
            if(s == null || s.length() == 0) {
                return -1;
            }

            Map<Character, Integer> frequencies = new HashMap<>();
            Map<Character, Integer> firstIndices = new HashMap<>();

            for(int i = 0; i < s.length(); i++) {
                char c= s.charAt(i);
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
                if(!firstIndices.containsKey(c)) {
                    firstIndices.put(c, i);
                }
            }

            int firstIndex = Integer.MAX_VALUE;
            for(Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
                if(entry.getValue() == 1) {
                    firstIndex = Math.min(firstIndex, firstIndices.get(entry.getKey()));
                }
            }

            return (firstIndex == Integer.MAX_VALUE) ? -1 : firstIndex;
        }
    }
}

//    387. First Unique Character in a String
//    Easy
//    Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
//
//
//
//    Example 1:
//
//    Input: s = "leetcode"
//    Output: 0
//    Example 2:
//
//    Input: s = "loveleetcode"
//    Output: 2
//    Example 3:
//
//    Input: s = "aabb"
//    Output: -1
//
//
//    Constraints:
//
//    1 <= s.length <= 105
//    s consists of only lowercase English letters.
