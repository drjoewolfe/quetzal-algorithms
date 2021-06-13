package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePairs {
    class Solution {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> pairs = new ArrayList<>();
            if(words == null || words.length < 2) {
                return pairs;
            }

            int n = words.length;

            Trie trie = new Trie();
            for(int i = 0; i < n; i++) {
                String word = words[i];
                trie.insert(word, i);
            }

            for(int i = 0; i < n; i++) {
                String word = words[i];
                trie.searchPalindromePairs(word, words, i, pairs);
            }

            return pairs;
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word, int index) {
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) {
                node.wordsPassingHere.add(index);

                char c = word.charAt(i);
                int j = c - 'a';

                if(node.children[j] == null) {
                    node.children[j] = new TrieNode();
                }

                node = node.children[j];
            }

            node.isWord = true;
            node.wordsEndingHere.add(index);
        }

        public void searchPalindromePairs(String word, String[] words, int index, List<List<Integer>> results) {
            TrieNode node = root;

            int n = word.length();
            for(int i = n - 1; i >= 0; i--) {
                if(node.isWord && isPalindrome(word, 0, i)) {
                    for(Integer k : node.wordsEndingHere) {
                        if(k != index) {
                            results.add(Arrays.asList(k, index));
                        }
                    }
                }

                char c = word.charAt(i);
                int j = c - 'a';

                if(node.children[j] == null) {
                    return;
                }

                node = node.children[j];
            }

            for(Integer k : node.wordsPassingHere) {
                String candidate = words[k];

                if(k != index && isPalindrome(candidate, n, candidate.length() - 1)) {
                    results.add(Arrays.asList(k, index));
                }
            }

            for(Integer k : node.wordsEndingHere) {
                String candidate = words[k];

                if(k != index && isPalindrome(candidate, n, candidate.length() - 1)) {
                    results.add(Arrays.asList(k, index));
                }
            }
        }

        private boolean isPalindrome(String word, int i, int j) {
            while(i < j) {
                if(word.charAt(i++) != word.charAt(j--)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class TrieNode {
        private TrieNode[] children;
        private boolean isWord;
        private List<Integer> wordsPassingHere;
        private List<Integer> wordsEndingHere;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.wordsPassingHere = new ArrayList<>();
            this.wordsEndingHere = new ArrayList<>();
        }
    }

    class Solution_Brute {
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> pairs = new ArrayList<>();
            if(words == null || words.length < 2) {
                return pairs;
            }

            int n = words.length;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i == j) {
                        continue;
                    }

                    if(isPalindromePair(words[i], words[j])) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(j);

                        pairs.add(pair);
                    }
                }
            }

            return pairs;
        }

        private boolean isPalindromePair(String word1, String word2) {
            String word = word1 + word2;

            int i = 0;
            int j = word.length() - 1;
            while(i < j) {
                if(word.charAt(i) != word.charAt(j)) {
                    return false;
                }

                i++;
                j--;
            }

            return true;
        }
    }

// ["abcd","dcba","lls","s","sssll"]
// ["a",""]
// ["a","abc","aba",""]
}

//    336. Palindrome Pairs
//    Hard
//    Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.
//
//
//
//    Example 1:
//
//    Input: words = ["abcd","dcba","lls","s","sssll"]
//    Output: [[0,1],[1,0],[3,2],[2,4]]
//    Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
//    Example 2:
//
//    Input: words = ["bat","tab","cat"]
//    Output: [[0,1],[1,0]]
//    Explanation: The palindromes are ["battab","tabbat"]
//    Example 3:
//
//    Input: words = ["a",""]
//    Output: [[0,1],[1,0]]
//
//
//    Constraints:
//
//    1 <= words.length <= 5000
//    0 <= words[i].length <= 300
//    words[i] consists of lower-case English letters.