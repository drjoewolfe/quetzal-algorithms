package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class StreamOfCharacters {
    class StreamChecker {
        private ReverseTrie trie;
        private int maxWordLength;

        Deque<Character> stream;

        public StreamChecker(String[] words) {
            trie = new ReverseTrie();
            stream = new LinkedList<>();
            maxWordLength = 0;

            for(String word : words) {
                trie.insert(word);
                maxWordLength = Math.max(maxWordLength, word.length());
            }
        }

        public boolean query(char letter) {
            if(stream.size() == maxWordLength) {
                stream.removeFirst();
            }

            stream.addLast(letter);
            return trie.matchAny(stream);
        }

        private class TrieNode {
            TrieNode[] children;
            boolean isWord;

            public TrieNode() {
                children = new TrieNode[26];
            }
        }

        private class ReverseTrie {
            TrieNode root;

            public ReverseTrie() {
                root = new TrieNode();
            }

            public void insert(String word) {
                TrieNode node = root;
                for(int i = word.length() - 1; i >= 0; i--) {
                    char c = word.charAt(i);
                    int index = c - 'a';

                    if(node.children[index] == null) {
                        node.children[index] = new TrieNode();
                    }

                    node = node.children[index];
                }

                node.isWord = true;
            }

            public boolean matchAny(Deque<Character> stream) {
                TrieNode node = root;
                Iterator<Character> iter = stream.descendingIterator();
                while(iter.hasNext()) {
                    Character c = iter.next();
                    int index = c - 'a';

                    if(node.children[index] == null) {
                        return false;
                    }

                    node = node.children[index];
                    if(node.isWord) {
                        return true;
                    }
                }

                return false;
            }
        }
    }

    class StreamChecker_Trie_1 {
        ReverseTrie trie;
        Deque<Character> stream;
        int maxWordLength;

        public StreamChecker_Trie_1(String[] words) {
            trie = new ReverseTrie();
            stream = new LinkedList<>();

            maxWordLength = 0;
            for(String w : words) {
                trie.insert(w);
                maxWordLength = Math.max(maxWordLength, w.length());
            }
        }

        public boolean query(char letter) {
            if(stream.size() == maxWordLength) {
                stream.removeFirst();
            }

            stream.addLast(letter);

            return trie.matchAny(stream);
        }

        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isEndOfWord;

            public TrieNode() {
                children = new HashMap<>();
            }
        }

        class ReverseTrie {
            TrieNode root;

            public ReverseTrie() {
                root = new TrieNode();
            }

            public void insert(String word) {
                TrieNode node = root;
                for(int i = word.length() - 1; i >= 0; i--) {
                    char c = word.charAt(i);
                    if(!node.children.containsKey(c)) {
                        node.children.put(c, new TrieNode());
                    }

                    node = node.children.get(c);
                }

                node.isEndOfWord = true;
            }

            public boolean matchAny(Deque<Character> stream) {
                TrieNode node = root;
                var rIterator = stream.descendingIterator();
                while(rIterator.hasNext()) {
                    Character c = rIterator.next();
                    if(!node.children.containsKey(c)) {
                        return false;
                    }

                    node = node.children.get(c);
                    if(node.isEndOfWord) {
                        return true;
                    }
                }

                return false;
            }
        }
    }

    class StreamChecker_Classic {
        Set<String> storedWords;
        int maxWordLength;

        LinkedList<StreamListItem> queriedFragments;

        public StreamChecker_Classic(String[] words) {
            storedWords = new HashSet<>();
            queriedFragments = new LinkedList<>();

            maxWordLength = 0;
            for(String w : words) {
                storedWords.add(w);
                maxWordLength = Math.max(maxWordLength, w.length());
            }
        }

        public boolean query(char letter) {
            if(queriedFragments.size() == maxWordLength) {
                queriedFragments.removeFirst();
            }

            queriedFragments.add(new StreamListItem());

            boolean found = false;
            for(var listItem : queriedFragments) {
                listItem.merge(letter);

                if(storedWords.contains(listItem.word)) {
                    found = true;
                }
            }

            return found;
        }

        class StreamListItem {
            String word;

            public StreamListItem() {
                word = "";
            }

            public StreamListItem(char letter) {
                this();
                merge(letter);
            }

            public void merge(char letter) {
                word += letter;
            }
        }
    }

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
}

//    1032. Stream of Characters
//    Hard
//    Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.
//
//    For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
//
//    Implement the StreamChecker class:
//
//    StreamChecker(String[] words) Initializes the object with the strings array words.
//    boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
//
//
//    Example 1:
//
//    Input
//    ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
//    [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
//    Output
//    [null, false, false, false, true, false, true, false, false, false, false, false, true]
//
//    Explanation
//    StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
//    streamChecker.query("a"); // return False
//    streamChecker.query("b"); // return False
//    streamChecker.query("c"); // return False
//    streamChecker.query("d"); // return True, because 'cd' is in the wordlist
//    streamChecker.query("e"); // return False
//    streamChecker.query("f"); // return True, because 'f' is in the wordlist
//    streamChecker.query("g"); // return False
//    streamChecker.query("h"); // return False
//    streamChecker.query("i"); // return False
//    streamChecker.query("j"); // return False
//    streamChecker.query("k"); // return False
//    streamChecker.query("l"); // return True, because 'kl' is in the wordlist
//
//
//    Constraints:
//
//    1 <= words.length <= 2000
//    1 <= words[i].length <= 2000
//    words[i] consists of lowercase English letters.
//    letter is a lowercase English letter.
//    At most 4 * 104 calls will be made to query.