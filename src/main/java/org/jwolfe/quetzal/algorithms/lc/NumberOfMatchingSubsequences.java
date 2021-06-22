package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class NumberOfMatchingSubsequences {
    class Solution {
        public int numMatchingSubseq(String s, String[] words) {
            if(s == null || s.length() == 0 || words == null || words.length == 0) {
                return 0;
            }

            int count = 0;
            Map<String, Integer> wordCount = new HashMap<>();
            for(String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }

            for(String word : wordCount.keySet()) {
                if(isSubsequence(s, word)) {
                    count += wordCount.get(word);
                }
            }

            return count;
        }

        private boolean isSubsequence(String s, String word) {
            int m = s.length();
            int n = word.length();

            if(m < n) {
                return false;
            }

            int i = 0;
            int j = 0;

            for(; i < m; i++) {
                if(s.charAt(i) == word.charAt(j)) {
                    j++;

                    if(j == n) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    class Solution_First {
        public int numMatchingSubseq(String s, String[] words) {
            if(s == null || words == null || words.length == 0) {
                return 0;
            }

            int count = 0;
            Map<String, Boolean> cache = new HashMap<>();
            for(String word : words) {
                if(isSubsequence(s, word, cache)) {
                    count++;
                }
            }

            return count;
        }

        private boolean isSubsequence(String s, String word, Map<String, Boolean> cache) {
            if(cache.containsKey(word)) {
                return cache.get(word);
            }

            int j = 0;
            int n = word.length();
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == word.charAt(j)) {
                    j++;
                }

                if(j == n) {
                    cache.put(word, true);
                    return true;
                }
            }

            cache.put(word, j == n);
            return j == n;
        }
    }

// "abcde"
// ["a","bb","acd","ace"]
}

//    792. Number of Matching Subsequences
//    Medium
//    Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
//
//    A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
//
//    For example, "ace" is a subsequence of "abcde".
//
//
//    Example 1:
//
//    Input: s = "abcde", words = ["a","bb","acd","ace"]
//    Output: 3
//    Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
//    Example 2:
//
//    Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
//    Output: 2
//
//
//    Constraints:
//
//    1 <= s.length <= 5 * 104
//    1 <= words.length <= 5000
//    1 <= words[i].length <= 50
//    s and words[i] consist of only lowercase English letters.