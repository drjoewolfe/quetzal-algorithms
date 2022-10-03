package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class CountVowelSubstringsOfAString {
    class Solution {
        public int countVowelSubstrings(String word) {
            if(word == null || word.length() < 5) {
                return 0;
            }

            int n = word.length();
            int count = 0;

            Set<Character> set = new HashSet<>();
            for(int i = 0; i <= n - 5; i++) {
                char c = word.charAt(i);
                if(!isVowel(c)) {
                    continue;
                }

                set.clear();
                set.add(c);

                for(int j = i + 1; j < n; j++) {
                    c = word.charAt(j);

                    if(!isVowel(c)) {
                        break;
                    }

                    set.add(c);
                    if(set.size() == 5) {
                        count++;
                    }
                }
            }

            return count;
        }

        private boolean isVowel(char c) {
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }

            return false;
        }
    }
}

//    2062. Count Vowel Substrings of a String
//    Easy
//    A substring is a contiguous (non-empty) sequence of characters within a string.
//
//    A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.
//
//    Given a string word, return the number of vowel substrings in word.
//
//
//
//    Example 1:
//
//    Input: word = "aeiouu"
//    Output: 2
//    Explanation: The vowel substrings of word are as follows (underlined):
//    - "aeiouu"
//    - "aeiouu"
//    Example 2:
//
//    Input: word = "unicornarihan"
//    Output: 0
//    Explanation: Not all 5 vowels are present, so there are no vowel substrings.
//    Example 3:
//
//    Input: word = "cuaieuouac"
//    Output: 7
//    Explanation: The vowel substrings of word are as follows (underlined):
//    - "cuaieuouac"
//    - "cuaieuouac"
//    - "cuaieuouac"
//    - "cuaieuouac"
//    - "cuaieuouac"
//    - "cuaieuouac"
//    - "cuaieuouac"
//
//
//    Constraints:
//
//    1 <= word.length <= 100
//    word consists of lowercase English letters only.
