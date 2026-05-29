package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountTheNumberOfSpecialCharactersII {
    class Solution {
        public int numberOfSpecialChars(String word) {
            if (word == null || word.length() < 2) {
                return 0;
            }

            int n = word.length();
            Map<Character, Integer> lastIndexMap = new HashMap<>();
            Map<Character, Integer> firstIndexMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);

                lastIndexMap.put(c, i);
                if (!firstIndexMap.containsKey(c)) {
                    firstIndexMap.put(c, i);
                }
            }

            int count = 0;
            for (char c = 'a'; c <= 'z'; c++) {
                char uc = (char) (c - 'a' + 'A');
                if (lastIndexMap.containsKey(c) && firstIndexMap.containsKey(uc)) {
                    if (lastIndexMap.get(c) < firstIndexMap.get(uc)) {
                        count++;
                    }
                }
            }

            return count;
        }
    }
}

//    3121. Count the Number of Special Characters II
//    Medium
//    You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.
//
//    Return the number of special letters in word.
//
//
//
//    Example 1:
//
//    Input: word = "aaAbcBC"
//
//    Output: 3
//
//    Explanation:
//
//    The special characters are 'a', 'b', and 'c'.
//
//    Example 2:
//
//    Input: word = "abc"
//
//    Output: 0
//
//    Explanation:
//
//    There are no special characters in word.
//
//    Example 3:
//
//    Input: word = "AbBCab"
//
//    Output: 0
//
//    Explanation:
//
//    There are no special characters in word.
//
//
//
//    Constraints:
//
//    1 <= word.length <= 2 * 105
//    word consists of only lowercase and uppercase English letters.