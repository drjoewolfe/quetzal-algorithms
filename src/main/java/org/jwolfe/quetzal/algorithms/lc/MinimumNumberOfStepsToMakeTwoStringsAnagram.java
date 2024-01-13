package org.jwolfe.quetzal.algorithms.lc;

public class MinimumNumberOfStepsToMakeTwoStringsAnagram {
    class Solution {
        public int minSteps(String s, String t) {
            if(s == null || t == null || s.length() != t.length()) {
                return 0;
            }

            int[] counts = new int[26];
            int n = s.length();

            for(int i = 0; i < n; i++) {
                counts[s.charAt(i) - 'a']++;
            }

            for(int i = 0; i < n; i++) {
                counts[t.charAt(i) - 'a']--;
            }

            int steps = 0;
            for(int i = 0; i < 26; i++) {
                if(counts[i] < 0) {
                    steps += Math.abs(counts[i]);
                }
            }

            return steps;
        }
    }
}

//    1347. Minimum Number of Steps to Make Two Strings Anagram
//    Medium
//    You are given two strings of the same length s and t. In one step you can choose any character of t and replace it with another character.
//
//    Return the minimum number of steps to make t an anagram of s.
//
//    An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
//
//
//
//    Example 1:
//
//    Input: s = "bab", t = "aba"
//    Output: 1
//    Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
//    Example 2:
//
//    Input: s = "leetcode", t = "practice"
//    Output: 5
//    Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
//    Example 3:
//
//    Input: s = "anagram", t = "mangaar"
//    Output: 0
//    Explanation: "anagram" and "mangaar" are anagrams.
//
//
//    Constraints:
//
//    1 <= s.length <= 5 * 104
//    s.length == t.length
//    s and t consist of lowercase English letters only.