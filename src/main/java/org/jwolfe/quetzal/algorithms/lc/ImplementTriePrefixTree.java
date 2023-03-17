package org.jwolfe.quetzal.algorithms.lc;

public class ImplementTriePrefixTree {
    class Trie {
        private class TrieNode {
            TrieNode[] children;
            boolean isWord;

            public TrieNode() {
                children = new TrieNode[26];
            }

        }

        TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode node = root;

            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';

                if(node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
            }

            node.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode node = root;

            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';

                if(node.children[index] == null) {
                    return false;
                }

                node = node.children[index];
            }

            return node.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = root;

            for(int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                int index = c - 'a';

                if(node.children[index] == null) {
                    return false;
                }

                node = node.children[index];
            }

            return true;
        }
    }

    class Trie_Correct_1 {

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isEndOfWord;
        }

        TrieNode root;

        /** Initialize your data structure here. */
        public Trie_Correct_1() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(word == null || word.length() == 0) {
                root.isEndOfWord = true;
                return;
            }

            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if(node.children[index] == null) {
                    TrieNode childNode = new TrieNode();
                    node.children[index] = childNode;
                }

                node = node.children[index];
            }

            node.isEndOfWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if(word == null || word.length() == 0) {
                return root.isEndOfWord;
            }

            TrieNode node = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if(node.children[index] == null) {
                    return false;
                }

                node = node.children[index];
            }

            return node.isEndOfWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if(prefix == null || prefix.length() == 0) {
                return true;
            }

            TrieNode node = root;
            for(int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                int index = c - 'a';
                if(node.children[index] == null) {
                    return false;
                }

                node = node.children[index];
            }

            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

// ["Trie","insert","search","search","startsWith","insert","search"]
// [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]

// ["Trie","insert","startsWith"]
// [[],["hotdog"],["dog"]]

// ["Trie","insert","search","search","startsWith","insert","search"]
// [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
}

//    208. Implement Trie (Prefix Tree)
//    Medium
//    A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
//
//    Implement the Trie class:
//
//    Trie() Initializes the trie object.
//    void insert(String word) Inserts the string word into the trie.
//    boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
//    boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
//
//
//    Example 1:
//
//    Input
//    ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//    [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//    Output
//    [null, null, true, false, true, null, true]
//
//    Explanation
//    Trie trie = new Trie();
//    trie.insert("apple");
//    trie.search("apple");   // return True
//    trie.search("app");     // return False
//    trie.startsWith("app"); // return True
//    trie.insert("app");
//    trie.search("app");     // return True
//
//
//    Constraints:
//
//    1 <= word.length, prefix.length <= 2000
//    word and prefix consist only of lowercase English letters.
//    At most 3 * 104 calls in total will be made to insert, search, and startsWith.