package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class RemoveLetterToEqualizeFrequency {
    class Solution {
        public boolean equalFrequency(String word) {
            if(word == null || word.length() == 0) {
                return false;
            }

            int n = word.length();
            for(int i = 0; i < n; i++) {
                if(areLetterCountsEqual(word, i)) {
                    return true;
                }
            }

            return false;
        }

        private boolean areLetterCountsEqual(String word, int indexToExclude) {
            int[] counts = new int[26];
            for(int i = 0; i < word.length(); i++) {
                if(i == indexToExclude) {
                    continue;
                }

                counts[word.charAt(i) - 'a']++;
            }

            int c = 0;
            for(int i = 0; i < 26; i++) {
                if(counts[i] == 0) {
                    continue;
                }

                if(c == 0) {
                    c = counts[i];
                } else if(c != counts[i]) {
                    return  false;
                }
            }

            return true;
        }
    }

    class Solution_Incorrect_2 {
        public boolean equalFrequency(String word) {
            if(word == null || word.length() == 0) {
                return false;
            }

            int n = word.length();
            if(n < 3) {
                return true;
            }

            int[] frequency = new int[26];
            for(int i = 0; i < n; i++) {
                char c = word.charAt(i);
                frequency[c - 'a']++;
            }

            Map<Integer, Integer> map = new HashMap<>();
            int uniqueLetters = 0;
            for(int i = 0; i < 26; i++) {
                int c = frequency[i];
                if(c == 0) {
                    continue;
                }

                uniqueLetters++;
                map.put(c, map.getOrDefault(c, 0) + 1);

                if(map.size() > 2) {
                    return false;
                }
            }

            if(map.size() == 1) {
                return uniqueLetters == 1 ? true : false;
            }

            List<Integer> list = new ArrayList<>(map.keySet());
            int f1 = list.get(0);
            int f2 = list.get(1);

            return Math.abs(f1 - f2) == 1;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

    class Solution_Incorrect {
        public boolean equalFrequency(String word) {
            if(word == null || word.length() == 0) {
                return true;
            }

            int[] counts = new int[26];
            for(int i = 0; i < word.length(); i++) {
                counts[word.charAt(i) - 'a']++;
            }

            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int i = 0; i < 26; i++) {
                int c = counts[i];
                if(c == 0) {
                    continue;
                }

                map.put(c, map.getOrDefault(c, 0) + 1);
                if(map.size() > 2) {
                    return false;
                }
            }

            System.out.println(map);
            if(map.size() == 1 && map.firstKey() == 1) {
                return true;
            }

            if(map.size() < 2) {
                return false;
            }

            Integer a = map.firstKey();
            Integer b = map.lastKey();

            if(map.get(a) == 1 || map.get(b) == 1) {
                return true;
            }

            return false;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// "abcc"
// "bac"
// "aazz"
}

//    2423. Remove Letter To Equalize Frequency
//    Easy
//    You are given a 0-indexed string word, consisting of lowercase English letters. You need to select one index and remove the letter at that index from word so that the frequency of every letter present in word is equal.
//
//    Return true if it is possible to remove one letter so that the frequency of all letters in word are equal, and false otherwise.
//
//    Note:
//
//    The frequency of a letter x is the number of times it occurs in the string.
//    You must remove exactly one letter and cannot chose to do nothing.
//
//
//    Example 1:
//
//    Input: word = "abcc"
//    Output: true
//    Explanation: Select index 3 and delete it: word becomes "abc" and each character has a frequency of 1.
//    Example 2:
//
//    Input: word = "aazz"
//    Output: false
//    Explanation: We must delete a character, so either the frequency of "a" is 1 and the frequency of "z" is 2, or vice versa. It is impossible to make all present letters have equal frequency.
//
//
//    Constraints:
//
//    2 <= word.length <= 100
//    word consists of lowercase English letters only.