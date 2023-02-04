package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class PermutationInString {
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            if(s1 == null || s1.length() == 0 || s2 == null || s2.length() < s1.length()) {
                return false;
            }

            int s1Length = s1.length();
            int s2Length = s2.length();

            int[] s1Frequencies = new int[26];
            for(int i = 0; i < s1Length; i++) {
                s1Frequencies[s1.charAt(i) - 'a']++;
            }

            for(int i = 0; i <= (s2Length - s1Length); i++) {
                int[] s2Frequencies = new int[26];

                for(int j = i; j < i + s1Length; j++) {
                    char c = s2.charAt(j);
                    s2Frequencies[c - 'a']++;
                }

                if(areEqual(s1Frequencies, s2Frequencies)) {
                    return true;
                }
            }

            return false;
        }

        private boolean areEqual(int[] arr1, int[] arr2) {
            for(int i = 0; i < arr1.length; i++) {
                if(arr1[i] != arr2[i]) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean checkInclusion(String s1, String s2) {
            if(s1 == null || s2 == null || s2.length() < s1.length()) {
                return false;
            }

            int stringLength = s2.length();
            int patternLength = s1.length();

            int[] counters = new int[26];
            for(int i = 0; i < patternLength; i++) {
                counters[s1.charAt(i) - 'a']++;
                counters[s2.charAt(i) - 'a']--;
            }

            for(int i = patternLength; i < stringLength; i++) {
                if(isZeroArray(counters)) {
                    return true;
                }

                int j = i - patternLength;
                counters[s2.charAt(j) - 'a']++;
                counters[s2.charAt(i) - 'a']--;
            }

            if(isZeroArray(counters)) {
                return true;
            }

            return false;
        }

        private boolean isZeroArray(int[] arr) {
            for(int n : arr) {
                if(n != 0) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Classic {
        public boolean checkInclusion(String s1, String s2) {
            if(s1 == null || s2 == null || s2.length() < s1.length()) {
                return false;
            }

            int stringLength = s2.length();
            int patternLength = s1.length();

            int[] patternFrequency = getCharacterFreqencies(s1, 0, patternLength - 1);
            int[] windowFrequency = getCharacterFreqencies(s2, 0, patternLength - 2);

            for(int i = 0; i + patternLength <= stringLength; i++) {
                int j = i + patternLength - 1;
                windowFrequency[s2.charAt(j)- 'a']++;
                if(areFrequenciesEqual(windowFrequency, patternFrequency)){
                    return true;
                }

                windowFrequency[s2.charAt(i) - 'a']--;
            }

            return false;
        }

        private boolean areFrequenciesEqual(int[] freq1, int[] freq2) {
            for(int i = 0; i < freq1.length; i++) {
                if(freq1[i] != freq2[i]) {
                    return false;
                }
            }

            return true;
        }

        private int[] getCharacterFreqencies(String s, int left, int right) {
            int[] frequency = new int[26];
            for(int i = left; i <= right; i++) {
                char c = s.charAt(i);
                frequency[c - 'a']++;
            }

            return frequency;
        }
    }

    class Solution_Brute {
        public boolean checkInclusion(String s1, String s2) {
            if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
                return false;
            }

            List<String> permutations = getAllPermutations(s1);
            for(String p : permutations) {
                if(s2.contains(p)) {
                    return true;
                }
            }

            return false;
        }

        private List<String> getAllPermutations(String s) {
            List<String> permutations = new ArrayList<>();
            generateAllPermutations(s.toCharArray(), 0, s.length() - 1, permutations);

            return permutations;
        }

        private void generateAllPermutations(char[] sArray, int left, int right, List<String> permutations) {
            if(left == right) {
                permutations.add(new String(sArray));
                return;
            }

            for(int i = left; i <= right; i++) {
                swap(sArray, left, i);
                generateAllPermutations(sArray, left + 1, right, permutations);
                swap(sArray, left, i);
            }
        }

        private void swap(char[] sArray, int left, int right) {
            char temp = sArray[left];
            sArray[left] = sArray[right];
            sArray[right] = temp;
        }

        private void print(List<String> list) {
            list.stream().forEach(s -> System.out.print(s + " "));
            System.out.println();
        }
    }

// "ab"
// "eidbaooo"
}

//    567. Permutation in String
//    Medium
//    Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
//
//    In other words, return true if one of s1's permutations is the substring of s2.
//
//
//
//    Example 1:
//
//    Input: s1 = "ab", s2 = "eidbaooo"
//    Output: true
//    Explanation: s2 contains one permutation of s1 ("ba").
//    Example 2:
//
//    Input: s1 = "ab", s2 = "eidboaoo"
//    Output: false
//
//
//    Constraints:
//
//    1 <= s1.length, s2.length <= 104
//    s1 and s2 consist of lowercase English letters.