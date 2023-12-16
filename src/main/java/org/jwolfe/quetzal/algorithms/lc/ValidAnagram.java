package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    class Solution {
        public boolean isAnagram(String s, String t) {
            if(s == null || t == null || s.length() != t.length()) {
                return false;
            }

            int n = s.length();
            int[] counts = new int[26];
            for(int i = 0; i < n; i++) {
                counts[s.charAt(i) - 'a']++;
                counts[t.charAt(i) - 'a']--;
            }

            for(int i = 0; i < 26; i++) {
                if(counts[i] != 0) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Corrct_1 {
        public boolean isAnagram(String s, String t) {
            if(s == null && t == null) {
                return true;
            }

            if(s == null || t == null) {
                return false;
            }

            Map<Character, Integer> sCounts = new HashMap<>();
            Map<Character, Integer> tCounts = new HashMap<>();

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                sCounts.put(c, sCounts.getOrDefault(c, 0) + 1);
            }

            for(int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                tCounts.put(c, tCounts.getOrDefault(c, 0) + 1);
            }

            if(sCounts.size() != tCounts.size()) {
                return false;
            }

            for(Map.Entry<Character, Integer> entry : sCounts.entrySet()) {
                Character key = entry.getKey();
                int count = entry.getValue();

                if(!tCounts.containsKey(key)
                        || tCounts.get(key) != count) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    242. Valid Anagram
//    Easy
//    Given two strings s and t , write a function to determine if t is an anagram of s.
//
//    Example 1:
//
//    Input: s = "anagram", t = "nagaram"
//    Output: true
//    Example 2:
//
//    Input: s = "rat", t = "car"
//    Output: false
//    Note:
//    You may assume the string contains only lowercase alphabets.
//
//    Follow up:
//    What if the inputs contain unicode characters? How would you adapt your solution to such case?