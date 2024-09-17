package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UncommonWordsFromTwoSentences {
    class Solution {
        public String[] uncommonFromSentences(String A, String B) {
            if ((A == null || A.length() == 0) && (B == null || B.length() == 0)) {
                return new String[0];
            }

            Map<String, Integer> frequencies = new HashMap<>();
            String[] wordsInA = A.split("\\s+");
            String[] wordsInB = B.split("\\s+");

            for (String word : wordsInA) {
                frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
            }

            for (String word : wordsInB) {
                frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
            }

            List<String> uncommonWords = new ArrayList<>();
            for (var entry : frequencies.entrySet()) {
                String key = entry.getKey();
                int count = entry.getValue();

                if (count == 1) {
                    uncommonWords.add(key);
                }
            }

            String[] results = new String[uncommonWords.size()];
            int index = 0;
            for (String word : uncommonWords) {
                results[index++] = word;
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public String[] uncommonFromSentences(String A, String B) {
            if ((A == null || A.length() == 0) && (B == null || B.length() == 0)) {
                return new String[0];
            }

            Map<String, Integer> frequencies = new HashMap<>();
            var wordsInA = A.split("\\s+");
            var wordsInB = B.split("\\s+");

            for (String word : wordsInA) {
                frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
            }

            for (String word : wordsInB) {
                frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
            }

            List<String> uncommonWords = new ArrayList<>();
            for (var entry : frequencies.entrySet()) {
                String word = entry.getKey();
                int count = entry.getValue();

                if (count == 1) {
                    uncommonWords.add(word);
                }
            }

            String[] results = new String[uncommonWords.size()];
            for (int i = 0; i < uncommonWords.size(); i++) {
                results[i] = uncommonWords.get(i);
            }

            return results;
        }
    }
}

//    884. Uncommon Words from Two Sentences
//    Easy
//    A sentence is a string of single-space separated words where each word consists only of lowercase letters.
//
//    A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
//
//    Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.
//
//
//
//    Example 1:
//
//    Input: s1 = "this apple is sweet", s2 = "this apple is sour"
//
//    Output: ["sweet","sour"]
//
//    Explanation:
//
//    The word "sweet" appears only in s1, while the word "sour" appears only in s2.
//
//    Example 2:
//
//    Input: s1 = "apple apple", s2 = "banana"
//
//    Output: ["banana"]
//
//
//
//    Constraints:
//
//    1 <= s1.length, s2.length <= 200
//    s1 and s2 consist of lowercase English letters and spaces.
//    s1 and s2 do not have leading or trailing spaces.
//    All the words in s1 and s2 are separated by a single space.