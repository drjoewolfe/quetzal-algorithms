package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class WordBreakII {
    class Solution {
        public List<String> wordBreak(String s, List<String> wordDict) {
            if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
                return new ArrayList<>();
            }

            Set<String> dictionary = new HashSet<>(wordDict);
            Map<String, List<String>> memo = new HashMap<>();
            return wordBreak(s, dictionary, memo);
        }

        private List<String> wordBreak(String s, Set<String> dictionary, Map<String, List<String>> memo) {
            if(memo.containsKey(s)) {
                return memo.get(s);
            }

            List<String> sentences = new ArrayList<>();
            for(int i = 0; i < s.length(); i++) {
                String word = s.substring(0, i + 1);
                if(!dictionary.contains(word)) {
                    continue;
                }

                if(i == s.length() - 1) {
                    sentences.add(word);
                } else {
                    String remainder = s.substring(i + 1);
                    List<String> remainderSentences = wordBreak(remainder, dictionary, memo);

                    for(String rSentence : remainderSentences) {
                        String sentence = word + " " + rSentence;
                        sentences.add(sentence);
                    }
                }
            }

            memo.put(s, sentences);
            return sentences;
        }
    }

    class Solution_Correct_1 {
        public List<String> wordBreak(String s, List<String> wordDict) {
            if(s == null || s.length() == 0) {
                return new ArrayList<>();
            }

            Set<String> dictionary = new HashSet<>(wordDict);
            return wordBreak(s, dictionary, new HashMap<>());
        }

        private List<String> wordBreak(String s, Set<String> dictionary, Map<String, List<String>> memo) {
            if(memo.containsKey(s)) {
                return memo.get(s);
            }

            int n = s.length();
            List<String> sentences = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                String word = s.substring(0, i + 1);

                if(dictionary.contains(word)) {
                    if(i == n - 1) {
                        sentences.add(word);
                    } else {
                        String remainder = s.substring(word.length());
                        List<String> remainderSentences = wordBreak(remainder, dictionary, memo);

                        for(var rSentence : remainderSentences) {
                            String sentence = word + " " + rSentence;
                            sentences.add(sentence);
                        }
                    }
                }
            }

            memo.put(s, sentences);
            return sentences;
        }
    }

    class Solution_Brute {
        public List<String> wordBreak(String s, List<String> wordDict) {
            List<String> sentences = new ArrayList<>();
            if(s == null || s.length() == 0) {
                return sentences;
            }

            Set<String> dictionary = new HashSet<>(wordDict);
            List<String> currentSentenceWords = new ArrayList<>();

            wordBreak(s, dictionary, 0, currentSentenceWords, sentences);
            return sentences;
        }

        private void wordBreak(String s, Set<String> dictionary, int wordStart, List<String> currentSentenceWords, List<String> sentences) {
            if(wordStart == s.length()) {
                StringBuilder builder = new StringBuilder();
                for(String word : currentSentenceWords) {
                    builder.append(word);
                    builder.append(" ");
                }

                builder.deleteCharAt(builder.length() - 1);

                sentences.add(builder.toString());
                return;
            }

            // for(int i = wordStart; i < s.length(); i++) {
            for(int i = s.length() - 1; i >= wordStart; i--) {
                String word = s.substring(wordStart, i + 1);

                if(dictionary.contains(word)) {
                    currentSentenceWords.add(word);
                    wordBreak(s, dictionary, i + 1, currentSentenceWords, sentences);

                    currentSentenceWords.remove(currentSentenceWords.size() - 1);
                }
            }
        }
    }

// "catsanddog"
// ["cat","cats","and","sand","dog"]
}

//    140. Word Break II
//    Hard
//    Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
//
//    Note that the same word in the dictionary may be reused multiple times in the segmentation.
//
//
//
//    Example 1:
//
//    Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
//    Output: ["cats and dog","cat sand dog"]
//    Example 2:
//
//    Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
//    Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
//    Explanation: Note that you are allowed to reuse a dictionary word.
//    Example 3:
//
//    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//    Output: []
//
//
//    Constraints:
//
//    1 <= s.length <= 20
//    1 <= wordDict.length <= 1000
//    1 <= wordDict[i].length <= 10
//    s and wordDict[i] consist of only lowercase English letters.
//    All the strings of wordDict are unique.
//    Input is generated in a way that the length of the answer doesn't exceed 105.