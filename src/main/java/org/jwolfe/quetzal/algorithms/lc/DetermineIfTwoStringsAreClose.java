package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class DetermineIfTwoStringsAreClose {
    class Solution {
        public boolean closeStrings(String word1, String word2) {
            if(word1 == null || word2 == null || word1.length() != word2.length()) {
                return false;
            }

            Set<Character> set1 = new HashSet<>();
            Set<Character> set2 = new HashSet<>();

            int[] frequencies1 = new int[26];
            int[] frequencies2 = new int[26];
            for(int i = 0; i < word1.length(); i++) {
                char c1 = word1.charAt(i);
                char c2 = word2.charAt(i);

                set1.add(c1);
                set2.add(c2);

                frequencies1[c1 - 'a']++;
                frequencies2[c2 - 'a']++;
            }

            Arrays.sort(frequencies1);
            Arrays.sort(frequencies2);

            return set1.equals(set2) && Arrays.equals(frequencies1, frequencies2);
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public boolean closeStrings(String word1, String word2) {
            if(word1 == null || word2 == null || word1.length() != word2.length()) {
                return false;
            }

            int n = word1.length();
            if(n < 1) {
                return true;
            }

            Map<Character, Integer> aCounters = new HashMap<>();
            Map<Character, Integer> bCounters = new HashMap<>();

            for(int i = 0; i < n; i++) {
                char a = word1.charAt(i);
                aCounters.put(a, aCounters.getOrDefault(a, 0) + 1);

                char b = word2.charAt(i);
                bCounters.put(b, bCounters.getOrDefault(b, 0) + 1);
            }

            Map<Integer, Integer> checks = new HashMap<>();
            for(var aEntry : aCounters.entrySet()) {
                Character key = aEntry.getKey();
                if(!bCounters.containsKey(key)) {
                    return false;
                }

                Integer count = aEntry.getValue();
                checks.put(count, checks.getOrDefault(count, 0) + 1);
            }

            for(var bEntry : bCounters.entrySet()) {
                Integer count = bEntry.getValue();
                checks.put(count, checks.getOrDefault(count, 0) - 1);
            }

            for(var entry : checks.entrySet()) {
                if(entry.getValue() != 0) {
                    return false;
                }
            }

            return true;
        }
    }

// "abc"
// "bca"

// "cabbba"
// "aabbss"

// "abbzzca"
// "babzzcz"
}

//    1657. Determine if Two Strings Are Close
//    Medium
//    Two strings are considered close if you can attain one from the other using the following operations:
//
//    Operation 1: Swap any two existing characters.
//    For example, abcde -> aecdb
//    Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
//    For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
//    You can use the operations on either string as many times as necessary.
//
//    Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
//
//
//
//    Example 1:
//
//    Input: word1 = "abc", word2 = "bca"
//    Output: true
//    Explanation: You can attain word2 from word1 in 2 operations.
//    Apply Operation 1: "abc" -> "acb"
//    Apply Operation 1: "acb" -> "bca"
//    Example 2:
//
//    Input: word1 = "a", word2 = "aa"
//    Output: false
//    Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
//    Example 3:
//
//    Input: word1 = "cabbba", word2 = "abbccc"
//    Output: true
//    Explanation: You can attain word2 from word1 in 3 operations.
//    Apply Operation 1: "cabbba" -> "caabbb"
//    Apply Operation 2: "caabbb" -> "baaccc"
//    Apply Operation 2: "baaccc" -> "abbccc"
//
//
//    Constraints:
//
//    1 <= word1.length, word2.length <= 105
//    word1 and word2 contain only lowercase English letters.