package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MapSumPairs {
    class MapSum {
        Trie trie;
        Map<String, Integer> map;

        /**
         * Initialize your data structure here.
         */
        public MapSum() {
            trie = new Trie();
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            int delta = val;
            if (map.containsKey(key)) {
                int oldVal = map.get(key);
                delta = val - oldVal;
            }

            map.put(key, val);
            trie.insert(key, delta);
        }

        public int sum(String prefix) {
            return trie.sum(prefix);
        }

        private class Trie {
            TrieNode root;

            public Trie() {
                this.root = new TrieNode();
            }

            public void insert(String key, int val) {
                TrieNode node = root;

                for (int i = 0; i < key.length(); i++) {
                    char c = key.charAt(i);
                    int index = c - 'a';

                    if (node.children[index] == null) {
                        node.children[index] = new TrieNode();
                    }

                    node = node.children[index];
                    node.sum += val;
                }

                node.isLeaf = true;
            }

            private int sum(String prefix) {
                TrieNode node = root;

                for (int i = 0; i < prefix.length(); i++) {
                    char c = prefix.charAt(i);
                    int index = c - 'a';

                    if (node.children[index] == null) {
                        return 0;
                    }

                    node = node.children[index];
                }

                return node.sum;
            }
        }

        private class TrieNode {
            TrieNode[] children;
            boolean isLeaf;

            int sum;

            public TrieNode() {
                this.children = new TrieNode[26];
            }
        }
    }

    /**
     * Your MapSum object will be instantiated and called as such:
     * MapSum obj = new MapSum();
     * obj.insert(key,val);
     * int param_2 = obj.sum(prefix);
     */


// ["MapSum", "insert", "sum", "insert", "sum"]
// [[], ["apple",3], ["ap"], ["app",2], ["ap"]]


    class MapSum_Correct_2 {
        Trie trie;
        Map<String, Integer> map;

        /**
         * Initialize your data structure here.
         */
        public MapSum_Correct_2() {
            trie = new Trie();
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            int delta = val;
            if (map.containsKey(key)) {
                delta = val - map.get(key);
            }

            trie.insert(key, delta);
            map.put(key, val);
        }

        public int sum(String prefix) {
            return trie.getSum(prefix);
        }

        private class Trie {
            TrieNode root;

            public Trie() {
                this.root = new TrieNode();
            }

            public void insert(String key, int delta) {
                TrieNode node = root;
                for (int i = 0; i < key.length(); i++) {
                    Character c = key.charAt(i);
                    if (!node.children.containsKey(c)) {
                        node.children.put(c, new TrieNode());
                    }

                    node = node.children.get(c);
                    node.sum += delta;
                }

                node.isWord = true;
            }

            public int getSum(String prefix) {
                TrieNode node = root;
                for (int i = 0; i < prefix.length(); i++) {
                    Character c = prefix.charAt(i);
                    if (!node.children.containsKey(c)) {
                        return 0;
                    }

                    node = node.children.get(c);
                }

                return node.sum;
            }
        }

        private class TrieNode {
            Map<Character, TrieNode> children;
            int sum;
            boolean isWord;

            public TrieNode() {
                this.children = new HashMap<>();
            }
        }
    }

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
}

//    677. Map Sum Pairs
//    Medium
//    Implement the MapSum class:
//
//    MapSum() Initializes the MapSum object.
//    void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
//    int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
//
//
//    Example 1:
//
//    Input
//    ["MapSum", "insert", "sum", "insert", "sum"]
//    [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
//    Output
//    [null, null, 3, null, 5]
//
//    Explanation
//    MapSum mapSum = new MapSum();
//    mapSum.insert("apple", 3);
//    mapSum.sum("ap");           // return 3 (apple = 3)
//    mapSum.insert("app", 2);
//    mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
//
//
//    Constraints:
//
//    1 <= key.length, prefix.length <= 50
//    key and prefix consist of only lowercase English letters.
//    1 <= val <= 1000
//    At most 50 calls will be made to insert and sum.
