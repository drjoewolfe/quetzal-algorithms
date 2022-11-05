package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class WordSearchII {
    class Solution {
        public List<String> findWords(char[][] board, String[] words) {
            List<String> results = new ArrayList<>();
            if(board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
                return results;
            }

            Trie trie = new Trie();
            for(String word : words) {
                trie.insert(word);
            }

            int m = board.length;
            int n = board[0].length;
            Set<String> matchedWords = new HashSet<>();

            boolean[][] visited = new boolean[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    searchWords(board, m, n, i, j, trie.root, visited, matchedWords);
                }
            }

            results.addAll(matchedWords);
            return results;
        }

        private void searchWords(char[][] board, int m, int n, int row, int col, TrieNode node, boolean[][] visited, Set<String> matchedWords) {
            if(row < 0 || row == m || col < 0 || col == n || visited[row][col]) {
                return;
            }

            visited[row][col] = true;
            char c = board[row][col];
            int index = c - 'a';

            if(node.children[index] != null) {
                TrieNode childNode = node.children[index];
                if(childNode.isWord) {
                    matchedWords.addAll(childNode.wordsEndingHere);
                }

                searchWords(board, m, n, row + 1, col, childNode, visited, matchedWords);
                searchWords(board, m, n, row - 1, col, childNode, visited, matchedWords);
                searchWords(board, m, n, row, col + 1, childNode, visited, matchedWords);
                searchWords(board, m, n, row, col - 1, childNode, visited, matchedWords);
            }

            visited[row][col] = false;
        }

        private void clear(boolean[][] arr) {
            for(boolean[] row : arr) {
                Arrays.fill(row, false);
            }
        }

        private class TrieNode {
            TrieNode[] children;

            boolean isWord;
            Set<String> wordsEndingHere;

            public TrieNode() {
                children = new TrieNode[26];
                wordsEndingHere = new HashSet<>();
            }
        }

        private class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

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
                node.wordsEndingHere.add(word);
            }
        }
    }

    class Solution_Correct_1 {

        class TrieNode {
            Map<Character, TrieNode> children;
            boolean isEndOfWord;

            public TrieNode() {
                this.children = new HashMap<>();
            }
        }

        class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public void insert(String word) {
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

            public boolean containsPrefix(String prefix) {
                TrieNode node = root;

                for(int i = 0; i < prefix.length(); i++) {
                    char c = prefix.charAt(i);

                    if(!node.children.containsKey(c)) {
                        return false;
                    }

                    node = node.children.get(c);
                }

                return true;
            }
        }

        public List<String> findWords(char[][] board, String[] words) {
            List<String> results = new ArrayList<>();

            if(board == null || board[0].length == 0) {
                return results;
            }

            int rowCount = board.length;
            int columnCount = board[0].length;

            for(char[] row : board) {
                if(row.length != columnCount) {
                    return results;
                }
            }

            Trie trie = getTrie(words);

            StringBuilder builder = new StringBuilder();
            boolean[][] visited = new boolean[rowCount][columnCount];
            for(int r = 0; r < rowCount; r++) {
                for(int c = 0; c < columnCount; c++) {
                    searchWords(board, r, c,  rowCount, columnCount, trie, builder, visited, results);
                }
            }

            return results;
        }

        private void searchWords(char[][] board, int r, int c, int rowCount, int columnCount, Trie trie, StringBuilder builder, boolean[][] visited, List<String> results) {
            if(r < 0 || r >= rowCount || c < 0 || c >= columnCount || visited[r][c]) {
                return;
            }

            visited[r][c] = true;
            char letter = board[r][c];
            builder.append(letter);

            String prefix = builder.toString();

            // System.out.println(prefix);
            if(trie.containsPrefix(prefix)) {
                if(trie.contains(prefix)) {
                    if(!results.contains(prefix)) {
                        results.add(prefix);
                    }
                }

                searchWords(board, r, c + 1,  rowCount, columnCount, trie, builder, visited, results);
                searchWords(board, r, c - 1,  rowCount, columnCount, trie, builder, visited, results);
                searchWords(board, r + 1, c,  rowCount, columnCount, trie, builder, visited, results);
                searchWords(board, r - 1, c,  rowCount, columnCount, trie, builder, visited, results);
            }

            builder.deleteCharAt(builder.length() - 1);
            visited[r][c] = false;
        }

        private Trie getTrie(String[] words) {
            Trie trie = new Trie();
            for(String word : words) {
                trie.insert(word);
            }

            return trie;
        }
    }

///////////////////////////////////////////////////////////////////

    class Solution_Classic {
        public List<String> findWords(char[][] board, String[] words) {
            List<String> results = new ArrayList<>();

            if(board == null || board[0].length == 0) {
                return results;
            }

            int rowCount = board.length;
            int columnCount = board[0].length;

            for(char[] row : board) {
                if(row.length != columnCount) {
                    return results;
                }
            }

            StringBuilder builder = new StringBuilder();
            boolean[][] visited = new boolean[rowCount][columnCount];
            for(int r = 0; r < rowCount; r++) {
                for(int c = 0; c < columnCount; c++) {
                    searchWords(board, r, c,  rowCount, columnCount, words, builder, visited, results);
                }
            }

            return results;
        }

        private void searchWords(char[][] board, int r, int c, int rowCount, int columnCount, String[] words, StringBuilder builder, boolean[][] visited, List<String> results) {
            if(r < 0 || r >= rowCount || c < 0 || c >= columnCount || visited[r][c]) {
                return;
            }

            visited[r][c] = true;
            char letter = board[r][c];
            builder.append(letter);

            String prefix = builder.toString();

            if(prefixExists(words, prefix)) {
                if(isValidWord(words, prefix)) {
                    if(!results.contains(prefix)) {
                        results.add(prefix);
                    }
                }

                searchWords(board, r, c + 1,  rowCount, columnCount, words, builder, visited, results);
                searchWords(board, r, c - 1,  rowCount, columnCount, words, builder, visited, results);
                searchWords(board, r + 1, c,  rowCount, columnCount, words, builder, visited, results);
                searchWords(board, r - 1, c,  rowCount, columnCount, words, builder, visited, results);
            }

            builder.deleteCharAt(builder.length() - 1);
            visited[r][c] = false;
        }

        private boolean prefixExists(String[] words, String prefix) {
            for(String word : words) {
                if(word.startsWith(prefix)) {
                    return true;
                }
            }

            return false;
        }

        private boolean isValidWord(String[] words, String candidate) {
            for(String word : words) {
                if(word.equals(candidate)) {
                    return true;
                }
            }

            return false;
        }
    }

// [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
// ["oath","pea","eat","rain"]

// [["o","a","b","n"],["o","t","a","e"],["a","h","k","r"],["a","f","l","v"]]
// ["oa","oaa"]

// [["a","b","c","e"],["x","x","c","d"],["x","x","b","a"]]
// ["abc","abcd"]
}

//    212. Word Search II
//    Hard
//    Given an m x n board of characters and a list of strings words, return all words on the board.
//
//    Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
//
//
//
//    Example 1:
//
//
//    Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
//    Output: ["eat","oath"]
//    Example 2:
//
//
//    Input: board = [["a","b"],["c","d"]], words = ["abcb"]
//    Output: []
//
//
//    Constraints:
//
//    m == board.length
//    n == board[i].length
//    1 <= m, n <= 12
//    board[i][j] is a lowercase English letter.
//    1 <= words.length <= 3 * 104
//    1 <= words[i].length <= 10
//    words[i] consists of lowercase English letters.
//    All the strings of words are unique.