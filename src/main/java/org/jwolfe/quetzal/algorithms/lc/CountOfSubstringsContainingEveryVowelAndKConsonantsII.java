package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountOfSubstringsContainingEveryVowelAndKConsonantsII {
    class Solution {
        public long countOfSubstrings(String word, int k) {
            if (word == null || word.length() < (5 + k)) {
                return 0;
            }

            long count = 0;
            int n = word.length();

            int[] nextConsonentIndex = new int[n];
            int nextIndex = n;
            for (int i = n - 1; i >= 0; i--) {
                nextConsonentIndex[i] = nextIndex;

                char c = word.charAt(i);
                if (!isVowel(c)) {
                    nextIndex = i;
                }
            }

            HashMap<Character, Integer> vowelMap = new HashMap<>();
            int left = 0;
            int consonents = 0;
            for (int right = 0; right < n; right++) {
                char rc = word.charAt(right);
                if (isVowel(rc)) {
                    vowelMap.put(rc, vowelMap.getOrDefault(rc, 0) + 1);
                } else {
                    consonents++;
                }

                while (consonents > k) {
                    char lc = word.charAt(left);

                    if (isVowel(lc)) {
                        decrementInMap(vowelMap, lc);
                    } else {
                        consonents--;
                    }

                    left++;
                }

                while (left <= right
                        && vowelMap.keySet().size() == 5
                        && consonents == k) {

                    long numSubStrings = nextConsonentIndex[right] - right;
                    count += numSubStrings;

                    char lc = word.charAt(left);
                    if (isVowel(lc)) {
                        decrementInMap(vowelMap, lc);
                    } else {
                        consonents--;
                    }
                    left++;
                }
            }

            return count;
        }

        private void decrementInMap(Map<Character, Integer> map, char c) {
            if (map.get(c) == 1) {
                map.remove(c);
            } else {
                map.put(c, map.get(c) - 1);
            }
        }

        private boolean isVowel(char c) {
            return (c == 'a')
                    || (c == 'e')
                    || (c == 'i')
                    || (c == 'o')
                    || (c == 'u');
        }
    }
}

//    3306. Count of Substrings Containing Every Vowel and K Consonants II
//    Medium
//    You are given a string word and a non-negative integer k.
//
//    Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants.
//
//
//
//    Example 1:
//
//    Input: word = "aeioqq", k = 1
//
//    Output: 0
//
//    Explanation:
//
//    There is no substring with every vowel.
//
//    Example 2:
//
//    Input: word = "aeiou", k = 0
//
//    Output: 1
//
//    Explanation:
//
//    The only substring with every vowel and zero consonants is word[0..4], which is "aeiou".
//
//    Example 3:
//
//    Input: word = "ieaouqqieaouqq", k = 1
//
//    Output: 3
//
//    Explanation:
//
//    The substrings with every vowel and one consonant are:
//
//    word[0..5], which is "ieaouq".
//    word[6..11], which is "qieaou".
//    word[7..12], which is "ieaouq".
//
//
//    Constraints:
//
//    5 <= word.length <= 2 * 105
//    word consists only of lowercase English letters.
//    0 <= k <= word.length - 5