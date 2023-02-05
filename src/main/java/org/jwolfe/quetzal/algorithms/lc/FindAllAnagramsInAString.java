package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> results = new ArrayList<>();
            if(s == null || s.length() == 0 || p == null || s.length() < p.length()) {
                return results;
            }

            int sl = s.length();
            int pl = p.length();

            int[] patternFrequencies = getFrequencies(p, 0, pl - 1);
            int[] stringFrequencies = getFrequencies(s, 0, pl - 2);

            for(int i = pl - 1; i < sl; i++) {
                stringFrequencies[s.charAt(i) - 'a']++;
                if(areEqual(stringFrequencies, patternFrequencies)) {
                    results.add(i - pl + 1);
                }

                stringFrequencies[s.charAt(i - pl + 1) - 'a']--;
            }

            return results;
        }

        private int[] getFrequencies(String s, int start, int end) {
            int[] frequencies = new int[26];
            for(int i = start; i <= end; i++) {
                frequencies[s.charAt(i) - 'a']++;
            }

            return frequencies;
        }

        private boolean areEqual(int[] arr1, int[] arr2) {
            for(int i = 0; i < arr1.length; i++) {
                if(arr1[i] != arr2[i]) {
                    return false;
                }
            }

            return true;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Correct_1 {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> results = new ArrayList<>();

            if(s == null || p == null || s.length() < p.length()) {
                return results;
            }

            int sLength = s.length();
            int pLength = p.length();

            int[] patternFrequencies = getFrequencies(p, 0, pLength);
            int[] stringFrequencies = getFrequencies(s, 0, pLength - 1);

            for(int i = 0; i + pLength <= sLength; i++) {
                stringFrequencies[s.charAt(i + pLength - 1) - 'a']++;

                if(areFrequenciesEqual(stringFrequencies, patternFrequencies)) {
                    results.add(i);
                }

                stringFrequencies[s.charAt(i) - 'a']--;
            }

            return results;
        }

        private int[] getFrequencies(String s, int startIndex, int length) {
            int[] frequencies = new int[26];
            for(int i = startIndex; i < length; i++) {
                frequencies[s.charAt(i) - 'a']++;
            }

            return frequencies;
        }

        private boolean areFrequenciesEqual(int[] freq1, int[] freq2) {
            for(int i = 0; i < freq1.length; i++) {
                if(freq1[i] != freq2[i]) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    438. Find All Anagrams in a String
//    Medium
//    Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
//
//    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
//
//
//
//    Example 1:
//
//    Input: s = "cbaebabacd", p = "abc"
//    Output: [0,6]
//    Explanation:
//    The substring with start index = 0 is "cba", which is an anagram of "abc".
//    The substring with start index = 6 is "bac", which is an anagram of "abc".
//    Example 2:
//
//    Input: s = "abab", p = "ab"
//    Output: [0,1,2]
//    Explanation:
//    The substring with start index = 0 is "ab", which is an anagram of "ab".
//    The substring with start index = 1 is "ba", which is an anagram of "ab".
//    The substring with start index = 2 is "ab", which is an anagram of "ab".
//
//
//    Constraints:
//
//    1 <= s.length, p.length <= 3 * 104
//    s and p consist of lowercase English letters.