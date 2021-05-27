package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaximumProductOfWordLengths {
    class Solution {
        public int maxProduct(String[] words) {
            if(words == null || words.length == 0) {
                return 0;
            }

            int n = words.length;
            int[] masks = new int[n];
            for(int i = 0; i < n; i++) {
                String word = words[i];

                int mask = 0;
                for(int j = 0; j < word.length(); j++) {
                    char c = word.charAt(j);
                    int number = c - '0';

                    mask |= (1 << number);
                }

                masks[i] = mask;
            }

            int maxProduct = 0;
            for(int i = 0; i < n - 1; i++) {
                for(int j = i + 1; j < n; j++) {
                    if((masks[i] & masks[j]) == 0) {
                        // No characters in common
                        maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
                    }
                }
            }

            return maxProduct;
        }
    }

    class Solution_Standard {
        public int maxProduct(String[] words) {
            if(words == null || words.length == 0) {
                return 0;
            }

            List<Set<Character>> charSets = new ArrayList<>();
            for(String word : words) {
                Set<Character> set = new HashSet<>();
                for(int i = 0; i < word.length(); i++) {
                    set.add(word.charAt(i));
                }

                charSets.add(set);
            }

            int n = words.length;
            int maxProduct = 0;
            for(int i = 0; i < n - 1; i++) {
                String word1 = words[i];
                Set<Character> charSet1 = charSets.get(i);

                for(int j = i + 1; j < n; j++) {
                    String word2 = words[j];
                    Set<Character> charSet2 = charSets.get(j);

                    if(hasCommonLetters(charSet1, charSet2)) {
                        continue;
                    }

                    maxProduct = Math.max(maxProduct, word1.length() * word2.length());
                }
            }

            return maxProduct;
        }

        private boolean hasCommonLetters(Set<Character> charSet1, Set<Character> charSet2) {
            for(var c : charSet1) {
                if(charSet2.contains(c)) {
                    return true;
                }
            }

            return false;
        }
    }
}

//    318. Maximum Product of Word Lengths
//    Medium
//    Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.
//
//
//
//    Example 1:
//
//    Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
//    Output: 16
//    Explanation: The two words can be "abcw", "xtfn".
//    Example 2:
//
//    Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
//    Output: 4
//    Explanation: The two words can be "ab", "cd".
//    Example 3:
//
//    Input: words = ["a","aa","aaa","aaaa"]
//    Output: 0
//    Explanation: No such pair of words.
//
//
//    Constraints:
//
//    2 <= words.length <= 1000
//    1 <= words[i].length <= 1000
//    words[i] consists only of lowercase English letters.