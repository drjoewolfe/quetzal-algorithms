package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MakeNumberOfDistinctCharactersEqual {
    class Solution {
        public boolean isItPossible(String word1, String word2) {
            if(word1 == null || word2 == null) {
                return false;
            }

            int[] count1 = getCounts(word1);
            int[] count2 = getCounts(word2);

            for(int i = 0; i < 26; i++) {
                if(count1[i] == 0) {
                    continue;
                }

                for(int j = 0; j < 26; j++) {
                    if(count2[j] == 0) {
                        continue;
                    }

                    // Try swap
                    count1[i]--;
                    count1[j]++;
                    count2[i]++;
                    count2[j]--;

                    if(distinct(count1) == distinct(count2)) {
                        return true;
                    }

                    count1[i]++;
                    count1[j]--;
                    count2[i]--;
                    count2[j]++;
                }
            }

            return false;
        }

        private int[] getCounts(String word) {
            int[] counts = new int[26];
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                counts[c - 'a']++;
            }

            return counts;
        }

        private int distinct(int[] counts) {
            int count = 0;
            for(int i = 0; i < counts.length; i++) {
                if(counts[i] > 0) {
                    count++;
                }
            }

            return count;
        }
    }

    class Solution_InCorrect {
        public boolean isItPossible(String word1, String word2) {
            if(word1 == null || word2 == null) {
                return false;
            }

            Map<Character, Integer> map1 = getMap(word1);
            Map<Character, Integer> map2 = getMap(word2);

            int size1 = map1.keySet().size();
            int size2 = map2.keySet().size();

            if(size1 == size2 && size1 != 1) {
                return true;
            }

            if(Math.abs(size1 - size2) > 1) {
                return false;
            }

            return hasCandidate(map1) && hasCandidate(map2);
        }

        private Map<Character, Integer> getMap(String word) {
            Map<Character, Integer> map = new HashMap<>();

            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            return map;
        }

        private boolean hasCandidate(Map<Character, Integer> map) {
            boolean candidate = false;
            for(var entry : map.entrySet()) {
                if(entry.getValue() > 1) {
                    candidate = true;
                    break;
                }
            }

            return candidate;
        }
    }

// "ac"
// "b"

// "abcc"
// "aab"

// "abcde"
// "fghij"

// "a"
// "bb"
}

//    2531. Make Number of Distinct Characters Equal
//    Medium
//    You are given two 0-indexed strings word1 and word2.
//
//    A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].
//
//    Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.
//
//
//
//    Example 1:
//
//    Input: word1 = "ac", word2 = "b"
//    Output: false
//    Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
//    Example 2:
//
//    Input: word1 = "abcc", word2 = "aab"
//    Output: true
//    Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.
//    Example 3:
//
//    Input: word1 = "abcde", word2 = "fghij"
//    Output: true
//    Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.
//
//
//    Constraints:
//
//    1 <= word1.length, word2.length <= 105
//    word1 and word2 consist of only lowercase English letters.