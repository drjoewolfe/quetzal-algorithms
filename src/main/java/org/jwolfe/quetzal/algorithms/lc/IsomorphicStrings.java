package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsomorphicStrings {
    class Solution {
        public boolean isIsomorphic(String s, String t) {
            if(s == null && t == null) {
                return true;
            }

            if(s == null || t == null || s.length() != t.length()) {
                return false;
            }

            Map<Character, Character> mappings = new HashMap<>();
            Set<Character> slottedTargetCharacters = new HashSet<>();

            for(int i = 0; i < s.length(); i++) {
                char sc = s.charAt(i);
                char tc = t.charAt(i);

                if(!mappings.containsKey(sc)) {
                    if(slottedTargetCharacters.contains(tc)) {
                        return false;
                    }

                    mappings.put(sc, tc);
                    slottedTargetCharacters.add(tc);
                } else {
                    if(mappings.get(sc) != tc) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    class Solution_Correct_3 {
        public boolean isIsomorphic(String s, String t) {
            if(s == null && t == null) {
                return true;
            }

            if(s == null || t == null || s.length() != t.length()) {
                return false;
            }

            Map<Character, Character> mappings = new HashMap<>();
            Set<Character> slottedCharacters = new HashSet<>();
            for(int i = 0; i < s.length(); i++) {
                char sc = s.charAt(i);
                char tc = t.charAt(i);

                if(mappings.containsKey(sc)) {
                    if(mappings.get(sc) != tc) {
                        return false;
                    }
                } else {
                    if(slottedCharacters.contains(tc)) {
                        return false;
                    }

                    mappings.put(sc, tc);
                    slottedCharacters.add(tc);
                }
            }

            return true;
        }
    }

    class Solution_NonPositional {
        public boolean isIsomorphic(String s, String t) {
            if(s == null && t == null) {
                return true;
            }

            if(s == null || t == null || s.length() != t.length()) {
                return false;
            }

            Map<Character, Integer> sMap = new HashMap<>();
            Map<Character, Integer> tMap = new HashMap<>();

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                sMap.put(c, sMap.getOrDefault(c, 0) + 1);
            }

            for(int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                tMap.put(c, tMap.getOrDefault(c, 0) + 1);
            }

            Map<Integer, Integer> counterMap = new HashMap<>();
            for(Map.Entry<Character, Integer> entry : sMap.entrySet()) {
                int count = entry.getValue();
                counterMap.put(count, counterMap.getOrDefault(count, 0) + 1);
            }

            for(Map.Entry<Character, Integer> entry : tMap.entrySet()) {
                int count = entry.getValue();
                if(!counterMap.containsKey(count)) {
                    return false;
                }

                int frequency = counterMap.get(count);
                if(frequency == 1) {
                    counterMap.remove(count);
                } else {
                    counterMap.put(count, frequency - 1);
                }
            }

            return counterMap.size() == 0;
        }
    }

    class Solution_Correct_2 {
        public boolean isIsomorphic(String s, String t) {
            if(s == null && t == null) {
                return true;
            }

            if(s == null || t == null || s.length() != t.length()) {
                return false;
            }

            int n = s.length();
            Map<Character, Character> mappings = new HashMap<>();
            Map<Character, Character> reverseMappings = new HashMap<>();
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                char d = t.charAt(i);

                if(!mappings.containsKey(c)) {
                    if(reverseMappings.containsKey(d)) {
                        return false;
                    }

                    mappings.put(c, d);
                    reverseMappings.put(d, c);
                }

                if(mappings.get(c) != d) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    205. Isomorphic Strings
//    Easy
//    Given two strings s and t, determine if they are isomorphic.
//
//    Two strings s and t are isomorphic if the characters in s can be replaced to get t.
//
//    All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
//
//
//
//    Example 1:
//
//    Input: s = "egg", t = "add"
//    Output: true
//    Example 2:
//
//    Input: s = "foo", t = "bar"
//    Output: false
//    Example 3:
//
//    Input: s = "paper", t = "title"
//    Output: true
//
//
//    Constraints:
//
//    1 <= s.length <= 5 * 104
//    t.length == s.length
//    s and t consist of any valid ascii character.
