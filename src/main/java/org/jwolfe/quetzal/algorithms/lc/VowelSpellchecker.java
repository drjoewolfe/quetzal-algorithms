package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VowelSpellchecker {
    class Solution {
        public String[] spellchecker(String[] wordlist, String[] queries) {
            if (wordlist == null || wordlist.length == 0 || queries == null || queries.length == 0) {
                return new String[0];
            }

            Set<String> exactWords = new HashSet<>();
            Map<String, String> capitalizedMap = new HashMap<>();
            Map<String, String> deVoweledMap = new HashMap<>();

            for (String word : wordlist) {
                exactWords.add(word);

                String lowerCaseWord = word.toLowerCase();
                capitalizedMap.putIfAbsent(lowerCaseWord, word);

                String deVoweledWord = deVowel(lowerCaseWord);
                deVoweledMap.putIfAbsent(deVoweledWord, word);
            }

            int n = queries.length;
            String[] answer = new String[n];
            for (int i = 0; i < n; i++) {
                String query = queries[i];
                answer[i] = solve(query, exactWords, capitalizedMap, deVoweledMap);
            }

            return answer;
        }

        private String solve(String query, Set<String> exactWords, Map<String, String> capitalizedMap, Map<String, String> deVoweledMap) {
            if (exactWords.contains(query)) {
                return query;
            }

            String lowerCaseQuery = query.toLowerCase();
            if (capitalizedMap.containsKey(lowerCaseQuery)) {
                return capitalizedMap.get(lowerCaseQuery);
            }

            String deVoweledQuery = deVowel(lowerCaseQuery);
            if (deVoweledMap.containsKey(deVoweledQuery)) {
                return deVoweledMap.get(deVoweledQuery);
            }

            return "";
        }

        private String deVowel(String word) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (isVowel(c)) {
                    builder.append("*");
                } else {
                    builder.append(c);
                }
            }

            return builder.toString();
        }

        private boolean isVowel(char c) {
            return (c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u');
        }
    }

    class Solution_Correct_1 {
        public String[] spellchecker(String[] wordlist, String[] queries) {
            if (wordlist == null || wordlist.length == 0 || queries == null || queries.length == 0) {
                return new String[0];
            }

            Set<String> wordSet = new HashSet<>();
            Map<String, String> lowerCasedMap = new HashMap<>();
            Map<String, String> vowelMaskedMap = new HashMap<>();

            for (String word : wordlist) {
                wordSet.add(word);

                String lowerCasedWord = word.toLowerCase();
                if (!lowerCasedMap.containsKey(lowerCasedWord)) {
                    lowerCasedMap.put(lowerCasedWord, word);
                }

                String vowelMaskedWord = getVowelMaskedWord(lowerCasedWord);
                if (!vowelMaskedMap.containsKey(vowelMaskedWord)) {
                    vowelMaskedMap.put(vowelMaskedWord, word);
                }
            }

            String[] output = new String[queries.length];
            for (int i = 0; i < queries.length; i++) {
                String query = queries[i];
                if (wordSet.contains(query)) {
                    output[i] = query;
                } else {
                    String lowerCasedQuery = query.toLowerCase();
                    if (lowerCasedMap.containsKey(lowerCasedQuery)) {
                        output[i] = lowerCasedMap.get(lowerCasedQuery);
                    } else {
                        String vowelMaskedWord = getVowelMaskedWord(lowerCasedQuery);
                        if (vowelMaskedMap.containsKey(vowelMaskedWord)) {
                            output[i] = vowelMaskedMap.get(vowelMaskedWord);
                        }
                    }
                }

                if (output[i] == null) {
                    output[i] = "";
                }
            }

            return output;
        }

        private String getVowelMaskedWord(String word) {
            StringBuilder builder = new StringBuilder(word);
            for (int i = 0; i < builder.length(); i++) {
                if (isVowel(builder.charAt(i))) {
                    builder.setCharAt(i, '*');
                }
            }

            return builder.toString();
        }

        private boolean isVowel(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        }
    }


// ["KiTe","kite","hare","Hare"]
// ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
}

//    966. Vowel Spellchecker
//    Medium
//    Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
//
//    For a given query word, the spell checker handles two categories of spelling mistakes:
//
//    Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
//    Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
//    Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
//    Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
//    Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
//    Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
//    Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
//    Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
//    In addition, the spell checker operates under the following precedence rules:
//
//    When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
//    When the query matches a word up to capitlization, you should return the first such match in the wordlist.
//    When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
//    If the query has no matches in the wordlist, you should return the empty string.
//    Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
//
//
//
//    Example 1:
//
//    Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
//    Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
//
//
//    Note:
//
//    1 <= wordlist.length <= 5000
//    1 <= queries.length <= 5000
//    1 <= wordlist[i].length <= 7
//    1 <= queries[i].length <= 7
//    All strings in wordlist and queries consist only of english letters.
