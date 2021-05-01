package org.jwolfe.quetzal.algorithms.lc;

import java.util.TreeSet;

public class PrefixAndSuffixSearch {
    class WordFilter {
        Trie trie;

        public WordFilter(String[] words) {
            trie = new Trie();

            for(int i = 0; i < words.length; i++) {
                String word = words[i];
                for(int j = word.length() - 1; j >= 0; j--) {
                    String suffix = word.substring(j, word.length());
                    String trieWord = suffix + "{" + word;

                    trie.insert(trieWord, i);
                }
            }
        }

        public int f(String prefix, String suffix) {
            String trieWord = suffix + "{" + prefix;
            TrieNode node = trie.searchPrefix(trieWord);
            if(node != null) {
                return node.tags.last();
            }

            return -1;
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String str, int tag) {
            TrieNode node = root;
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                int index = c - 'a';
                if(node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
                node.tags.add(tag);
            }

            node.isLeaf = true;
        }

        public TrieNode searchPrefix(String prefix) {
            TrieNode node = root;
            for(int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);

                int index = c - 'a';
                if(node.children[index] == null) {
                    return null;
                }

                node = node.children[index];
            }

            return node;
        }
    }

    static class TrieNode {
        TrieNode[] children;
        boolean isLeaf;
        TreeSet<Integer> tags;

        public TrieNode() {
            this.children = new TrieNode[27];
            tags = new TreeSet<>();
        }
    }

    class WordFilter_Brute {
        String[] words;

        public WordFilter_Brute(String[] words) {
            this.words = words;
        }

        public int f(String prefix, String suffix) {
            int index = -1;

            for(int i = 0; i < words.length; i++) {
                String str = words[i];
                if(str.startsWith(prefix) && str.endsWith(suffix)) {
                    index = i;
                }
            }

            return index;
        }
    }

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */

// ["WordFilter","f"]
// [[["apple"]],["a","e"]]

// ["WordFilter","f","f","f","f","f","f","f","f","f","f"]
// [[["cabaabaaaa","ccbcababac","bacaabccba","bcbbcbacaa","abcaccbcaa","accabaccaa","cabcbbbcca","ababccabcb","caccbbcbab","bccbacbcba"]],["bccbacbcba","a"],["ab","abcaccbcaa"],["a","aa"],["cabaaba","abaaaa"],["cacc","accbbcbab"],["ccbcab","bac"],["bac","cba"],["ac","accabaccaa"],["bcbb","aa"],["ccbca","cbcababac"]]
}

//    745. Prefix and Suffix Search
//    Hard
//    Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.
//
//    Implement the WordFilter class:
//
//    WordFilter(string[] words) Initializes the object with the words in the dictionary.
//    f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
//
//
//    Example 1:
//
//    Input
//    ["WordFilter", "f"]
//    [[["apple"]], ["a", "e"]]
//    Output
//    [null, 0]
//
//    Explanation
//    WordFilter wordFilter = new WordFilter(["apple"]);
//    wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
//
//
//    Constraints:
//
//    1 <= words.length <= 15000
//    1 <= words[i].length <= 10
//    1 <= prefix.length, suffix.length <= 10
//    words[i], prefix and suffix consist of lower-case English letters only.
//    At most 15000 calls will be made to the function f.