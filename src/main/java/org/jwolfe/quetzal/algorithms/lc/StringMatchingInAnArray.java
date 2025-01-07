package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringMatchingInAnArray {
    class Solution {
        public List<String> stringMatching(String[] words) {
            List<String> subStrings = new ArrayList<>();
            if (words == null || words.length < 2) {
                return subStrings;
            }

            int n = words.length;
            Set<String> matched = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String word1 = words[i];
                int[] lps = computeLPS(word1);

                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }

                    String word2 = words[j];
                    if (!matched.contains(word2)
                            && isSubString(word1, word2, lps)) {
                        subStrings.add(word2);
                        matched.add(word2);
                    }
                }
            }

            return subStrings;
        }

        private int[] computeLPS(String word) {
            int n = word.length();
            int[] lps = new int[n];

            int i = 0;
            int j = 1;

            while (j < n) {
                char c1 = word.charAt(i);
                char c2 = word.charAt(j);

                if (c1 == c2) {
                    i++;
                    lps[j] = i;
                    j++;
                } else {
                    if (i > 0) {
                        i = lps[i - 1];
                    } else {
                        j++;
                    }
                }
            }

            return lps;
        }

        private boolean isSubString(String main, String sub, int[] lps) {
            int i = 0;
            int j = 0;

            int n = main.length();
            int m = sub.length();

            while (i < n) {
                char c1 = main.charAt(i);
                char c2 = sub.charAt(j);

                if (c1 == c2) {
                    i++;
                    j++;

                    if (j == m) {
                        return true;
                    }
                } else {
                    if (j > 0) {
                        j = lps[j - 1];
                    } else {
                        i++;
                    }
                }
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public List<String> stringMatching(String[] words) {
            List<String> subStrings = new ArrayList<>();
            if (words == null || words.length < 2) {
                return subStrings;
            }


            for (int i = 0; i < words.length; i++) {
                String word = words[i];

                for (int j = 0; j < words.length; j++) {
                    if (i == j) {
                        continue;
                    }

                    String candidate = words[j];
                    if (word.length() > candidate.length()) {
                        continue;
                    }

                    if (candidate.contains(word)) {
                        subStrings.add(word);
                        break;
                    }
                }
            }

            return subStrings;
        }
    }
}

//    1408. String Matching in an Array
//    Easy
//    Given an array of string words, return all strings in words that is a substring of another word. You can return the answer in any order.
//
//    A substring is a contiguous sequence of characters within a string
//
//
//
//    Example 1:
//
//    Input: words = ["mass","as","hero","superhero"]
//    Output: ["as","hero"]
//    Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
//    ["hero","as"] is also a valid answer.
//    Example 2:
//
//    Input: words = ["leetcode","et","code"]
//    Output: ["et","code"]
//    Explanation: "et", "code" are substring of "leetcode".
//    Example 3:
//
//    Input: words = ["blue","green","bu"]
//    Output: []
//    Explanation: No string of words is substring of another string.
//
//
//    Constraints:
//
//    1 <= words.length <= 100
//    1 <= words[i].length <= 30
//    words[i] contains only lowercase English letters.
//    All the strings of words are unique.