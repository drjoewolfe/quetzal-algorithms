package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class ShortEncodingOfWords {
    public int minimumLengthEncoding(String[] words) {
        if(words == null || words.length == 0) {
            return 0;
        }

        SuffixTrie trie = new SuffixTrie();
        Map<TrieNode, Integer> map = new HashMap<>();

        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode node = trie.insert(word);

            map.put(node, i);
        }

        int length = 0;
        for(var entry : map.entrySet()) {
            TrieNode node = entry.getKey();
            if(node.isLeaf) {
                Integer index = entry.getValue();
                length += words[index].length();
                length++;
            }
        }

        return length;
    }

    static class TrieNode {
        TrieNode[] children;
        boolean isLeaf;
        boolean isWord;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    static class SuffixTrie {
        TrieNode root;

        public SuffixTrie() {
            this.root = new TrieNode();
        }

        public TrieNode insert(String s) {
            TrieNode node = root;
            for(int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);

                int index = c - 'a';
                if(node.children[index] == null) {
                    node.children[index] = new TrieNode();
                    node.isLeaf = false;
                }

                node = node.children[index];
            }

            node.isWord = true;
            for(int i = 0; i < 26; i++) {
                if(node.children[i] != null) {
                    node.isLeaf = false;
                    return node;
                }
            }

            node.isLeaf = true;
            return node;
        }
    }
}

//    820. Short Encoding of Words
//    Medium
//    A valid encoding of an array of words is any reference string s and array of indices indices such that:
//
//    words.length == indices.length
//    The reference string s ends with the '#' character.
//    For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
//    Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
//
//
//
//    Example 1:
//
//    Input: words = ["time", "me", "bell"]
//    Output: 10
//    Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
//    words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
//    words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
//    words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
//    Example 2:
//
//    Input: words = ["t"]
//    Output: 2
//    Explanation: A valid encoding would be s = "t#" and indices = [0].
//
//
//
//    Constraints:
//
//    1 <= words.length <= 2000
//    1 <= words[i].length <= 7
//    words[i] consists of only lowercase letters.
