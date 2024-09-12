package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class CountTheNumberOfConsistentStrings {
    class Solution {
        public int countConsistentStrings(String allowed, String[] words) {
            if (allowed == null || allowed.length() == 0 || words == null || words.length == 0) {
                return 0;
            }

            Set<Character> set = new HashSet<>();
            for (int i = 0; i < allowed.length(); i++) {
                char c = allowed.charAt(i);
                set.add(c);
            }

            int count = 0;
            for (String word : words) {
                int i = 0;
                for (; i < word.length(); i++) {
                    if (!set.contains(word.charAt(i))) {
                        break;
                    }
                }

                if (i == word.length()) {
                    count++;
                }
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int countConsistentStrings(String allowed, String[] words) {
            if (allowed == null || allowed.length() == 0 || words == null || words.length == 0) {
                return 0;
            }

            boolean[] map = new boolean[26];
            for (int i = 0; i < allowed.length(); i++) {
                char c = allowed.charAt(i);
                map[c - 'a'] = true;
            }

            int count = 0;
            for (String word : words) {
                boolean consistent = true;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!map[c - 'a']) {
                        consistent = false;
                        break;
                    }
                }

                if (consistent) {
                    count++;
                }
            }

            return count;
        }
    }
}

//    1684. Count the Number of Consistent Strings
//    Easy
//    You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.
//
//    Return the number of consistent strings in the array words.
//
//
//
//    Example 1:
//
//    Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
//    Output: 2
//    Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
//    Example 2:
//
//    Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
//    Output: 7
//    Explanation: All strings are consistent.
//    Example 3:
//
//    Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
//    Output: 4
//    Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
//
//
//    Constraints:
//
//    1 <= words.length <= 104
//    1 <= allowed.length <= 26
//    1 <= words[i].length <= 10
//    The characters in allowed are distinct.
//    words[i] and allowed contain only lowercase English letters.