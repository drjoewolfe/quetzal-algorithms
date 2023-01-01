package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    class Solution {
        public boolean wordPattern(String pattern, String str) {
            if (pattern == null && str == null) {
                return true;
            }

            if (pattern == null || str == null) {
                return false;
            }

            String[] words = str.split(" ");
            if (words.length != pattern.length()) {
                return false;
            }

            Map<Character, String> patternToStringMap = new HashMap<>();
            Map<String, Character> stringToPatternMap = new HashMap<>();

            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                String word = words[i];

                if ((patternToStringMap.containsKey(c)
                        && !patternToStringMap.get(c).equals(word))
                        || (stringToPatternMap.containsKey(word)
                        && !stringToPatternMap.get(word).equals(c))) {
                    return false;
                }

                patternToStringMap.put(c, word);
                stringToPatternMap.put(word, c);
            }

            return true;
        }
    }

    class Solution_Correct_2 {
        public boolean wordPattern(String pattern, String str) {
            if (pattern == null && str == null) {
                return true;
            }

            if (pattern == null || str == null) {
                return false;
            }

            String[] words = str.split(" ");
            if (words.length != pattern.length()) {
                return false;
            }

            Map<Character, String> patternMappings = new HashMap<>();
            Map<String, Character> wordMappings = new HashMap<>();

            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                String w = words[i];

                if (patternMappings.containsKey(c) && !patternMappings.get(c).equals(w)) {
                    return false;
                }

                if (wordMappings.containsKey(w) && !wordMappings.get(w).equals(c)) {
                    return false;
                }

                patternMappings.put(c, w);
                wordMappings.put(w, c);
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean wordPattern(String pattern, String str) {
            if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0) {
                return false;
            }

            String[] words = str.split(" ");
            if (words.length != pattern.length()) {
                return false;
            }

            Map<Character, String> charMappings = new HashMap<>();
            Map<String, Character> wordMappings = new HashMap<>();

            for (int i = 0; i < words.length; i++) {
                String w = words[i];
                char c = pattern.charAt(i);

                if (charMappings.containsKey(c) && !charMappings.get(c).equals(w)) {
                    return false;
                }

                if (wordMappings.containsKey(w) && wordMappings.get(w) != c) {
                    return false;
                }

                charMappings.put(c, w);
                wordMappings.put(w, c);
            }

            return true;

        }
    }
}

//    290. Word Pattern
//    Easy
//    Given a pattern and a string s, find if s follows the same pattern.
//
//    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
//
//
//
//    Example 1:
//
//    Input: pattern = "abba", s = "dog cat cat dog"
//    Output: true
//    Example 2:
//
//    Input: pattern = "abba", s = "dog cat cat fish"
//    Output: false
//    Example 3:
//
//    Input: pattern = "aaaa", s = "dog cat cat dog"
//    Output: false
//
//
//    Constraints:
//
//    1 <= pattern.length <= 300
//    pattern contains only lower-case English letters.
//    1 <= s.length <= 3000
//    s contains only lowercase English letters and spaces ' '.
//    s does not contain any leading or trailing spaces.
//    All the words in s are separated by a single space.
