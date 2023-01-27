package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class ConcatenatedWords {
    class Solution {
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            List<String> results = new ArrayList<>();
            if(words == null || words.length < 2) {
                return results;
            }

            Set<String> dictionary = new HashSet<>(Arrays.asList(words));

            for(String word : words) {
                int n = word.length();
                boolean[] dp = new boolean[n + 1];
                dp[0] = true;

                for(int i = 1; i <= n; i++) {
                    for(int j = (i == n ? 1 : 0); j < i; j++) {
                        if(dp[j]
                                && dictionary.contains(word.substring(j, i))) {
                            dp[i] = true;
                        }
                    }
                }

                if(dp[n]) {
                    results.add(word);
                }
            }

            return results;
        }

        private void print(boolean[] arr) {
            for(boolean a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}

//    472. Concatenated Words
//    Hard
//    Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
//
//    A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
//
//
//
//    Example 1:
//
//    Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
//    Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
//    Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
//    "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
//    "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
//    Example 2:
//
//    Input: words = ["cat","dog","catdog"]
//    Output: ["catdog"]
//
//
//    Constraints:
//
//    1 <= words.length <= 104
//    1 <= words[i].length <= 30
//    words[i] consists of only lowercase English letters.
//    All the strings of words are unique.
//    1 <= sum(words[i].length) <= 105