package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SearchSuggestionsSystem {
    class Solution {
        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            List<List<String>> results = new ArrayList<>();
            if(products == null || products.length == 0) {
                return results;
            }

            Trie trie = new Trie();
            for(String p : products) {
                trie.insert(p);
            }

            for(int l = 1; l <= searchWord.length(); l++) {
                List<String> suggestions = trie.getSuggestionsForPrefix(searchWord.substring(0, l));
                results.add(suggestions);
            }

            return results;
        }
    }

    private static class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String str) {
            TrieNode node = root;
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                int index = c - 'a';
                if(node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
                node.addSuggestion(str);
            }

            node.isLeaf = true;
        }

        public List<String> getSuggestionsForPrefix(String prefix) {
            List<String> suggestions = new ArrayList<>();

            TrieNode node = root;
            for(int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                int index = c - 'a';
                if(node.children[index] == null) {
                    return suggestions;
                }

                node = node.children[index];
            }

            suggestions.addAll(node.suggestions);
            return suggestions;
        }
    }

    private static class TrieNode {
        TrieNode[] children;
        boolean isLeaf;
        TreeSet<String> suggestions;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.suggestions = new TreeSet<>();
        }

        public void addSuggestion(String str) {
            this.suggestions.add(str);
            if(this.suggestions.size() > 3) {
                this.suggestions.remove(this.suggestions.last());
            }
        }
    }
}

//    1268. Search Suggestions System
//    Medium
//
//    Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
//
//    Return list of lists of the suggested products after each character of searchWord is typed.
//
//
//
//    Example 1:
//
//    Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
//    Output: [
//    ["mobile","moneypot","monitor"],
//    ["mobile","moneypot","monitor"],
//    ["mouse","mousepad"],
//    ["mouse","mousepad"],
//    ["mouse","mousepad"]
//    ]
//    Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
//    After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
//    After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
//    Example 2:
//
//    Input: products = ["havana"], searchWord = "havana"
//    Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
//    Example 3:
//
//    Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
//    Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
//    Example 4:
//
//    Input: products = ["havana"], searchWord = "tatiana"
//    Output: [[],[],[],[],[],[],[]]
//
//
//    Constraints:
//
//    1 <= products.length <= 1000
//    There are no repeated elements in products.
//    1 <= Σ products[i].length <= 2 * 10^4
//    All characters of products[i] are lower-case English letters.
//    1 <= searchWord.length <= 1000
//    All characters of searchWord are lower-case English letters.