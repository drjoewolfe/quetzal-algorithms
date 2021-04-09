package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class VerifyingAnAlienDictionary {
    class Solution {
        public boolean isAlienSorted(String[] words, String order) {
            if(words == null || words.length == 0 || order == null || order.length() != 26) {
                return false;
            }

            Map<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < 26; i++) {
                map.put(order.charAt(i), i);
            }

            for(int i = 1; i < words.length; i++) {
                String firstWord = words[i - 1];
                String secondWord = words[i];

                if(compare(firstWord, secondWord, map) > 0) {
                    return false;
                }
            }

            return true;
        }

        private int compare(String firstWord, String secondWord, Map<Character, Integer> map) {
            int m = firstWord.length();
            int n = secondWord.length();

            int i = 0;
            while(i < m && i < n) {
                char fc = firstWord.charAt(i);
                char sc = secondWord.charAt(i);

                if(map.get(fc) < map.get(sc)) {
                    return -1;
                } else if(map.get(fc) > map.get(sc)) {
                    return 1;
                }

                i++;
            }

            if(i == m && i == n) {
                return 0;
            } else if(i == m) {
                return -1;
            } else {
                return 1;
            }
        }
    }

// ["hello","leetcode"]
// "hlabcdefgijkmnopqrstuvwxyz"
}

//    953. Verifying an Alien Dictionary
//    Easy
//    In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
//
//    Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
//
//
//
//    Example 1:
//
//    Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
//    Output: true
//    Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
//    Example 2:
//
//    Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
//    Output: false
//    Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
//    Example 3:
//
//    Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
//    Output: false
//    Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
//
//
//    Constraints:
//
//    1 <= words.length <= 100
//    1 <= words[i].length <= 20
//    order.length == 26
//    All characters in words[i] and order are English lowercase letters.
