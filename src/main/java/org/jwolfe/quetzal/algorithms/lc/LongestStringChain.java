package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LongestStringChain {
    class Solution {
        public int longestStrChain(String[] words) {
            if(words == null || words.length == 0) {
                return 0;
            }

            int n = words.length;
            if(n < 2) {
                return 1;
            }

            Map<Integer, List<Token>> tokensByLength = new TreeMap<>();
            for(String w : words) {
                Token token = new Token(w);

                if(!tokensByLength.containsKey(token.length)) {
                    tokensByLength.put(token.length, new ArrayList<>());
                }

                tokensByLength.get(token.length).add(token);
            }

            int maxChainLength = 1;
            for(var entry : tokensByLength.entrySet()) {
                int length = entry.getKey();
                if(!tokensByLength.containsKey(length - 1)) {
                    continue;
                }

                List<Token> tokens = entry.getValue();
                List<Token> prevTokens = tokensByLength.get(length - 1);
                for(Token token : tokens) {
                    for(Token prevToken : prevTokens) {
                        if(isOneCharAdditionAway(token, prevToken)) {
                            token.chainLength = Math.max(token.chainLength, prevToken.chainLength + 1);
                        }
                    }

                    maxChainLength = Math.max(maxChainLength, token.chainLength);
                }
            }

            return maxChainLength;
        }

        private boolean isOneCharAdditionAway(Token token, Token prevToken) {
            int diff = 0;
            for(int i = 0; i < 26; i++) {
                if(token.frequency[i] != prevToken.frequency[i]) {
                    diff++;

                    if(diff > 1) {
                        break;
                    }
                }
            }

            if(diff != 1) {
                return false;
            }

            String word1 = token.word;
            String word2 = prevToken.word;

            int m = word1.length();
            int n = word2.length();

            diff = 0;
            int i = 0;
            int j = 0;
            for(; i < m && j < n; i++, j++) {
                if(word1.charAt(i) != word2.charAt(j)) {
                    i++;
                    diff++;

                    if(diff > 1) {
                        break;
                    }
                }
            }

            return (i == m && j == n && diff == 1) || (i == m - 1 && j == n && diff == 0);
        }
    }

    private static class Token {
        String word;
        int[] frequency;
        int length;
        int chainLength;


        public Token(String word) {
            this.word = word;
            this.length = word.length();
            this.frequency = Token.getFrequency(word);
            this.chainLength = 1;
        }

        private static int[] getFrequency(String word) {
            int[] frequency = new int[26];
            for(int i  = 0; i < word.length(); i++) {
                frequency[word.charAt(i) - 'a']++;
            }

            return frequency;
        }

        @Override
        public String toString() {
            return word + "[" + chainLength + "]";
        }
    }

// ["a","b","ba","bca","bda","bdca"]
// ["a","b","ab","bac"]
// ["xbc","pcxbcf","xb","cxbc","pcxbc"]
}

//    1048. Longest String Chain
//    Medium
//    Given a list of words, each word consists of English lowercase letters.
//
//    Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2. For example, "abc" is a predecessor of "abac".
//
//    A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
//
//    Return the longest possible length of a word chain with words chosen from the given list of words.
//
//
//
//    Example 1:
//
//    Input: words = ["a","b","ba","bca","bda","bdca"]
//    Output: 4
//    Explanation: One of the longest word chain is "a","ba","bda","bdca".
//    Example 2:
//
//    Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
//    Output: 5
//
//
//    Constraints:
//
//    1 <= words.length <= 1000
//    1 <= words[i].length <= 16
//    words[i] only consists of English lowercase letters.
