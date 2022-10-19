package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class TopKFrequentWords {
    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            List<String> results = new ArrayList<>();
            if(words == null || words.length == 0 || k < 1) {
                return results;
            }

            Map<String, Integer> map = new HashMap<>();
            for(String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }

            PriorityQueue<WordFrequency> heap = new PriorityQueue<>();
            for(var entry : map.entrySet()) {
                String word = entry.getKey();
                int frequency = entry.getValue();

                WordFrequency wf = new WordFrequency(word, frequency);
                heap.offer(wf);

                if(heap.size() > k) {
                    heap.poll();
                }
            }

            while(!heap.isEmpty()) {
                results.add(0, heap.poll().word);
            }

            return results;
        }

        private class WordFrequency implements Comparable<WordFrequency> {
            String word;
            int frequency;

            public WordFrequency(String word, int frequency) {
                this.word = word;
                this.frequency = frequency;
            }

            @Override
            public int compareTo(WordFrequency other) {
                if(this.frequency == other.frequency) {
                    return other.word.compareTo(this.word);
                }

                return this.frequency - other.frequency;
            }
        }
    }

    class Solution_Correct_1 {
        public List<String> topKFrequent(String[] words, int k) {
            List<String> kFrequentWords = new ArrayList<>();
            if(words == null || words.length == 0) {
                return kFrequentWords;
            }

            Map<String, Integer> frequencies = new HashMap<>();
            for(String word : words) {
                frequencies.put(word, frequencies.getOrDefault(word, 0) + 1);
            }

            PriorityQueue<WordFrequency> heap = new PriorityQueue<>((wf1, wf2) -> {
                if(wf1.frequency == wf2.frequency) {
                    return wf2.word.compareTo(wf1.word);
                }
                return wf1.frequency - wf2.frequency;
            });

            for(Map.Entry<String, Integer> entry : frequencies.entrySet()) {
                heap.offer(new WordFrequency(entry.getKey(), entry.getValue()));
                if(heap.size() > k) {
                    heap.poll();
                }
            }

            while(!heap.isEmpty()) {
                kFrequentWords.add(0, heap.poll().word);
            }

            return kFrequentWords;
        }

        class WordFrequency {
            String word;
            int frequency;

            public WordFrequency(String word, int frequency) {
                this.word = word;
                this.frequency = frequency;
            }
        }
    }
}

//    692. Top K Frequent Words
//    Medium
//    Given an array of strings words and an integer k, return the k most frequent strings.
//
//    Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
//
//
//
//    Example 1:
//
//    Input: words = ["i","love","leetcode","i","love","coding"], k = 2
//    Output: ["i","love"]
//    Explanation: "i" and "love" are the two most frequent words.
//    Note that "i" comes before "love" due to a lower alphabetical order.
//    Example 2:
//
//    Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
//    Output: ["the","is","sunny","day"]
//    Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
//
//
//    Constraints:
//
//    1 <= words.length <= 500
//    1 <= words[i].length <= 10
//    words[i] consists of lowercase English letters.
//    k is in the range [1, The number of unique words[i]]
//
//
//    Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?