package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAndReplacePattern {
    class Solution {
        public List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> results = new ArrayList<>();
            if(words == null || words.length == 0 || pattern == null || pattern.length() == 0) {
                return results;
            }

            for(String word : words) {
                if(matchPattern(word, pattern)) {
                    results.add(word);
                }
            }

            return results;
        }

        private boolean matchPattern(String word, String pattern) {
            if(word.length() != pattern.length()) {
                return false;
            }

            Map<Character, Character> wordMap = new HashMap<>();
            Map<Character, Character> patternMap = new HashMap<>();

            for(int i = 0; i < word.length(); i++) {
                char wc = word.charAt(i);
                char pc = pattern.charAt(i);

                if(wordMap.containsKey(wc)
                        && wordMap.get(wc) != pc) {
                    return false;
                }

                if(wordMap.containsKey(wc) && !patternMap.containsKey(pc)) {
                    return false;
                }

                if(patternMap.containsKey(pc) && patternMap.get(pc) != wc) {
                    return false;
                }

                wordMap.put(wc, pc);
                patternMap.put(pc, wc);
            }

            return true;
        }
    }

    class Solution_Classic {
        public List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> results = new ArrayList<>();
            if(words == null || words.length == 0 || pattern == null || pattern.length() == 0) {
                return results;
            }

            for(String word : words) {
                if(matchPattern(word, pattern)) {
                    results.add(word);
                }
            }

            return results;
        }

        private boolean matchPattern(String word, String pattern) {
            if(word.length() != pattern.length()) {
                return false;
            }

            Map<Character, Character> wordMap = new HashMap<>();
            Map<Character, Character> patternMap = new HashMap<>();

            int[] frequency = new int[26];
            for(int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                frequency[c - 'a']++;
            }

            for(int i = 0; i < word.length(); i++) {
                char wc = word.charAt(i);
                char pc = pattern.charAt(i);

                if(wordMap.containsKey(wc)
                        && wordMap.get(wc) != pc) {
                    return false;
                }

                if(wordMap.containsKey(wc) && !patternMap.containsKey(pc)) {
                    return false;
                }

                if(patternMap.containsKey(pc) && patternMap.get(pc) != wc) {
                    return false;
                }

                wordMap.put(wc, pc);
                patternMap.put(pc, wc);
                frequency[pc - 'a']--;
            }

            for(int i = 0; i < 26; i++) {
                if(frequency[i] != 0) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    890. Find and Replace Pattern
//    Medium
//    Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.
//
//    A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
//
//    Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.
//
//
//
//    Example 1:
//
//    Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
//    Output: ["mee","aqq"]
//    Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
//    "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
//    Example 2:
//
//    Input: words = ["a","b","c"], pattern = "a"
//    Output: ["a","b","c"]
//
//
//    Constraints:
//
//    1 <= pattern.length <= 20
//    1 <= words.length <= 50
//    words[i].length == pattern.length
//    pattern and words[i] are lowercase English letters.
