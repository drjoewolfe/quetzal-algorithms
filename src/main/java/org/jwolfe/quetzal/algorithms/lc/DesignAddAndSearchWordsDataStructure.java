package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class DesignAddAndSearchWordsDataStructure {
    class WordDictionary {
        Trie trie;

        /** Initialize your data structure here. */
        public WordDictionary() {
            trie = new Trie();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            trie.add(word);
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return trie.search(word);
        }

        private class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public void add(String word) {
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

            public boolean search(String word) {
                return search(word, 0, root);
            }

            private boolean search(String word, int fromIndex, TrieNode node) {
                if(fromIndex == word.length()) {
                    return node.isWord;
                }

                for(int i = fromIndex; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if(c == '.') {
                        for(int j = 0; j < 26; j++) {
                            if(node.children[j] != null
                                    && search(word, i + 1, node.children[j])) {
                                return true;
                            }
                        }

                        return false;
                    } else {
                        int index = c - 'a';

                        if(node.children[index] == null) {
                            return false;
                        }

                        node = node.children[index];
                    }
                }

                return node.isWord;
            }
        }


        private class TrieNode {
            TrieNode[] children;
            boolean isWord;

            public TrieNode() {
                this.children = new TrieNode[26];
            }
        }
    }

    class WordDictionary_Correct_1 {
        Trie trie;

        /** Initialize your data structure here. */
        public WordDictionary_Correct_1() {
            trie = new Trie();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            trie.add(word);
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return trie.search(word);
        }

        class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public void add(String word) {
                if(word == null || word.length() == 0) {
                    return;
                }

                TrieNode node = root;
                for(int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if(!node.children.containsKey(c)) {
                        node.children.put(c, new TrieNode());
                    }

                    node = node.children.get(c);
                }

                node.isEndOfWord = true;
            }

            public boolean contains(String word) {
                if(word == null || word.length() == 0) {
                    return false;
                }

                TrieNode node = root;
                for(int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if(!node.children.containsKey(c)) {
                        return false;
                    }

                    node = node.children.get(c);
                }

                return node.isEndOfWord;
            }

            public boolean search(String wordRegex) {
                if(wordRegex == null || wordRegex.length() == 0) {
                    return false;
                }

                return search(wordRegex, root, 0);
            }

            private boolean search(String wordRegex, TrieNode node, int startIndex) {
                if(startIndex == wordRegex.length()) {
                    return node.isEndOfWord;
                }

                for(int i = startIndex; i < wordRegex.length(); i++) {
                    char c = wordRegex.charAt(i);
                    if(c == '.') {
                        for(TrieNode childNode : node.children.values()) {
                            if(search(wordRegex, childNode, i + 1)) {
                                return true;
                            }
                        }

                        return false;
                    } else if(!node.children.containsKey(c)) {
                        return false;
                    }

                    node = node.children.get(c);
                }

                return node.isEndOfWord;
            }
        }

        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isEndOfWord;

            public TrieNode() {
                children = new HashMap<>();
            }
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

// ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
// [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
}

//    211. Design Add and Search Words Data Structure
//    Medium
//
//    6328
//
//    361
//
//    Add to List
//
//    Share
//    Design a data structure that supports adding new words and finding if a string matches any previously added string.
//
//    Implement the WordDictionary class:
//
//    WordDictionary() Initializes the object.
//    void addWord(word) Adds word to the data structure, it can be matched later.
//    bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
//
//
//    Example:
//
//    Input
//    ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
//    [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//    Output
//    [null,null,null,null,false,true,true,true]
//
//    Explanation
//    WordDictionary wordDictionary = new WordDictionary();
//    wordDictionary.addWord("bad");
//    wordDictionary.addWord("dad");
//    wordDictionary.addWord("mad");
//    wordDictionary.search("pad"); // return False
//    wordDictionary.search("bad"); // return True
//    wordDictionary.search(".ad"); // return True
//    wordDictionary.search("b.."); // return True
//
//
//    Constraints:
//
//    1 <= word.length <= 25
//    word in addWord consists of lowercase English letters.
//    word in search consist of '.' or lowercase English letters.
//    There will be at most 3 dots in word for search queries.
//    At most 104 calls will be made to addWord and search.