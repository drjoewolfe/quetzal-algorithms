package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class FindResultantArrayAfterRemovingAnagrams {
    class Solution {
        public List<String> removeAnagrams(String[] words) {
            List<String> results = new ArrayList<>();
            if (words == null || words.length == 0) {
                return results;
            }

            results.add(words[0]);

            int n = words.length;
            for (int i = 1; i < n; i++) {
                String word1 = words[i - 1];
                String word2 = words[i];

                if (!areAnagrams(word1, word2)) {
                    results.add(word2);
                }
            }

            return results;
        }

        private boolean areAnagrams(String word1, String word2) {
            int[] frequency = new int[26];
            for (int i = 0; i < word1.length(); i++) {
                char c = word1.charAt(i);
                frequency[c - 'a']++;
            }

            for (int i = 0; i < word2.length(); i++) {
                char c = word2.charAt(i);
                frequency[c - 'a']--;
            }

            for (int i = 0; i < 26; i++) {
                if (frequency[i] != 0) {
                    return false;
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public List<String> removeAnagrams(String[] words) {
            List<String> results = new ArrayList<>();
            if (words == null || words.length == 0) {
                return results;
            }

            String startWord = words[0];
            for (int i = 1; i < words.length; i++) {
                String word = words[i];
                if (!areAnagrams(startWord, word)) {
                    results.add(startWord);
                    startWord = word;
                }
            }

            results.add(startWord);
            return results;
        }

        private boolean areAnagrams(String word1, String word2) {
            if (word1.length() != word2.length()) {
                return false;
            }

            int[] arr = new int[26];
            for (int i = 0; i < word1.length(); i++) {
                arr[word1.charAt(i) - 'a']++;
                arr[word2.charAt(i) - 'a']--;
            }

            for (int i = 0; i < 26; i++) {
                if (arr[i] != 0) {
                    return false;
                }
            }

            return true;
        }
    }
}

//    2273. Find Resultant Array After Removing Anagrams
//    Easy
//    You are given a 0-indexed string array words, where words[i] consists of lowercase English letters.
//
//    In one operation, select any index i such that 0 < i < words.length and words[i - 1] and words[i] are anagrams, and delete words[i] from words. Keep performing this operation as long as you can select an index that satisfies the conditions.
//
//    Return words after performing all operations. It can be shown that selecting the indices for each operation in any arbitrary order will lead to the same result.
//
//    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase using all the original letters exactly once. For example, "dacb" is an anagram of "abdc".
//
//
//
//    Example 1:
//
//    Input: words = ["abba","baba","bbaa","cd","cd"]
//    Output: ["abba","cd"]
//    Explanation:
//    One of the ways we can obtain the resultant array is by using the following operations:
//    - Since words[2] = "bbaa" and words[1] = "baba" are anagrams, we choose index 2 and delete words[2].
//    Now words = ["abba","baba","cd","cd"].
//    - Since words[1] = "baba" and words[0] = "abba" are anagrams, we choose index 1 and delete words[1].
//    Now words = ["abba","cd","cd"].
//    - Since words[2] = "cd" and words[1] = "cd" are anagrams, we choose index 2 and delete words[2].
//    Now words = ["abba","cd"].
//    We can no longer perform any operations, so ["abba","cd"] is the final answer.
//    Example 2:
//
//    Input: words = ["a","b","c","d","e"]
//    Output: ["a","b","c","d","e"]
//    Explanation:
//    No two adjacent strings in words are anagrams of each other, so no operations are performed.
//
//
//    Constraints:
//
//    1 <= words.length <= 100
//    1 <= words[i].length <= 10
//    words[i] consists of lowercase English letters.