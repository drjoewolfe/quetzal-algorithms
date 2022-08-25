package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            if(ransomNote == null || ransomNote.length() == 0) {
                return true;
            }

            if(magazine == null || magazine.length() < ransomNote.length()) {
                return false;
            }

            int[] counts = new int[26];
            for(int i = 0; i < magazine.length(); i++) {
                char c = magazine.charAt(i);
                counts[c - 'a']++;
            }

            for(int i = 0; i < ransomNote.length(); i++) {
                char c = ransomNote.charAt(i);
                if(counts[c - 'a'] == 0) {
                    return false;
                }

                counts[c - 'a']--;
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean canConstruct(String ransomNote, String magazine) {
            if(ransomNote == null || ransomNote.length() == 0 ) {
                return true;
            }

            if(magazine == null || magazine.length() == 0) {
                return false;
            }

            int[] frequencies = new int[26];
            for(int i = 0; i < magazine.length(); i++) {
                char c = magazine.charAt(i);

                frequencies[c - 'a']++;
            }

            for(int i = 0; i < ransomNote.length(); i++) {
                char c = ransomNote.charAt(i);
                if(frequencies[c - 'a'] == 0) {
                    return false;
                }

                frequencies[c - 'a']--;
            }

            return true;
        }
    }

    class Solution_Classic {
        public boolean canConstruct(String ransomNote, String magazine) {
            if(ransomNote == null || ransomNote.length() == 0 ) {
                return true;
            }

            if(magazine == null || magazine.length() == 0) {
                return false;
            }

            Map<Character, Integer> frequencies = new HashMap<>();
            for(int i = 0; i < magazine.length(); i++) {
                char c = magazine.charAt(i);

                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }

            for(int i = 0; i < ransomNote.length(); i++) {
                char c = ransomNote.charAt(i);
                if(!frequencies.containsKey(c)) {
                    return false;
                }

                int count = frequencies.get(c);
                if(count == 1) {
                    frequencies.remove(c);
                } else {
                    frequencies.put(c, count - 1);
                }
            }

            return true;
        }
    }
}

//    383. Ransom Note
//    Easy
//    Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
//
//    Each letter in magazine can only be used once in ransomNote.
//
//
//
//    Example 1:
//
//    Input: ransomNote = "a", magazine = "b"
//    Output: false
//    Example 2:
//
//    Input: ransomNote = "aa", magazine = "ab"
//    Output: false
//    Example 3:
//
//    Input: ransomNote = "aa", magazine = "aab"
//    Output: true
//
//
//    Constraints:
//
//    1 <= ransomNote.length, magazine.length <= 105
//    ransomNote and magazine consist of lowercase English letters.