package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountCommonWordsWithOneOccurrence {
    class Solution {
        public int countWords(String[] words1, String[] words2) {
            if(words1 == null || words1.length == 0 || words2 == null || words2.length == 0) {
                return 0;
            }

            Map<String, Integer> words1Counts = new HashMap<>();
            Map<String, Integer> words2Counts = new HashMap<>();

            for(String word : words1) {
                words1Counts.put(word, words1Counts.getOrDefault(word, 0) + 1);
            }

            for(String word : words2) {
                words2Counts.put(word, words2Counts.getOrDefault(word, 0) + 1);
            }

            int count = 0;
            for(var entry : words1Counts.entrySet()) {
                if(entry.getValue() != 1) {
                    continue;
                }

                String word = entry.getKey();
                if(!words2Counts.containsKey(word)
                        || words2Counts.get(word) != 1) {
                    continue;
                }

                count++;
            }

            return count;
        }
    }
}

//    2085. Count Common Words With One Occurrence
//    Easy
//    Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.
//
//
//
//    Example 1:
//
//    Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
//    Output: 2
//    Explanation:
//    - "leetcode" appears exactly once in each of the two arrays. We count this string.
//    - "amazing" appears exactly once in each of the two arrays. We count this string.
//    - "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
//    - "as" appears once in words1, but does not appear in words2. We do not count this string.
//    Thus, there are 2 strings that appear exactly once in each of the two arrays.
//    Example 2:
//
//    Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
//    Output: 0
//    Explanation: There are no strings that appear in each of the two arrays.
//    Example 3:
//
//    Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
//    Output: 1
//    Explanation: The only string that appears exactly once in each of the two arrays is "ab".
//
//
//    Constraints:
//
//    1 <= words1.length, words2.length <= 1000
//    1 <= words1[i].length, words2[j].length <= 30
//    words1[i] and words2[j] consists only of lowercase English letters.